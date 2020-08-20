package sk.kovalak.RESTapp.controller;

import org.springframework.web.bind.annotation.*;
import sk.kovalak.RESTapp.entities.Data;
import sk.kovalak.RESTapp.entities.Sensor;
import sk.kovalak.RESTapp.exceptions.BadRequestException;
import sk.kovalak.RESTapp.exceptions.DataNotFoundException;
import sk.kovalak.RESTapp.exceptions.SensorNotFoundException;
import sk.kovalak.RESTapp.repository.DataRepository;
import sk.kovalak.RESTapp.repository.SensorRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/data")
public class DataController {

    private final DataRepository dataRepository;
    private final SensorRepository sensorRepository;

    public DataController(DataRepository dataRepository, SensorRepository sensorRepository){
        this.dataRepository = dataRepository;
        this.sensorRepository = sensorRepository;
    }

    //GET All data
    @RequestMapping(method = RequestMethod.GET)
    public List<Data> findAllData(){
        return dataRepository.findAll();
    }

    //GET All data by sensor id
    @RequestMapping(value="/sensor",params = "id", method = RequestMethod.GET)
    public List<Data> findDataBySensorId(@RequestParam Long id){

        Sensor sensor = sensorRepository.findSensorById(id);
        if(id == null){
            throw new BadRequestException();
        }
        if(sensor == null){
            throw new SensorNotFoundException();
        }

        return dataRepository.findAllBySensor(sensor);
    }

    //GET All data by sensor name
    @RequestMapping(value="/sensor", params = "name", method = RequestMethod.GET)
    public List<Data> findDataBySensorName(@RequestParam String name){
        Sensor sensor = sensorRepository.findByName(name);
        if(name.equals("")){
            throw new BadRequestException();
        }
        if(sensor == null){
            throw new SensorNotFoundException();
        }
        return dataRepository.findAllBySensor(sensor);
    }

    //GET All data by date
    @RequestMapping(params = "date", method = RequestMethod.GET)
    public List<Data> findDataByDate(@RequestParam String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException();
        }

        List<Data> data = dataRepository.findAllByDate(convertedCurrentDate);
        if(data.isEmpty()){
            throw new DataNotFoundException();
        }
        return data;
    }

    @RequestMapping(params = {"name","date"}, method = RequestMethod.GET)
    public List<Data> findDataBySensorAndDate(@RequestParam String name,@RequestParam String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(name.equals("")){
            throw new BadRequestException();
        }
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException();
        }
        Sensor sensor = sensorRepository.findByName(name);
        if(sensor == null){
            throw new SensorNotFoundException();
        }
        List<Data> data = dataRepository.findAllBySensorAndDate(sensor,convertedCurrentDate);
        if(data.isEmpty()){
            throw new DataNotFoundException();
        }
        return data;
    }

    @RequestMapping(params = {"id","date"}, method = RequestMethod.GET)
    public List<Data> findDataBySensorAndDate(@RequestParam Long id,@RequestParam String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(id == null){
            throw new BadRequestException();
        }
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException();
        }
        Sensor sensor = sensorRepository.findSensorById(id);
        if(sensor == null){
            throw new SensorNotFoundException();
        }
        List<Data> data = dataRepository.findAllBySensorAndDate(sensor,convertedCurrentDate);
        if(data.isEmpty()){
            throw new DataNotFoundException();
        }
        return data;
    }

    @RequestMapping (value = "/**", method = RequestMethod.GET)
    public void otherRequests(){
        throw new BadRequestException();
    }




}
