package sk.kovalak.RESTapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kovalak.RESTapp.entities.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    Sensor findByName(String name);
    Sensor findSensorById(Long id);
}
