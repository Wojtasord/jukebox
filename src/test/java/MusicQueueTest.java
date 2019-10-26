import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MusicQueueTest {

    @DisplayName("When add Track to queue then queue should have this track")
    @Test
    void addTrackToQueueTest()
    {
        // given
        MusicQueue musicQueue = new MusicQueue();
        Track track = new Track();
        // when
        musicQueue.addTrackToQueue(track);
        // then
        assertThat(musicQueue.totalQueueSize()).isEqualTo(1);
        assertThat(musicQueue.scheduledTracks()).hasSize(1);
        assertThat(musicQueue.scheduledTracks()).contains(track);
    }

}