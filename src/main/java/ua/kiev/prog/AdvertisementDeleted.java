package ua.kiev.prog;

import javax.persistence.*;

@Entity
@Table(name = "Advs_del")
public class AdvertisementDeleted {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "long_desc")
    private String longDesc;

    private String phone;
    private double price;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private PhotoDeleted photo;

    public AdvertisementDeleted() {}

    public AdvertisementDeleted(Advertisement advertisement, PhotoDeleted photo) {
        name = advertisement.getName();
        shortDesc = advertisement.getShortDesc();
        longDesc = advertisement.getLongDesc();
        phone = advertisement.getPhone();
        price = advertisement.getPrice();
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PhotoDeleted getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDeleted photo) {
        this.photo = photo;
    }
}
