import java.util.Collection;
import java.util.LinkedList;

class MusicQueue {

    private LinkedList<Track> tracklist;

    MusicQueue() {
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
