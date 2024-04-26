package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Review implements IModel {
    @TableGenerator(
            name = "reviewGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="reviewGenerator")
    private Integer id;
    private Date date;
    private String comment;
    @JsonIgnoreProperties(value = {"reviews"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userFrom;
    @JsonIgnoreProperties(value = {"reviews"})
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
