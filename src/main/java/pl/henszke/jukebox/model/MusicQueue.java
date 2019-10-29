package pl.henszke.jukebox.model;


import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class MusicQueue {

    @Id
    @GeneratedValue
    private int id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Track> tracksQueue;

    public MusicQueue() {
        tracksQueue = new LinkedList<>();
    }
    public void addTrackToQueue(Track track){
        tracksQueue.add(track);
    }
    public LinkedList<Track> getScheduledTracks() { return new LinkedList<>(tracksQueue); }
    public Track pop(){
        LinkedList<Track> linkedQueue = getScheduledTracks();
        Track track = linkedQueue.pop();
        setTracksQueue(linkedQueue);
        return track;
    }
    //TODO: should return 5 tracks only
    //TODO: add getFullTracks
    //TODO: dodac kolejne zachowania
}
