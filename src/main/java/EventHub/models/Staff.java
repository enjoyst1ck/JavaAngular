package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staff implements IModel {
    @TableGenerator(
            name = "staffGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="staffGenerator")
    private Integer id;
    private String name;
    @JsonIgnoreProperties(value = {"staff"})
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    //lista organizowanych eventow
    @JsonIgnore
    @JsonIgnoreProperties(value = {"staff"})
    @ManyToMany(mappedBy = "staff")
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
