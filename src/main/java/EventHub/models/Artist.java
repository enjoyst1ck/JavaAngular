package EventHub.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist implements IModel {
    @TableGenerator(
            name = "artistGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="artistGenerator")
    private Integer id;
    private String name;

    @JsonIgnoreProperties(value = {"artist"})
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    //mozna wyswietlic strone artysty i zobaczyc w jakich wydarzeniach bral udział
    @JsonIgnoreProperties(value = {"artists"})
    @ManyToMany(mappedBy = "artists")
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
