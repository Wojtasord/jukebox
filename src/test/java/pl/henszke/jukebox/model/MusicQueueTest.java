package pl.henszke.jukebox.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.henszke.jukebox.model.MusicQueue;
import pl.henszke.jukebox.model.Track;

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
        assertThat(musicQueue.getScheduledTracks()).hasSize(1);
        assertThat(musicQueue.getScheduledTracks()).contains(track);
    }

}