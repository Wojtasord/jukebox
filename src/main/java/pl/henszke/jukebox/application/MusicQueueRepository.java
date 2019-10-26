package pl.henszke.jukebox.application;

import pl.henszke.jukebox.model.MusicQueue;
import org.springframework.data.repository.CrudRepository;

public interface MusicQueueRepository extends CrudRepository<MusicQueue,Integer> {
}
