package sk.kovalak.RESTapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SENSOR_DATA")
public class Data {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade= CascadeType.PERSIST)
    private Sensor sensor;

    private float value;
    @Temporal(TemporalType.DATE)
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
