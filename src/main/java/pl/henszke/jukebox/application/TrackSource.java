package pl.henszke.jukebox.application;

import pl.henszke.jukebox.model.Track;

import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

public interface TrackSource {
    Iterable<Track> findAll();

    Collection<Track> findByTitle(String name);

    InputStream loadContent(UUID trackId);
}
