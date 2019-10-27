package pl.henszke.jukebox.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.henszke.jukebox.application.PlayerService;
import pl.henszke.jukebox.model.MusicQueue;

import java.util.Collections;
import java.util.List;
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WebMvcTest(controllers = MusicPlayerController.class)
class MusicPlayerControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    private int musicQueueId = 1;
    @BeforeEach
    void setup(){
        MusicQueue musicQueue = new MusicQueue();
        musicQueue.setId(musicQueueId);
        Mockito.when(playerService.createNewQueue()).thenReturn(musicQueue);
    }

    @DisplayName("When POST on /jukebox/musicPlayer Then new queue is created")
    @Test
    void createMusicQueuePostTest() throws Exception {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/jukebox/musicPlayer"))
                // then
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(playerService).createNewQueue();
    }

    @DisplayName("When POST on /jukebox/musicPlayer/{id}/tracks with content trackId Then" +
            "player queue with that id adds this track")
    @Test
    void musicPlayerPostTracksToQueueTest() throws Exception
    {
        // given
        String content = "{\"trackId\" : 2}";
        // when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/jukebox/musicPlayer/{id}/queue",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        // then
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(playerService).addTrackToQueue(1,2);

    }

    @DisplayName("When Get on /jukebox/musicPlayer/{id}/queue Then list of track id is returned")
    @Test
    void getOnTracksFromQueueTest() throws Exception {
        // given
        List<TrackReadDto> tracks = Collections.singletonList(new TrackReadDto(2));
        Mockito.when(playerService.getScheduledTracksReadDto(musicQueueId)).thenReturn(tracks);
        // when
        mockMvc.perform(MockMvcRequestBuilders
                .get("/jukebox/musicPlayer/{id}/queue", 1))
        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trackId", Matchers.is(2)));
    }

}