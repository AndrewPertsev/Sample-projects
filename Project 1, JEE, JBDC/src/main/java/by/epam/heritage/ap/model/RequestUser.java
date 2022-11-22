package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RequestUser extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int requestId;
    private int userId;
    private int quantity;
    private int category;
    private int menu;
    private int transfer;
    private int distance;
    private LocalDate start;
    private LocalDate end;
    private LocalDate dateRequest;
    private boolean isResponded;

    public RequestUser() {
    }

    public RequestUser(int requestId, int userId, int quantity, int category, int menu, int transfer, int distance, LocalDate start, LocalDate end, LocalDate dateRequest, boolean isResponded) {
        this.requestId = requestId;
        this.userId = userId;
        this.quantity = quantity;
        this.category = category;
        this.menu = menu;
        this.transfer = transfer;
        this.distance = distance;
        this.start = start;
        this.end = end;
        this.dateRequest = dateRequest;
        this.isResponded = isResponded;
    }


    public void setResponded(boolean responded) {
        isResponded = responded;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
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

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public boolean isResponded() {
        return isResponded;
    }
    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestUser requestUser = (RequestUser) o;
        return requestId == requestUser.requestId && userId == requestUser.userId && quantity == requestUser.quantity && category == requestUser.category && menu == requestUser.menu && transfer == requestUser.transfer && distance == requestUser.distance && isResponded == requestUser.isResponded && start.equals(requestUser.start) && end.equals(requestUser.end) && dateRequest.equals(requestUser.dateRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, userId, quantity, category, menu, transfer, distance, start, end, dateRequest, isResponded);
    }

    @Override
    public String toString() {
        return "RequestUser{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", category=" + category +
                ", menuId=" + menu +
                ", transferId=" + transfer +
                ", distance=" + distance +
                ", dateIn=" + start +
                ", dateOut=" + end +
                ", dateRequest=" + dateRequest +
                ", isResponded=" + isResponded +
                '}';
    }
}
