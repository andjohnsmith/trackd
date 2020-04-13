package trackd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Tracklist {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToMany
	@JoinTable(
	  name = "tracklist_track",
	  joinColumns = @JoinColumn(name = "tracklist_id"),
	  inverseJoinColumns = @JoinColumn(name = "track_id"))
	private List<Track> tracks;
	
}
