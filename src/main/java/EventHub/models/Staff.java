package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staff extends BaseEntity implements IModel {
    @JsonIgnoreProperties(value = {"staff"})
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    //lista organizowanych eventow
    @JsonIgnore
    @JsonIgnoreProperties(value = {"staff"})
    @ManyToMany(mappedBy = "staff")
    private List<Event> events;

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
