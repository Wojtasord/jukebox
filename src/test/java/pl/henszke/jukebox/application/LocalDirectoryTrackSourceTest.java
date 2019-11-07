package pl.henszke.jukebox.application;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.henszke.jukebox.model.Track;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;

class LocalDirectoryTrackSourceTest {

    @DisplayName("When findAll() on localDirectoryTrackSource " +
            "created with folder containing an .mp3 files" +
            "Then list of Track objects based on that files should be returned")
    @Test
    void findAllTest() throws IOException {
        // given
        File tempDirectory = new File(Files.createTempDirectory("jukeboxTemp").toString());
        File tmpMp3File = File.createTempFile("jukebox", ".mp3",tempDirectory);
        String tempDirectoryPath = tempDirectory.getPath();
        LocalDirectoryTrackSource localDirectoryTrackSource = new LocalDirectoryTrackSource(tempDirectoryPath);
        Track track = new Track();
        track.setUrl(tmpMp3File.toURL());
        // when
        Collection<Track> result = localDirectoryTrackSource.findAll();
        // then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result).containsExactly(track);
        FileUtils.deleteDirectory(tempDirectory);
    }

    @DisplayName("When loadContent() on tracksource containing .mp3 file " +
            "Then this is file is returned as inputStream")
    @Test
    void loadContentTest() throws IOException {
        // given
        File jukeboxTemp = new File(Files.createTempDirectory("jukeboxTemp").toString());
        File tmp1 = File.createTempFile("tmp1", ".mp3",jukeboxTemp);
        File tmp2 = File.createTempFile("tmp2", ".mp3",jukeboxTemp);
        String tempFolderPath = tmp1.getParent();
        Files.writeString(tmp1.toPath(),"testmp3");
        LocalDirectoryTrackSource localDirectoryTrackSource = new LocalDirectoryTrackSource(tempFolderPath);
        Collection<Track> tracks = localDirectoryTrackSource.findAll();
        int id = tracks.iterator().next().getId();
        // when
        InputStream inputStream = localDirectoryTrackSource.loadContent(id);
        // then
        FileUtils.copyInputStreamToFile(inputStream,tmp2);
        Assertions.assertThat(Files. isSameFile(tmp1.toPath(),tmp2.toPath())).isTrue();
        Files.deleteIfExists(jukeboxTemp.toPath());
        Files.deleteIfExists(tmp2.toPath());
    }
}