package pl.henszke.jukebox.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.henszke.jukebox.application.MusicQueueRepository;
import pl.henszke.jukebox.application.MusicQueueService;
import pl.henszke.jukebox.application.TrackRepository;
import pl.henszke.jukebox.model.MusicQueue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MusicQueueServiceIntegrationTest {
    @Autowired
    private MusicQueueService musicQueueService;

    @DisplayName("When invoke createNewQueue Then newQueue should be returned")
    @Test
    void createMusicQueueTest() {
        // when
        MusicQueue newQueue = musicQueueService.createNewQueue();
        // then
        Assertions.assertThat(newQueue).isNotNull();
    }

//    @DisplayName("When we are adding track to particular musicQueue then this Queue Contains this track  to ")
//    @Test
//    void addTrackToQueueTest() throws Exception {
//        // given
//        // when
//        // then
//        Fail.fail("Write your test");
//    }
//
//    @DisplayName("test")
//    @Test
//    void test3
//            () throws Exception {
//        // given
//        // when
//        // then
//        Fail.fail("Write your test");
//    }
}