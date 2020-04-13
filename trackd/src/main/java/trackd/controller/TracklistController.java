package trackd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import trackd.model.Track;
import trackd.model.Tracklist;
import trackd.service.TrackService;
import trackd.service.TracklistService;

@Controller
public class TracklistController {

	@Autowired
	private TracklistService tracklistService;
	
	@Autowired
	private TrackService trackService;
	
	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/tracklists")
	public String showTracklists(Model model) {
		List<Tracklist> list = tracklistService.findAll();
		
		model.addAttribute("tracklists", list);
		
		return "tracklists";
	}
	
	@PostMapping("/tracklists")
	public String createTracklist(@ModelAttribute("tracklist") Tracklist tracklist, Model model) {
		tracklist = tracklistService.createOrUpdateTracklist(tracklist);
		
		return "redirect:/tracklists/" + tracklist.getId();
	}
	
	@GetMapping("/tracklists/new")
	public String showNewTracklistForm(Model model) {
		Tracklist tracklist = new Tracklist();
		
		model.addAttribute("tracklist", tracklist);
		
		return "create";
	}
	
	@GetMapping("/tracklists/{id}")
	public String showSinglePage(@PathVariable(name = "id") Long id, Model model) {
		Tracklist tracklist = tracklistService.findById(id);
		Track track = new Track();
		
		model.addAttribute("tracklist", tracklist);
		model.addAttribute("track", track);
		
		return "single";
	}
	
	@PostMapping("/tracklists/{id}/tracks")
	public String addTrack(@PathVariable(name = "id") Long id, @ModelAttribute("track") Track track, Model model) {
		Tracklist tracklist = tracklistService.findById(id);
		
		track = trackService.saveTrack(track);
	
		tracklist.getTracks().add(track);
		
		tracklistService.createOrUpdateTracklist(tracklist);
		
		return "redirect:/tracklists/" + id;
	}
	
}
