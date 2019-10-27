package pl.henszke.jukebox.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AddTrackDto {
    private int id;

    @JsonCreator
    public AddTrackDto(@JsonProperty("trackId") int id) {
        this.id = id;
    }
}
