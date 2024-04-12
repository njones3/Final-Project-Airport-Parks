package airport.parks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import airport.parks.controller.model.PilotData;
import airport.parks.controller.model.PilotData.AirportParksData;
import airport.parks.service.ParksService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/airport_parks")
@Slf4j

public class ParksController {
	
	/*
	 *  Creating mappings to use CRUD.  Create, Read, Update, and Delete.
	 */
	
	@Autowired
	private ParksService parksService;
	
	@PostMapping("/pilot")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PilotData createPilot(@RequestBody PilotData pilotData) {
		
		log.info("Creating Pilot {}", pilotData);
		
		return parksService.savePilot(pilotData);
	}
	
	@PutMapping("/pilot/{pilotId}")
	public PilotData updatePilot(@PathVariable Long pilotId, @RequestBody PilotData pilotData) {
		
		pilotData.setPilotId(pilotId);
		log.info("Updating pilot {}", pilotData);
		
		return parksService.savePilot(pilotData);
	}
	

	@GetMapping("/pilot/{pilotId}")
	public PilotData retrievePilot(@PathVariable Long pilotId) {
		
		log.info("Retrieving pilot with ID={}", pilotId);
		
		return parksService.retrievePilotById(pilotId);
	}
	
	@GetMapping("/pilot")
	public List<PilotData> retrieveAllPilots() {
		
		log.info("Retrieveing all pilot.");
		
		return parksService.retrieveAllPilots();
	}
	
	@DeleteMapping("/pilot/{pilotId}")
	public Map<String, String> deletePilot(@PathVariable Long pilotId) {
		
		log.info("Pilot with ID=" + pilotId + ".");
		
		parksService.deletePilot(pilotId);
		
		return Map.of("message", "Pilot with ID=" + pilotId 
				+ " was deleted successfully.");
	}
}
