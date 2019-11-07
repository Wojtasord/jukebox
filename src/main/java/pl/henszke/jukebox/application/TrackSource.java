package pl.henszke.jukebox.application;

import pl.henszke.jukebox.model.Track;

import java.io.InputStream;
import java.util.Collection;

public interface TrackSource {
    Iterable<Track> findAll();

    Collection<Track> findByTitle(String name);

    InputStream loadContent(int trackId);
}
