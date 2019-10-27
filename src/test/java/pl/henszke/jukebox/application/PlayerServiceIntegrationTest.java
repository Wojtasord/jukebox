package pl.henszke.jukebox.application;

import org.assertj.core.api.Fail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.henszke.jukebox.controller.TrackReadDto;
import pl.henszke.jukebox.model.MusicQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.henszke.jukebox.model.Track;

import java.util.List;

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

    @DisplayName("When we are adding track to particular musicQueue " +
            "Then this Queue return this track when performing getScheduledTracks")
    @Test
    void addTrackToQueueAndGetScheduledTracksTest() {
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
        assertThat(playerService.getScheduledTracks(queueId)).isNotEmpty();
        assertThat(playerService.getScheduledTracks(queueId)).contains(track);
    }

    @DisplayName("When getScheduledTracksReadDto() on queue with one track with id 1 " +
            "Then List of TrackReadDto containing same id is returned")
    @Test
    void getScheduledTracksReadDtoTest() {
        // given
        int trackId = 1;
        int queueId = 2;
        Track track = new Track();
        track.setId(trackId);
        trackRepository.save(track);
        MusicQueue musicQueue = playerService.createNewQueue();
        musicQueue.setId(queueId);
        playerService.addTrackToQueue(queueId,trackId);
        // when
        List<TrackReadDto> scheduledTracksReadDto = playerService.getScheduledTracksReadDto(queueId);
        // then
        assertThat(scheduledTracksReadDto).isNotEmpty();
        TrackReadDto expectedTrack = new TrackReadDto(trackId);
        assertThat(scheduledTracksReadDto).containsExactly(expectedTrack);
    }
}