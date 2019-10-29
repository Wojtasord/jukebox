package pl.henszke.jukebox.application;

import org.springframework.data.repository.CrudRepository;
import pl.henszke.jukebox.model.Track;

public interface TrackRepository extends CrudRepository<Track,Integer> {

}
