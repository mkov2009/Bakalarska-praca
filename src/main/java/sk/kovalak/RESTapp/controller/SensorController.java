package sk.kovalak.RESTapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.kovalak.RESTapp.entities.Sensor;
import sk.kovalak.RESTapp.exceptions.BadRequestException;
import sk.kovalak.RESTapp.exceptions.SensorNotFoundException;
import sk.kovalak.RESTapp.repository.SensorRepository;

import java.util.List;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorController(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Sensor> findAllSensors(){
        return sensorRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Sensor findSensor(@PathVariable Long id){
        if(id == null){
            throw new BadRequestException();
        }
        Sensor sensor = sensorRepository.findSensorById(id);
        if(sensor == null){
            throw new SensorNotFoundException();
        }
        return sensor;
    }

    @RequestMapping(params = "name", method = RequestMethod.GET)
    public Sensor findSensorByName(@RequestParam String name){
        if(name.equals("")){
            throw new BadRequestException();
        }
        Sensor sensor = sensorRepository.findByName(name);
        if(sensor == null){
            throw new SensorNotFoundException();
        }
        return sensor;
    }

    @RequestMapping (value = "/**", method = RequestMethod.GET)
    public void otherRequests(){
        throw new BadRequestException();
    }



}
