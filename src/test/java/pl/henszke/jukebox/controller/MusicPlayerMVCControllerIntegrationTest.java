package pl.henszke.jukebox.controller;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.henszke.jukebox.application.PlayerService;
import pl.henszke.jukebox.application.TrackReadDto;
import pl.henszke.jukebox.model.MusicQueue;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(MusicPlayerMVCController.class)
class MusicPlayerMVCControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    PlayerService playerService;

    @DisplayName("When GET on / then index view is returned")
    @Test
    void getIndex() throws Exception
    {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @DisplayName("When POST on /createPlayer Then new queue is created ")
    @Test
    void postCreatePlayer() throws Exception
    {
        // given
        when(playerService.createNewQueue()).thenReturn(musicQueueWithId(1));
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/createPlayer"))
        // then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/"+1));
        Mockito.verify(playerService,Mockito.times(1)).createNewQueue();

    }

    private MusicQueue musicQueueWithId(int id) {
        MusicQueue musicQueue = new MusicQueue();
        musicQueue.setId(id);
        return musicQueue;
    }

    @DisplayName("When GET on /{playerId} " +
            "Then musicPlayer view is returned with scheduled tracks to that player")
    @Test
    void getMusicPlayer() throws Exception
    {
        // given
        int musicQueueId = 1;
        MusicQueue musicQueue = new MusicQueue();
        musicQueue.setId(musicQueueId);
        when(playerService.createNewQueue()).thenReturn(musicQueue);
        List<TrackReadDto> tracks = Collections.singletonList(new TrackReadDto(2));
        when(playerService.getScheduledTracksReadDto(musicQueueId)).thenReturn(tracks);
        // when
        MusicQueue queue;
        mockMvc.perform(MockMvcRequestBuilders.get("/{playerId}",1))
        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("musicPlayer"))
                .andExpect(MockMvcResultMatchers.model().attribute("queue",tracks));

    }
}