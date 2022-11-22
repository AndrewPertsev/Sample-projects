package by.epam.heritage.ap.controller;

/**
 * Names of request parameters and attributes.
 */
public final class ConstantsParametersAndAttributes {

    private ConstantsParametersAndAttributes() {
    }

    public static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    public static final String ATTRIBUTE_MESSAGE_FAIL = "message_fail";
    public static final String ATTRIBUTE_EXCEPTION = "att_exception";
    public static final String MESSAGE_DATABASE_ERROR = "Database error.";
    public static final String MESSAGE_EMAIL_ERROR = "Sending message error.";
    public static final String MESSAGE_INVALID_DATA = "Invalid data.";
    public static final String MESSAGE_SUCCESS_ADD_APARTMENT = "Successfully added new apartment.";
    public static final String MESSAGE_SUCCESS_DELETE_APARTMENT = "Successfully deleted apartment.";
    public static final String MESSAGE_SUCCESS_DELETE_REQUEST = "Successfully deleted request.";
    public static final String MESSAGE_SUCCESS_LOGIN = "Welcome!";
    public static final String MESSAGE_SUCCESS_PUSH_TO_USER = "Successfully pushed to the user.";
    public static final String MESSAGE_SUCCESS_REGISTRATION = "Congratulations! Successfully registered.";
    public static final String MESSAGE_SUCCESS_REQUEST = "Thank you! Check your email tomorrow!";
    public static final String MESSAGE_SUCCESS_UPDATE_APARTMENT = "Successfully updated apartment data.";
    public static final String MESSAGE_SUCCESS_UPDATE_GUEST = "Successfully updated guest data.";
    public static final String MESSAGE_SUCCESS_UPDATE_OFFER_STATUS = "Successfully updated offer status.";
    public static final String MESSAGE_SUCCESS_UPDATE_REQUEST = "Successfully updated user's request.";
    public static final String MESSAGE_FAIL = "Sorry, your data is not valid , please try again.";
    public static final String MESSAGE_FAIL_ADD_APARTMENT = "Sorry, unsuccessfully added new apartment.";
    public static final String MESSAGE_FAIL_DELETE_APARTMENT = "Sorry, unsuccessfully deleted apartment.";
    public static final String MESSAGE_FAIL_DELETE_REQUEST = "Sorry, unsuccessfully deleted request.";
    public static final String MESSAGE_FAIL_LOGIN = "Sorry, your data is not valid , please try again.";
    public static final String MESSAGE_FAIL_PUSH_TO_USER = "Sorry, unsuccessfully pushed to the user.";
    public static final String MESSAGE_FAIL_REGISTRATION = "Sorry, unsuccessfully registered!";
    public static final String MESSAGE_FAIL_REQUEST = "Sorry, your data is not valid , please try again.";
    public static final String MESSAGE_FAIL_UPDATE_APARTMENT = "Sorry, unsuccessfully updated apartment data.";
    public static final String MESSAGE_FAIL_UPDATE_GUEST = "Sorry, unsuccessfully updated guest data.";
    public static final String MESSAGE_FAIL_UPDATE_OFFER_STATUS = "Sorry, unsuccessfully updated offer status.";
    public static final String MESSAGE_FAIL_UPDATE_REQUEST = "Sorry, unsuccessfully updated user's request.";

    public static final String SESSION_ATTRIBUTE_GUEST_ID = "userId";
    public static final String SESSION_ATTRIBUTE_GUEST_ROLE_ID = "userRoleId";
    public static final String SESSION_ATTRIBUTE_GUEST_NAME = "userName";
    public static final String SESSION_ATTRIBUTE_GUEST_SUR_NAME = "userSurName";
    public static final String SESSION_ATTRIBUTE_GUEST_EMAIL = "userEmail";
    public static final String SESSION_ATTRIBUTE_GUEST_PHONE = "userPhone";
    public static final String SESSION_ATTRIBUTE_GUEST_VIP = "userVIP";

