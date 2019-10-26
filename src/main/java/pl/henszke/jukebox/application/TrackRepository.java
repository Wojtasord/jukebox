package pl.henszke.jukebox.application;

import pl.henszke.jukebox.model.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track,Integer> {

}
