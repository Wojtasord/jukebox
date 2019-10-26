package application;

import model.Track;

import java.util.Collection;
import java.util.LinkedList;

class MusicQueueService {

    private LinkedList<Track> tracklist;

    MusicQueueService() {
        this.tracklist = new LinkedList<>();
    }

    void addTrackToQueue(Track track){
        tracklist.add(track);
    }

    int totalQueueSize() {
        return tracklist.size();
    }

    Collection<Track> scheduledTracks() {
        return tracklist;
    }
}
