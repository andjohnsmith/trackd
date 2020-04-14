package trackd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Track {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Artist is required")
	private String artist;
	
	@ManyToMany(mappedBy = "tracks")
	private List<Tracklist> lists;
	
}
