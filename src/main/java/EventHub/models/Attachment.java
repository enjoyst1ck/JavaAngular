package EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Attachment implements IModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fileName;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    @JsonIgnore
    @JsonIgnoreProperties(value = {"attachments"})
    @ManyToOne(optional = true)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @JsonIgnore
    @JsonIgnoreProperties(value = {"attachments"})
    @ManyToOne(optional = true)
    @JoinColumn(name = "event_id")
    private Event event;
    @JsonIgnore
    @JsonIgnoreProperties(value = {"attachments"})
    @ManyToOne(optional = true)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
