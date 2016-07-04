package ua.kiev.prog;


import javax.persistence.*;

@Entity
@Table(name="Photos_del")
public class PhotoDeleted {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Lob
    private byte[] body;

    public PhotoDeleted() {}

    public PhotoDeleted(Photo photo) {
        name = photo.getName();
        body = photo.getBody();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
