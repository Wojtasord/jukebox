package pl.henszke.jukebox.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TrackReadDto {
    private int trackId;
    @JsonCreator
    public TrackReadDto(@JsonProperty("trackId") int trackId) {
        this.trackId = trackId;
    }
}
