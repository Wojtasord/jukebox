package pl.henszke.jukebox.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.henszke.jukebox.model.MusicQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.henszke.jukebox.model.Track;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PlayerServiceIntegrationTest {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TrackRepository trackRepository;

    @DisplayName("When invoke createNewQueue Then newQueue should be returned")
    @Test
    void createMusicQueueTest() {
        // when
        MusicQueue newQueue = playerService.createNewQueue();
        // then
        assertThat(newQueue).isNotNull();
    }

    @DisplayName("When we are adding track to particular musicQueue then this Queue Contains this track  to ")
    @Test
    void addTrackToQueueTest() {
        // given
        int trackId = 1;
        int queueId = 2;
        Track track = new Track();
        track.setId(trackId);
        trackRepository.save(track);
        MusicQueue musicQueue = playerService.createNewQueue();
        musicQueue.setId(queueId);
        // when
        playerService.addTrackToQueue(queueId,trackId);
        // then
        assertThat(playerService.scheduledTracks(queueId)).isNotEmpty();
        assertThat(playerService.scheduledTracks(queueId)).contains(track);
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