package airport.parks.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Pilot {
	/*
	 *  Establishing the pilot table in the database using annotations to create the joins.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long pilotId;
	
	@EqualsAndHashCode.Exclude
	private String pilotName;
	
	@EqualsAndHashCode.Exclude
	private String pilotPhone;
	
	@Column(unique = true)
	private String pilotEmail;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "pilot", cascade = CascadeType.ALL)
	private Set<AirportParks> airportParks = new HashSet<>();

}
