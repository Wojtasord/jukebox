import lombok.Data;
import lombok.Generated;

import java.util.Objects;

@Data
public class Track {

    @Generated
    private long id;
    private String artist;
    private String title;
    private String  url;

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
