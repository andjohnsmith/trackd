package trackd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Track {
	
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	
	private String artist;
	
	@ManyToMany(mappedBy = "tracks")
	private List<Tracklist> lists;
	
}
