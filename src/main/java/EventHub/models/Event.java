package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Event implements IModel {
    @TableGenerator(
            name = "eventGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy=GenerationType.TABLE,
            generator="eventGenerator")
    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    @JsonIgnoreProperties(value = {"event"})
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    @JsonIgnore
    @JsonIgnoreProperties(value = {"event"})
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @JsonIgnoreProperties(value = {"events"})
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    @JsonIgnoreProperties(value = {"events"})
    @ManyToMany
    @JoinTable(name = "eventArtist",
               joinColumns = {@JoinColumn(name = "id_event")},
               inverseJoinColumns = {@JoinColumn(name = "id_artist")})
    private List<Artist> artists;

    @JsonIgnoreProperties(value = {"events"})
    @ManyToMany
    @JoinTable(name = "eventOrganizer",
               joinColumns = {@JoinColumn(name = "id_event")},
               inverseJoinColumns = {@JoinColumn(name = "id_staff")})
    private List<Staff> staff;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
}
