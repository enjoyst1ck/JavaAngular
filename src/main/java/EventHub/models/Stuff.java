package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stuff implements IModel {
    @TableGenerator(
            name = "stuffGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="stuffGenerator")
    private Integer id;
    private String name;
    @JsonIgnoreProperties(value = {"stuff"})
    @OneToMany(mappedBy = "stuff", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    //lista organizowanych eventow
    @JsonIgnore
    @JsonIgnoreProperties(value = {"stuff"})
    @ManyToMany(mappedBy = "stuff")
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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
