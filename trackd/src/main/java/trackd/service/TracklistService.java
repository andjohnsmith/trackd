package trackd.service;

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
			tracklist = repository.save(tracklist);
			return tracklist;
		} else {
			Tracklist newTracklist = repository.findById(tracklist.getId()).get();
			newTracklist.setName(tracklist.getName());
			newTracklist.setDescription(tracklist.getDescription());
			newTracklist.setTracks(tracklist.getTracks());
			newTracklist = repository.save(newTracklist);
			return newTracklist;
		}
	}
	
	public List<Tracklist> findAll() {
		return repository.findAll();
	}
	
	public Tracklist findById(Long id) {
		return repository.findById(id).get();
	}
	
}
