package airport.parks.controller.model;

import java.util.HashSet;
import java.util.Set;

import airport.parks.entity.AirportParks;
import airport.parks.entity.Amenity;
import airport.parks.entity.Pilot;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PilotData {
	/*
	 * Putting the data in the correct fields in the database.
	 */
	
	private Long pilotId;
	private String pilotName;
	private String pilotPhone;
	private String pilotEmail;
	
	private Set<AirportParksData> airportParks = new HashSet<>();
	
	public PilotData (Pilot pilot) {
		
		this.pilotId = pilot.getPilotId();
		this.pilotName = pilot.getPilotName();
		this.pilotPhone = pilot.getPilotPhone();
		this.pilotEmail = pilot.getPilotEmail();
		
		for(AirportParks airportParks : pilot.getAirportParks()) {
			this.airportParks.add(new AirportParksData(airportParks));
		}
	}
	
	public PilotData(Long pilotId, String pilotName, String pilotPhone, String pilotEmail) {
		
		this.pilotId = pilotId;
		this.pilotName = pilotName;
		this.pilotPhone = pilotPhone;
		this.pilotEmail = pilotEmail;
	}
	
	public Pilot toPilot() {
		
		Pilot pilot = new Pilot();
		
		pilot.setPilotId(pilotId);
		pilot.setPilotName(pilotName);
		pilot.setPilotPhone(pilotPhone);
		pilot.setPilotEmail(pilotEmail);
		
		for(AirportParksData airportParksData : airportParks) {
			
			pilot.getAirportParks().add(airportParksData.toAirportParks());
		}
		
		return pilot;
	}
	
	
	@Data
	@NoArgsConstructor
	public class AirportParksData {
		
		private Long airportId;
		private String airportName;
		private String airportAddress;
		private String airportCity;
		private String airportState;
		private String airportZip;
		private Set<AmenityData> amenities = new HashSet<>();
		
		public AirportParksData(AirportParks airportParks) {
			
			this.airportId = airportParks.getAirportId();
			this.airportName = airportParks.getAirportName();
			this.airportAddress = airportParks.getAirportAddress();
			this.airportCity = airportParks.getAirportCity();
			this.airportState = airportParks.getAirportState();
			this.airportZip = airportParks.getAirportZip();
			this.airportId = airportParks.getAirportId();
			
			for(Amenity amenity : airportParks.getAmenities()) {
				
				this.amenities.add(new AmenityData(amenity));
			}
		}
		
		public AirportParks toAirportParks() {
			
			AirportParks airportParks = new AirportParks();
			
			airportParks.setAirportId(airportId);
			airportParks.setAirportName(airportName);
			airportParks.setAirportAddress(airportAddress);
			airportParks.setAirportCity(airportCity);
			airportParks.setAirportState(airportState);
			airportParks.setAirportZip(airportZip);
			
			
			for(AmenityData amenityData : amenities) {
				
				airportParks.getAmenities().add(amenityData.toAmenity());
				
			}
			
			return airportParks;
		}
	}
		
		@Data
		@NoArgsConstructor
		public class AmenityData {
			
			private Long amenityId;
			private String amenityTypes;
			
		public AmenityData(Amenity amenity) {
			
			this.amenityId = amenity.getAmenityId();
			this.amenityTypes = amenity.getAmenityTypes();
			
		}
		
		public Amenity toAmenity() {
			
			Amenity amenity =  new Amenity();
			
			amenity.setAmenityId(amenityId);
			amenity.setAmenityTypes(amenityTypes);
			
			
			return amenity;
		}
			
		}
}
