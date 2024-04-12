package airport.parks.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import airport.parks.entity.Pilot;

public interface PilotDao extends JpaRepository<Pilot, Long> {

}
