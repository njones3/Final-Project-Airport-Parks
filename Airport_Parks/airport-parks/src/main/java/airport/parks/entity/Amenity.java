package airport.parks.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Amenity {
	/*
	 *  Establishing the amenity table in the database using annotations to create the joins.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long amenityId;
	private String amenityTypes;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "amenities")
	private Set<AirportParks> airportParks = new HashSet<>();

}
