package trackd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trackd.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}
