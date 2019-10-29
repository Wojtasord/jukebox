package pl.henszke.jukebox.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("When pop track from queue " +
            "Then first track from queue is returned and queue no longer contain that track")
    @Test
    void popTrack() {
        // given
        MusicQueue musicQueue = new MusicQueue();
        Track track1 = new Track();
        track1.setId(2);
        Track track2 = new Track();
        track2.setId(3);
        musicQueue.addTrackToQueue(track1);
        musicQueue.addTrackToQueue(track2);
        // when
        Track popedTrack = musicQueue.pop();
        // then
        assertThat(popedTrack).isEqualTo(track1);
        assertThat(musicQueue.getScheduledTracks()).hasSize(1);
    }
}