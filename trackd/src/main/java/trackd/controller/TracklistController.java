package trackd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String createTracklist(@Valid @ModelAttribute("tracklist") Tracklist tracklist, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tracklist", bindingResult);
            redirectAttributes.addFlashAttribute("tracklist", tracklist);
			return "redirect:/tracklists/new";
		}
		
		tracklist = tracklistService.createOrUpdateTracklist(tracklist);
		
		return "redirect:/tracklists/" + tracklist.getId();
	}
	
	@GetMapping("/tracklists/new")
	public String showNewTracklistForm(Model model) {
		if (!model.containsAttribute("tracklist")) {
			Tracklist tracklist = new Tracklist();
			
			model.addAttribute("tracklist", tracklist);
		}
		
		return "create";
	}
	
	@GetMapping("/tracklists/{id}")
	public String showSinglePage(@PathVariable(name = "id") Long id, Model model) {
		if (!model.containsAttribute("track")) {
			Track track = new Track();
			model.addAttribute("track", track);
		}
		
		Tracklist tracklist = tracklistService.findById(id);
		model.addAttribute("tracklist", tracklist);
		
		return "single";
	}
	
	@PostMapping("/tracklists/{id}/tracks")
	public String addTrack(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("track") Track track,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.track", bindingResult);
            redirectAttributes.addFlashAttribute("track", track);
		} else {
			Tracklist tracklist = tracklistService.findById(id);
			
			track = trackService.saveTrack(track);
		
			tracklist.getTracks().add(track);
			
			tracklistService.createOrUpdateTracklist(tracklist);
		}
		
		return "redirect:/tracklists/" + id;
	}
	
}
