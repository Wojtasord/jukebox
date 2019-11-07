package pl.henszke.jukebox.application;

import org.apache.commons.io.FileUtils;
import pl.henszke.jukebox.model.Track;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class LocalDirectoryTrackSource implements TrackSource {

    private Path sourceDirectoryPath;
    private Collection<Track> tracks;

    public LocalDirectoryTrackSource(String directorySourceUrl) {
        sourceDirectoryPath = new File(directorySourceUrl).toPath();
        tracks = new ArrayList<>();
    }

    @Override
    public Collection<Track> findAll() {
        try {
            return readTracksFromDirectory(sourceDirectoryPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load from source directory", e);
        }
    }

    @Override
    public Collection<Track> findByTitle(String title) {
        return findAll().stream()
                .filter(track -> track.getTitle().contains(title)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public InputStream loadContent(int trackId) {
        Collection<Track> result = findAll();
        Optional<Track> track1 = result.stream()
                .filter(track -> track.getId() == trackId).findAny();
        try {
            return FileUtils.openInputStream(new File(String.valueOf(track1.orElseThrow(() -> new FileNotFoundException("Cant find this track")).getUrl().getPath())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Collection<Track> readTracksFromDirectory(Path directory) throws IOException {
        Set<Track> allTracks = new HashSet<>(tracks);
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".mp3")) {
                    allTracks.add(new Track("file:/" + file.toAbsolutePath()));
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return allTracks;
    }
}
