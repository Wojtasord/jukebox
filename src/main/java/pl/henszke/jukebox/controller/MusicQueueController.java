package pl.henszke.jukebox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.henszke.jukebox.application.MusicQueueService;
import pl.henszke.jukebox.model.MusicQueue;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/jukebox/musicQueue")
public class MusicQueueController {

    private MusicQueueService musicQueueService;

    public MusicQueueController(MusicQueueService musicQueueService) {
        this.musicQueueService = musicQueueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewQueue() throws URISyntaxException {
        MusicQueue newQueue = musicQueueService.createNewQueue();
        return ResponseEntity
                .status(HttpStatus.CREATED).location(new URI("/jukebox/musicQueue" + newQueue.getId()))
                .build();
    }
}
