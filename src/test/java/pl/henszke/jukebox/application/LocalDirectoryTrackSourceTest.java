package pl.henszke.jukebox.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocalDirectoryTrackSourceTest {

    @DisplayName("When findAll() on localDirectoryTrackSource " +
            "created with folder containing an .mp3 files" +
            "Then list of Track objects based on that files should be returned")
    @Test
    void findAllTest() {
//        // given
//        Path jukeboxTemp = Files.createTempDirectory("jukeboxTemp");
//        Path tmp = Files.createTempFile("tmp", ".mp3");
//        String uri = tmp.getParent().toString();
//        LocalDirectoryTrackSource localDirectoryTrackSource = new LocalDirectoryTrackSource(uri);
//        // when
//        Collection<Track> result = localDirectoryTrackSource.findAll();
//        // then
//        Assertions.assertThat(result).hasSize(1);
//        Files.deleteIfExists(tmp);
//        //Assertions.assertThat(result).containsExactly()
    }

    @DisplayName("When loadContent() on tracksource containing .mp3 file " +
            "Then this is file is returned as inputStream")
    @Test
    void loadContentTest(){
//        // given
//        File jukeboxTemp = new File(Files.createTempDirectory("jukeboxTemp").toString());
//        File tmp1 = File.createTempFile("tmp1", ".mp3",jukeboxTemp);
//        File tmp2 = File.createTempFile("tmp2", ".mp3",jukeboxTemp);
//        String tempFolderPath = tmp1.getParent().toString();
//        LocalDirectoryTrackSource localDirectoryTrackSource = new LocalDirectoryTrackSource(tempFolderPath);
//        Collection<Track> tracks = localDirectoryTrackSource.findAll();
//        UUID id = tracks.iterator().next().getUuid();
//        // when
//        InputStream inputStream = localDirectoryTrackSource.loadContent(id);
//        // then
//        FileUtils.copyInputStreamToFile(inputStream,tmp2);
//        Assertions.assertThat(Files.isSameFile(tmp1.toPath(),tmp2.toPath())).isTrue();
//        Files.deleteIfExists(jukeboxTemp.toPath());
//        Files.deleteIfExists(tmp2.toPath());
    }
}