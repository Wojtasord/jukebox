package pl.henszke.jukebox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.henszke.jukebox.application.PlayerService;
import pl.henszke.jukebox.model.MusicQueue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/jukebox/musicPlayer")
public class MusicPlayerController {
    private PlayerService playerService;

    public MusicPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> createNewQueue() throws URISyntaxException {
        MusicQueue newQueue = playerService.createNewQueue();
        return ResponseEntity
                .status(HttpStatus.CREATED).location(new URI("/jukebox/musicPlayer/" + newQueue.getId()))
                .build();
    }
    //TODO: use HATEOAS here
    @PostMapping("/{musicPlayerId}/queue")
    public ResponseEntity<?> addTrackToQueue(@PathVariable("musicPlayerId") int playerId ,
                                             @RequestBody AddTrackDto addTrackDto) throws URISyntaxException {
        playerService.addTrackToQueue(playerId,addTrackDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(new URI("/jukebox/musicPlayer/"+playerId+"/queue/"+addTrackDto.getId()))
                .build();
    }

    @GetMapping("/{musicPlayerId}/queue")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackReadDto> scheduledTracks(@PathVariable("musicPlayerId") int playerId){
        return playerService.getScheduledTracksReadDto(playerId);
    }

}
