package airport.parks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import airport.parks.controller.model.PilotData;
import airport.parks.entity.Pilot;

public class ParksServiceTestSupport {
	
	
	private static final String AIRPORT_PARKS_TABLE = "airport_parks";

	private static final String AIRPORT_AMENITY_TABLE = "airport_amenity";

	private static final String AMENITY_TABLE = "amenity";

	private static final String PILOT_TABLE = "pilot";

	private static final String INSERT_AIRPORT_PARKS_1_SQL = """
	
			insert into airport_parks 
			(airport_name, airport_address, airport_city, airport_state, airport_zip, pilot_id)
			values ('Winchester Regional Airport', '491 Airport Rd', 'Winchester', 'VA', '22602', 1)
			""";

	private static final String INSERT_AIRPORT_PARKS_2_SQL = """
			
			insert into airport_parks 
			(airport_name, airport_address, airport_city, airport_state, airport_zip, pilot_id)
			values ('Hampton Roads Executive Airport', '5172 W Military Highway', 'Chesapeake', 'VA', '23321', 1)
			""";

	private static final String INSERT_AMENITIES_1_SQL = """
			
			insert into airport_amenity
			(airport_id, Amenity_id)
			values (1, 1), (1, 1)
			""";

	private static final String INSERT_AMENITIES_2_SQL = """
			
			insert into airport_amenity
			(airport_id, amenity_id)
			values (2,2), (2, 2)
			""";
	
	// @formatter:off
	
	private PilotData insertPilotName1 = new PilotData(
			
			1L,
			"Belinda Jones",
			"540-678-0728",
			"jonesbe@gmail.com"
			);
	
	private PilotData insertPilotName2 = new PilotData(
			
			2L,
			"Tyler Jones",
			"540-247-0105",
			"TJones@gmail.com"
			);
	
	private PilotData updatePilot1 = new PilotData(
			
			1L,
			"Gus Jones",
			"540-662-3993",
			"gusterjones@gmail.com"
			);
	
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ParksController parksController;
	
	protected PilotData buildInsertPilot(int which) {
		
		
		return which == 1 ? insertPilotName1 : insertPilotName2;
	}
	
protected int rowsInPilotTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PILOT_TABLE);
	}

	protected PilotData insertPilot(PilotData pilotData) {
		
		Pilot pilot = pilotData.toPilot();
		PilotData clone = new PilotData(pilot);
		
		clone.setPilotId(null);
		return parksController.createPilot(clone);
	}
	
	protected PilotData retrievePilot(Long pilotId) {
		
		return parksController.retrievePilot(pilotId);
	}
	
	protected List<PilotData> insertTwoPilots() {
		
		PilotData pilot1 = insertPilot(buildInsertPilot(1));
		PilotData pilot2 = insertPilot(buildInsertPilot(2));
		
		return List.of(pilot1, pilot2);
	}

	protected List<PilotData> retreiveAllPilots() {
		
		return parksController.retrieveAllPilots();
	}
	
	protected PilotData updatePilot(PilotData pilotData) {
		
		return parksController.updatePilot(pilotData.getPilotId(), pilotData);
	}

	protected PilotData buildUpdatePilot() {
		
		return updatePilot1;
	}
	
	protected void insertAirportParks(int which) {
		
		String airportParksSql = which == 1 ? INSERT_AIRPORT_PARKS_1_SQL : INSERT_AIRPORT_PARKS_2_SQL;
		String amenitySql = which == 1 ? INSERT_AMENITIES_1_SQL : INSERT_AMENITIES_2_SQL;
		
		jdbcTemplate.update(airportParksSql);
		jdbcTemplate.update(amenitySql);
	}
	
protected int rowsInAmenityTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AMENITY_TABLE);
	}

	protected int rowsInAirportAmenityTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AIRPORT_AMENITY_TABLE);
	}

	protected int rowsInAirportParksTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AIRPORT_PARKS_TABLE);
	}
	
	protected void deletePilot(Long pilotId) {
		
		parksController.deletePilot(pilotId);
		
	}
}
