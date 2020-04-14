package trackd.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trackd.model.Tracklist;
import trackd.repository.TracklistRepository;

@Service
public class TracklistService {

	@Autowired
	private TracklistRepository repository;
	
	public Tracklist createOrUpdateTracklist(Tracklist tracklist) {
		if (tracklist.getId() ==  null) {
			tracklist.setDate(getDate());
			tracklist = repository.save(tracklist);
			return tracklist;
		} else {
			Tracklist newTracklist = repository.findById(tracklist.getId()).get();
			
			newTracklist.setName(tracklist.getName());
			newTracklist.setAuthor(tracklist.getAuthor());
			newTracklist.setDescription(tracklist.getDescription());
			newTracklist.setTracks(tracklist.getTracks());
			
			newTracklist = repository.save(newTracklist);
			return newTracklist;
		}
	}
	
	private static String getDate() {
		LocalDateTime ldt = LocalDateTime.now();
		StringBuilder date = new StringBuilder();
		
		date.append(toTitleCase(ldt.getMonth().toString()));
		date.append(" ");
		date.append(ldt.getDayOfMonth());
		date.append(", ");
		date.append(ldt.getYear());
		
		return date.toString();
	}
	
	private static String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder(input.length());

	    titleCase.append(input.charAt(0));
	    
	    for (int i = 1; i < input.length(); i++) {
	        char c = input.charAt(i);
	        c = Character.toLowerCase(c);

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
	
	public List<Tracklist> findAll() {
		return repository.findAll();
	}
	
	public Tracklist findById(Long id) {
		return repository.findById(id).get();
	}
	
}
