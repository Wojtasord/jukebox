package pl.henszke.jukebox.application;

import org.springframework.stereotype.Service;
import pl.henszke.jukebox.model.MusicQueue;
import pl.henszke.jukebox.model.Track;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public LinkedList<Track> getScheduledTracks(int queueId) {
        return queueRepository.findById(queueId).map(MusicQueue::getScheduledTracks).orElse(new LinkedList<>());
    }

    public List<TrackReadDto> getScheduledTracksReadDto(int queueId) {
        return getScheduledTracks(queueId).stream()
                .map(track -> new TrackReadDto(track.getId())).collect(Collectors.toList());
    }

    public Optional<Track> popTrackFromQueue(int queueId){
        return queueRepository.findById(queueId).map(MusicQueue::pop);
    }
}
