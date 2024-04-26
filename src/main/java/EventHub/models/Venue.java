package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Venue implements IModel {
    @TableGenerator(
            name = "venueGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="venueGenerator")
    private Integer id;
    private String name;
    private String description;
    private String city;
    private String address;
    @JsonIgnoreProperties(value = {"venue"})
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Event> events;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
