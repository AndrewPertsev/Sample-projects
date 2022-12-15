package by.pertsev.hotel.hiber.model;

public class DBColumnLabel {
    private DBColumnLabel() {
    }

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String USERS = "users";
    public static final String USERS_ID = "user_id";
    public static final String USERS_ROLE_ID = "role_id";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_NAME = "user_name";
    public static final String USERS_SURNAME = "surname";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_TEL = "tel";
    public static final String USERS_PASSPORT = "passport_number";
    public static final String USERS_COUNTRY = "country";
    public static final String USERS_COMMENT = "comments";
    public static final String USERS_VIP = "vip_status";
    public static final String USERS_NON_GRATA = "nongrata_status";


    public static final String APARTMENTS = "apartments";
    public static final String APARTMENTS_ID = "apartment_id";
    public static final String APARTMENTS_CAPACITY = "capacity";
    public static final String APARTMENTS_PICTURE = "picture";
    public static final String APARTMENTS_DESCRIPTION = "description";
    public static final String APARTMENTS_CATEGORY_ID = "category_id";


    public static final String CATEGORY = "category_room";
    public static final String CATEGORY_ROOM_ID = "category_id";
    public static final String CATEGORY_ROOM = "category_name";
    public static final String CATEGORY_ROOM_PRICE = "category_price";


    public static final String OFFER = "offer";
    public static final String OFFER_ID = "offer_id";
    public static final String OFFER_REQUEST_ID = "request_id";

    public static final String OFFER_APARTMENT_ID = "apartment_id";
    public static final String OFFER_BOOKED_FROM = "booked_from";
    public static final String OFFER_BOOKED_BEFORE = "booked_before";

    public static final String OFFER_IS_SENT = "is_sent";
    public static final String OFFER_IS_PAID = "is_paid";
    public static final String OFFER_IS_CLOSED = "is_closed";
    public static final String OFFER_PRICE = "price_offer";
    public static final String OFFER_QUANTITY_PERSONS = "quantity_persons";


    public static final String REQUEST = "request";
    public static final String REQUEST_ID = "request_id";
    public static final String REQUEST_USER_ID = "user_id";

    public static final String REQUEST_QUANTITY = "quantity_persons";
    public static final String REQUEST_DATE_IN = "date_in";
    public static final String REQUEST_DATE_OUT = "date_out";
    public static final String REQUEST_DISTANCE = "distance";
    public static final String REQUEST_DATE_REQUEST = "date_request";
    public static final String REQUEST_CATEGORY_ID = "category_id";
    public static final String REQUEST_RESPONDED = "responded";


    public static final String TIMESHEET = "timesheet";
    public static final String TIMESHEET_ID = "id_timesheet";
    public static final String TIMESHEET_APARTMENT_ID = "apartment_id";
    public static final String TIMESHEET_RESERVED_DATE = "reserved_date";
    public static final String TIMESHEET_IS_RESERVED = "isreserved";


}
