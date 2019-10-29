package pl.henszke.jukebox.application;

import pl.henszke.jukebox.model.Track;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocalDirectoryTrackSource implements TrackSource {

    private String directorySourceUrl;
    private Path sourceDirectoryPath;

    public LocalDirectoryTrackSource(String directorySourceUrl) {
        this.directorySourceUrl = directorySourceUrl;
        sourceDirectoryPath = new File(directorySourceUrl).toPath();
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
    public InputStream loadContent(UUID trackId) {
        ArrayList<Track> result = new ArrayList<>();
        Track track1 = findAll().stream()
                .filter(track -> track.getUuid() == trackId).findFirst().get();
        try {
            return new FileInputStream(new File(String.valueOf(track1.getUrl())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Collection<Track> readTracksFromDirectory(Path directory) throws IOException {
        List<Track> allTracks = new ArrayList<>();
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".mp3")) {
                    allTracks.add(new Track("file://" + file.toAbsolutePath()));
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return allTracks;
    }
}
