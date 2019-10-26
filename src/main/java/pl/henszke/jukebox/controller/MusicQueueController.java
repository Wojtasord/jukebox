package pl.henszke.jukebox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> createNewQueue() throws URISyntaxException {
        MusicQueue newQueue = musicQueueService.createNewQueue();
        return ResponseEntity
                .status(HttpStatus.CREATED).location(new URI("/jukebox/musicQueue/" + newQueue.getId()))
                .build();
    }
    //TODO: use HATEOAS here
    @PostMapping("{musicQueueId}/tracks")
    public ResponseEntity<?> addTrackToQueue(@PathVariable("musicQueueId") int queueId ,
                                             @RequestBody AddTrackDto addTrackDto) throws URISyntaxException {
        musicQueueService.addTrackToQueue(queueId,addTrackDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(new URI("/jukebox/musicQueue/"+queueId+"/tracks/"+addTrackDto.getId()))
                .build();
    }

    @GetMapping("{musicQueueId}")
    public ResponseEntity<?> scheduledTracks(@PathVariable("musicQueueId") int queueId){
        return  null;
    }

}
