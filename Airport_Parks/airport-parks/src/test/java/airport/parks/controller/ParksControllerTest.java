package airport.parks.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import airport.parks.AirportParksApplication;
import airport.parks.controller.model.PilotData;


@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = AirportParksApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")

class ParksControllerTest extends ParksServiceTestSupport {

	@Test
	void testInsertPilot() {
		
		// Given: A pilot request
		PilotData request = buildInsertPilot(1);
		PilotData expected = buildInsertPilot(1);
		
		// When: the pilot is added to the pilot table
		PilotData actual = insertPilot(request);
		
		// Then: the pilot returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the pilot table.
		assertThat(rowsInPilotTable()).isOne();
	}
	
	@Test
	void testRetrievePilot() {
		
		// Given: A pilot.
		PilotData pilot = insertPilot(buildInsertPilot(1));
		PilotData expected = buildInsertPilot(1);
		
		// When: The pilot is retrieved by pilot ID.
		PilotData actual = retrievePilot(pilot.getPilotId());
		
		
		// Then: the actual pilot is equal to the expected pilot.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testRetrieveAllLocations() {
		
		// Given: Two pilots.
		List<PilotData> expected = insertTwoPilots();
		
		
		// When: All pilots are retrieved.
		List<PilotData> actual = retreiveAllPilots();
		
		
		// Then: the retrieved pilots are the same as what is expected.
		assertThat(actual).isEqualTo(expected);
		
	}
	
	@Test
	void testUpdatePilot() {
		
		// Given: a pilot and an update request.
		insertPilot(buildInsertPilot(1));
		PilotData expected = buildUpdatePilot();
				
		// When: the pilot is updated.
		PilotData actual = updatePilot(expected);
				
		// Then: the pilot is returned as expected.
		assertThat(actual).isEqualTo(expected);
		
		
		// And: there is one row in the pilot table.
		assertThat(rowsInPilotTable()).isOne();
	}
	
	@Test
	void testDeletePilotWithAirportParks() {
		
		// Given: An airport park and two amenities.
		PilotData pilot = insertPilot(buildInsertPilot(1));
		Long pilotId = pilot.getPilotId();
		
		insertAirportParks(1);
		insertAirportParks(2);
		
		assertThat(rowsInPilotTable()).isOne();
		assertThat(rowsInAirportParksTable()).isEqualTo(2);
		assertThat(rowsInAirportAmenityTable()).isEqualTo(4);
		int amenityRows = rowsInAmenityTable();
		
		// When: the pilot is deleted
		deletePilot(pilotId);
				
		// Then: there are no pilots, airport parks, or airport_amenity rows
		assertThat(rowsInPilotTable()).isZero();
		assertThat(rowsInAirportParksTable()).isZero();
		assertThat(rowsInAirportAmenityTable()).isZero();
				
		// And: the number of amenity rows has not changed.
		assertThat(rowsInAmenityTable()).isEqualTo(amenityRows);
		
	}
}
	
	
		



