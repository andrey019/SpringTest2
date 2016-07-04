package ua.kiev.prog;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "Advs")
@XmlAccessorType(XmlAccessType.NONE)
public class Advertisement {
    @Id
    @GeneratedValue
    @XmlAttribute
    private Long id;

    @XmlElement
    private String name;

    @Column(name = "short_desc")
    @XmlElement
    private String shortDesc;

    @Column(name = "long_desc")
    @XmlElement
    private String longDesc;

    @XmlElement
    private String phone;

    @XmlElement
    private double price;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public Advertisement() {}

    public Advertisement(String name, String shortDesc, String longDesc, String phone, double price, Photo photo) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.phone = phone;
        this.price = price;
        this.photo = photo;
    }

    public Advertisement(AdvertisementDeleted advertisementDeleted, Photo photo){
        name = advertisementDeleted.getName();
        shortDesc = advertisementDeleted.getShortDesc();
        longDesc = advertisementDeleted.getLongDesc();
        phone = advertisementDeleted.getPhone();
        price = advertisementDeleted.getPrice();
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

