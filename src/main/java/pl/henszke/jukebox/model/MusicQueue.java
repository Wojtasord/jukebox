package pl.henszke.jukebox.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;

@Entity
@Data
public class MusicQueue {

    @Id
    @GeneratedValue
    private int id;
    private LinkedList<Track> tracksQueue;

    public MusicQueue() {
        tracksQueue = new LinkedList<>();
    }

    public void addTrackToQueue(Track track){
        tracksQueue.add(track);
    }
    LinkedList<Track> getScheduledTracks() {
        return tracksQueue;
    } //TODO: should return 5 tracks only
    //TODO: add getFullTracks
    //TODO: dodac kolejne zachowania
}
