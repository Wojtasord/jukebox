package pl.henszke.jukebox.application;

import org.assertj.core.api.Fail;
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
import pl.henszke.jukebox.model.Track;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MusicQueueServiceIntegrationTest {
    @Autowired
    private MusicQueueService musicQueueService;
    @Autowired
    private TrackRepository trackRepository;

    @DisplayName("When invoke createNewQueue Then newQueue should be returned")
    @Test
    void createMusicQueueTest() {
        // when
        MusicQueue newQueue = musicQueueService.createNewQueue();
        // then
        assertThat(newQueue).isNotNull();
    }

    @DisplayName("When we are adding track to particular musicQueue then this Queue Contains this track  to ")
    @Test
    void addTrackToQueueTest() throws Exception {
        // given
        int trackId = 1;
        int queueId = 2;
        Track track = new Track();
        track.setId(trackId);
        trackRepository.save(track);
        MusicQueue musicQueue = musicQueueService.createNewQueue();
        musicQueue.setId(queueId);
        // when
        musicQueueService.addTrackToQueue(queueId,trackId);
        // then
        assertThat(musicQueueService.scheduledTracks(queueId)).isNotEmpty();
        assertThat(musicQueueService.scheduledTracks(queueId)).contains(track);
    }
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