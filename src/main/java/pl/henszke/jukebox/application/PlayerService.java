package pl.henszke.jukebox.application;

import org.springframework.stereotype.Service;
import pl.henszke.jukebox.model.MusicQueue;
import pl.henszke.jukebox.model.Track;

import java.util.LinkedList;

@Service
public class PlayerService {

    private MusicQueueRepository queueRepository;
    private TrackRepository trackRepository;

    public PlayerService(MusicQueueRepository queueRepository, TrackRepository trackRepository) {
        this.queueRepository = queueRepository;
        this.trackRepository = trackRepository;
    }

    public MusicQueue createNewQueue() {
        return queueRepository.save(new MusicQueue());
    }

    public void addTrackToQueue(int queueId, int trackId) {
        queueRepository
                .findById(queueId)
                .ifPresent(musicQueue -> trackRepository.findById(trackId)
                        .ifPresent(track -> {
                                    musicQueue.addTrackToQueue(track);
                                    queueRepository.save(musicQueue);
                                }
                        ));
    }


    LinkedList<Track> scheduledTracks(int queueId) {
        return queueRepository.findById(queueId).map(MusicQueue::getTracksQueue).orElse(new LinkedList<>());
    }

}
