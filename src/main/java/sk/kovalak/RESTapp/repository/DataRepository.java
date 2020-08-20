package sk.kovalak.RESTapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kovalak.RESTapp.entities.Data;
import sk.kovalak.RESTapp.entities.Sensor;

import java.util.Date;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data,Long> {
    List<Data> findAllBySensor(Sensor sensor);
    List<Data> findAllByDate(Date date);
    List<Data> findAllBySensorAndDate(Sensor sensor, Date date);
}
