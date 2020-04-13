package trackd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trackd.model.Track;
import trackd.repository.TrackRepository;

@Service
public class TrackService {

	@Autowired
	private TrackRepository repository;
	
	public Track saveTrack(Track track) {
		track = repository.save(track);
		
		return track;
	}
	
	public List<Track> findByTitleAndArtist(String title, String artist) {
		List<Track> allTracks = repository.findAll();
		List<Track> result = new ArrayList<Track>();
		
		for (Track t : allTracks) {
			if (t.getTitle().equals(title) && t.getArtist().equals(artist)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
}
