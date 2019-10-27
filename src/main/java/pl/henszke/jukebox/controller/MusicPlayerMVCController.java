package pl.henszke.jukebox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.henszke.jukebox.application.PlayerService;
import pl.henszke.jukebox.model.MusicQueue;

@Controller
public class MusicPlayerMVCController {

    private PlayerService playerService;

    public MusicPlayerMVCController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{playerId}")
    public String getPlayer(@PathVariable("playerId")int playerId, Model model){
        model.addAttribute("queue",playerService.getScheduledTracksReadDto(playerId));
        return "musicPlayer";
    }

    @PostMapping("/createPlayer")
    public String createPlayer(){
        MusicQueue newQueue = playerService.createNewQueue();
        return "redirect:/"+newQueue.getId();
    }

}
