package by.pertsev.hotel.hiber.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = REQUEST)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"requestId"})
@Builder
public class RequestUser extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int DEFAULT_DISTANCE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = REQUEST_ID)
    private Integer requestId;

    @ManyToOne(optional = false)
    @JoinColumn(name = REQUEST_USER_ID)
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToOne(optional = false)                         // for onetomany::, cascade = {CascadeType.MERGE}
    @JoinColumn(name = REQUEST_CATEGORY_ID)
    private Category category;

    @Column(name = REQUEST_QUANTITY)
    private int quantity;

    @Column(name = REQUEST_DISTANCE)
    private int distance = DEFAULT_DISTANCE;

    @Column(name = REQUEST_DATE_IN)
    private LocalDate start;

    @Column(name = REQUEST_DATE_OUT)
    private LocalDate end;

    @Column(name = REQUEST_DATE_REQUEST)
    private LocalDate dateRequest;

    @Column(name = REQUEST_RESPONDED)
    private boolean isResponded;


    @Override
    public String toString() {
        return "RequestUser{" +
                "requestId=" + requestId +
                ", user=" + user +
                ", category=" + category +
                ", quantity=" + quantity +
                ", distance=" + distance +
                ", start=" + start +
                ", end=" + end +
                ", dateRequest=" + dateRequest +
                ", isResponded=" + isResponded +
                '}';
    }
}
