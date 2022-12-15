package by.pertsev.hotel.hiber.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = OFFER)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"offerId"})
@Builder
public class Offer extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = OFFER_ID)
    private Integer offerId;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = OFFER_REQUEST_ID)
    private RequestUser requestUser;


    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = OFFER_APARTMENT_ID)
    private Apartment apartment;

    @Column(name = OFFER_QUANTITY_PERSONS)
    private int quantity;

    @Column(name = OFFER_BOOKED_FROM)
    private LocalDate start;

    @Column(name = OFFER_BOOKED_BEFORE)
    private LocalDate end;

    @Column(name = OFFER_IS_SENT)
    private boolean isSent;

    @Column(name = OFFER_IS_PAID)
    private boolean isPaid;

    @Column(name = OFFER_IS_CLOSED)
    private boolean isClosed;

    @Column(name = OFFER_PRICE)
    private BigDecimal priceOffer;


    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", requestUser=" + requestUser +
                ", apartment=" + apartment +
                ", quantity=" + quantity +
                ", start=" + start +
                ", end=" + end +
                ", isSent=" + isSent +
                ", isPaid=" + isPaid +
                ", isClosed=" + isClosed +
                ", priceOffer=" + priceOffer +
                '}';
    }
}