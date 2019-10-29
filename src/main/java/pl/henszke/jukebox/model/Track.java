package pl.henszke.jukebox.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Track {

    @Id
    @GeneratedValue
    private int id;
    private UUID uuid;
    private String artist;
    private String title;
    private URL url;

    public Track() {
    }

    public Track(String url) throws MalformedURLException {
        this.url = new URL(url);
        uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
