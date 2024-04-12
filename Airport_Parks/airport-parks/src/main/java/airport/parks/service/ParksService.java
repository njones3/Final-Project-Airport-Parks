package airport.parks.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airport.parks.controller.model.PilotData;
import airport.parks.dao.PilotDao;
import airport.parks.entity.Pilot;

@Service

public class ParksService {
	
	@Autowired
	private PilotDao pilotDao;
	
	@Transactional(readOnly = false)
	public PilotData savePilot(PilotData pilotData) {
		
		Pilot pilot = pilotData.toPilot();
		
		Pilot dbPilot = pilotDao.save(pilot);
		
		return new PilotData(dbPilot);
	}

	@Transactional(readOnly = true)	
	public PilotData retrievePilotById(Long pilotId) {
		
		Pilot pilot = findPilotById(pilotId);
		return new PilotData(pilot);
		
	}

	private Pilot findPilotById(Long pilotId) {

		return pilotDao.findById(pilotId).orElseThrow(
				() -> new NoSuchElementException(
						"Location with ID=" + pilotId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<PilotData> retrieveAllPilots() {
		
		List<Pilot> pilotEntities = pilotDao.findAll();
		List<PilotData> pilotDtos = new LinkedList<>();
		
		for(Pilot pilot : pilotEntities) {
			
			PilotData pilotData = new PilotData(pilot);
			pilotDtos.add(pilotData);
		}
		
		return pilotDtos;
		
	}

	@Transactional(readOnly = false)
	public void deletePilot(Long pilotId) {
		
		Pilot pilot = findPilotById(pilotId);
		pilotDao.delete(pilot);
		
	}
}
