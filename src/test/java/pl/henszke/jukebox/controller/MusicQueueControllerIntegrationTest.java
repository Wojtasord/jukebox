package pl.henszke.jukebox.controller;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.henszke.jukebox.application.MusicQueueService;
import pl.henszke.jukebox.model.MusicQueue;

@WebMvcTest(controllers = MusicQueueController.class)
class MusicQueueControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MusicQueueService musicQueueService;

    @BeforeEach
    void setup(){
        MusicQueue musicQueue = new MusicQueue();
        musicQueue.setId(1);
        Mockito.when(musicQueueService.createNewQueue()).thenReturn(musicQueue);
    }

    @DisplayName("When POST on /jukebox/musicQueue Then new queue is created")
    @Test
    void createMusicQueuePostTest() throws Exception {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/jukebox/musicQueue"))
                // then
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(musicQueueService).createNewQueue();
    }

    @DisplayName("When POST on /jukebox/musicQueue/{id}/tracks with content treackId Then" +
            "queue with that id adds this track")
    @Test
    void musicQueueGetTest() throws Exception
    {
        // given

        // when
        String content = "{\"trackId\" : 2}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/jukebox/musicQueue/{id}/tracks",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        // then
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(musicQueueService).addTrackToQueue(1,2);

    }

//    @DisplayName("When Get on /jukebox/musicQueue/{id} Then ")
//    @Test
//    void test() throws Exception
//    {
//        // given
//        // when
//        // then
//        Fail.fail("Write your test");
//    }

}