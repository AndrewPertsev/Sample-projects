package by.pertsev.hotel.hiber.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = APARTMENTS)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"apartmentId"})
@Getter
@Setter
@Builder
public class Apartment extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = APARTMENTS_ID)
    private Integer apartmentId;

    @Column(name = APARTMENTS_CATEGORY_ID)
    private int category;

    @Column(name = APARTMENTS_CAPACITY)
    private int capacity;

    @Column(name = APARTMENTS_DESCRIPTION)
    private String description;

    @Column(name = APARTMENTS_PICTURE)
    private String pathToPicture;

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", category=" + category +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", pathToPicture='" + pathToPicture + '\'' +
                '}';
    }
}
