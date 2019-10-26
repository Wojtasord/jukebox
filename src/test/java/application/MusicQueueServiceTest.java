package application;

import application.MusicQueueService;
import model.Track;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MusicQueueServiceTest {

    @DisplayName("When add model.Track to queue then queue should have this track")
    @Test
    void addTrackToQueueTest()
    {
        // given
        MusicQueueService musicQueueService = new MusicQueueService();
        Track track = new Track();
        // when
        musicQueueService.addTrackToQueue(track);
        // then
        assertThat(musicQueueService.totalQueueSize()).isEqualTo(1);
        assertThat(musicQueueService.scheduledTracks()).hasSize(1);
        assertThat(musicQueueService.scheduledTracks()).contains(track);
    }

}