    public static final String PARAMETER_APARTMENT_ID = "apartmentId";
    public static final String PARAMETER_CAPACITY = "capacity";
    public static final String PARAMETER_CATEGORY = "category";
    public static final String PARAMETER_CHECK_IN_DATE = "start";
    public static final String PARAMETER_CHECK_OUT_DATE = "end";
    public static final String PARAMETER_COMMENT = "comment";
    public static final String PARAMETER_COMMAND = "command";
    public static final String PARAMETER_COUNTRY = "country";
    public static final String PARAMETER_DESCRIPTION = "description";
    public static final String PARAMETER_GUEST_ID = "guestId";
    public static final String PARAMETER_GUEST_IDEQ = "&guestId=";
    public static final String PARAMETER_GUEST_IS_VIP = "isVip";
    public static final String PARAMETER_GUEST_IS_NON_GRATA = "isNonGrata";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_FILTER_ACTIVE = "active";
    public static final String PARAMETER_LOCALE_COMMON = "locale";
    public static final String PARAMETER_LOCALE_DEFAULT_EN = "en_EN";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_MENU = "menu";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_PASSPORT_NUMBER = "passport";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_PASSWORD2 = "password2";
    public static final String PARAMETER_PATH_TO_PICTURE = "pathToPicture";
    public static final String PARAMETER_PHONE = "phone";
    public static final String PARAMETER_QUANTITY = "quantity";
    public static final String PARAMETER_REQUEST_ENCODING = "requestEncoding";
    public static final String PARAMETER_REQUEST_ID = "requestId";
    public static final String PARAMETER_ROLE_ID = "roleId";
    public static final String PARAMETER_SURNAME = "surname";
    public static final String PARAMETER_TRANSFER = "transfer";
    public static final String PARAMETER_USER_ID = "userId";
    public static final String PARAMETER_OFFER_ID = "offerId";
    public static final String PARAMETER_OFFER_PRICE = "priceOffer";
    public static final String PARAMETER_OFFER_PAID = "paid";
    public static final String PARAMETER_OFFER_SENT = "sent";
    public static final String PARAMETER_OFFER_CLOSED = "closed";

    public static final String ATTRIBUTE_OFFERS = "ATTRIBUTE_OFFERS";
    public static final String ATTRIBUTE_REQUESTS = "ATTRIBUTE_REQUESTS";
    public static final String ATTRIBUTE_GUEST_LIST = "ATTRIBUTE_GUEST_LIST";
    public static final String ATTRIBUTE_ROOM_LIST = "ATTRIBUTE_ROOM_LIST";
    public static final String ATTRIBUTE_TIMESHEET = "ATTRIBUTE_TIMESHEET_LIST";
    public static final String ATTRIBUTE_USER_OFFERS = "ATTRIBUTE_USER_OFFERS";
    public static final String ATTRIBUTE_FIND_UNRESPONDED_REQUESTS = "find_unresponded";
    public static final String DEFAULT_COMMENT = " ";
    public static final String ENCODING_UTF8 = "utf-8";
    public static final int DEFAULT_DISTANCE = 10;
    public static final int DEFAULT_ROLE_ID = 1;
    public static final boolean DEFAULT_IS_OFFER_CLOSED = false;
    public static final boolean DEFAULT_IS_OFFER_PAID = false;
    public static final boolean DEFAULT_IS_OFFER_SENT = true;
    public static final boolean DEFAULT_IS_RESPONDED = false;
    public static final boolean DEFAULT_NONGRATA_STATUS = false;
    public static final boolean DEFAULT_VIP_STATUS = false;
    public static final boolean IS_RESERVED_APARTMENT_TRUE = true;


    public final static String TEXT_MAIL_SUBJECT = "Order confirmation";
    public final static String TEXT_MAIL_MESSAGE = "Your request has been confirmed. Thank you!";
    public final static String DEFAULT_ADDRESS_FOR_ERRORS = "xmessage@gmail.com";
}
