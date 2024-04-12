package airport.parks.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class AirportParks {
	/*
	 *  Establishing the airport_parks table in the database using annotations to create the joins.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long airportId;
	private String airportName;
	private String airportAddress;
	private String airportCity;
	private String airportState;
	private String airportZip;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pilot_id", nullable = false)
	private Pilot pilot;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "airport_amenity",
			joinColumns = @JoinColumn(name = "airport_id"),
			inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private Set<Amenity> amenities = new HashSet<>();

}
