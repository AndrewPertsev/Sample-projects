package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Offer extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int offerId;
    private int quantity;
    private int requestId;
    private int apartmentId;
    private int managerId;
    private int transfer;
    private int menu;
    private LocalDate start;
    private LocalDate end;
    private LocalDate dateSent;
    private LocalDate datePaid;
    private LocalDate dateClosed;
    private boolean isSent;
    private boolean isPaid;
    private boolean isClosed;
    private BigDecimal priceOffer;


    public Offer() {
    }

    public Offer(int offerId, int quantity, int requestId, int apartmentId, int managerId, int transfer, int menu, LocalDate start, LocalDate end, LocalDate dateSent, LocalDate datePaid, LocalDate dateClosed, boolean isSent, boolean isPaid, boolean isClosed, BigDecimal priceOffer) {
        this.offerId = offerId;
        this.quantity = quantity;
        this.requestId = requestId;
        this.apartmentId = apartmentId;
        this.managerId = managerId;
        this.transfer = transfer;
        this.menu = menu;
        this.start = start;
        this.end = end;
        this.dateSent = dateSent;
        this.datePaid = datePaid;
        this.dateClosed = dateClosed;
        this.isSent = isSent;
        this.isPaid = isPaid;
        this.isClosed = isClosed;
        this.priceOffer = priceOffer;
    }

    public Offer(int offerId, boolean isSent, boolean isPaid, boolean isClosed, BigDecimal priceOffer) {
        this.offerId = offerId;
        this.isSent = isSent;
        this.isPaid = isPaid;
        this.isClosed = isClosed;
        this.priceOffer = priceOffer;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPriceOffer() {
        return priceOffer;
    }
    public void setPriceOffer(BigDecimal priceOffer) {
        this.priceOffer = priceOffer;
    }
    public int getOfferId() {
        return offerId;
    }
    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }
    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }
    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }
    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    public boolean isClosed() {
        return isClosed;
    }
    public void setClosed(boolean closed) {
        isClosed = closed;
    }
    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()){return false;}
        Offer offer = (Offer) o;
        return offerId == offer.offerId && quantity == offer.quantity && requestId == offer.requestId && apartmentId == offer.apartmentId && managerId == offer.managerId && transfer == offer.transfer && menu == offer.menu && isSent == offer.isSent && isPaid == offer.isPaid && isClosed == offer.isClosed && start.equals(offer.start) && end.equals(offer.end) && dateSent.equals(offer.dateSent) && datePaid.equals(offer.datePaid) && dateClosed.equals(offer.dateClosed) && priceOffer.equals(offer.priceOffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, quantity, requestId, apartmentId, managerId, transfer, menu, start, end, dateSent, datePaid, dateClosed, isSent, isPaid, isClosed, priceOffer);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", quantity=" + quantity +
                ", requestId=" + requestId +
                ", apartment=" + apartmentId +
                ", managerId=" + managerId +
                ", transfer=" + transfer +
                ", menu=" + menu +
                ", start=" + start +
                ", end=" + end +
                ", dateSent=" + dateSent +
                ", datePaid=" + datePaid +
                ", dateClosed=" + dateClosed +
                ", isSent=" + isSent +
                ", isPaid=" + isPaid +
                ", isClosed=" + isClosed +
                ", priceOffer=" + priceOffer +
                '}';
    }
}