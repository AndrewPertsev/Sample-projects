package by.pertsev.hotel.hiber.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = CATEGORY)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"categoryId"})
@Builder
public class Category extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CATEGORY_ROOM_ID)
    private Integer categoryId;

    @Column(name = CATEGORY_ROOM)
    private String categoryName;

    @Column(name = CATEGORY_ROOM_PRICE)
    private BigDecimal categoryPrice;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryPrice=" + categoryPrice +
                '}';
    }
}
