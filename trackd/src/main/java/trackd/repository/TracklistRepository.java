package trackd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trackd.model.Tracklist;

@Repository
public interface TracklistRepository extends JpaRepository<Tracklist, Long> {

}
