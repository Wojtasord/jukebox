package pl.henszke.jukebox.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Entity
@Data
public class Track {

    @Id
    @GeneratedValue
    private int id;
    private String artist;
    private String title;
    private URL url;

    public Track() {
    }

    public Track(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(url, track.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
