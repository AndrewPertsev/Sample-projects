package by.epam.heritage.ap.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.controller.filter.Role.*;
import static java.lang.Boolean.TRUE;

public class AccessCommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AccessCommandFilter.class);
    private final Map<String, Role> commandAccess = new HashMap<>();
    private FilterConfig filterConfig;

    public AccessCommandFilter() {
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;

        commandAccess.put(LOG_IN, ANONYMOUS);
        commandAccess.put(REGISTRATION, ANONYMOUS);
        commandAccess.put(NO_COMMAND, ANONYMOUS);
        commandAccess.put(GO_TO_HOME_PAGE, ANONYMOUS);
        commandAccess.put(GO_TO_LOGIN_PAGE, ANONYMOUS);
        commandAccess.put(GO_TO_GALLERY_PAGE, ANONYMOUS);
        commandAccess.put(GO_TO_REGISTRATION_PAGE, ANONYMOUS);
        commandAccess.put(GO_TO_MAIN_PAGE, GUEST);
        commandAccess.put(REQUEST, GUEST);
        commandAccess.put(CLOSE_GUEST_SESSION, GUEST);
        commandAccess.put(GO_TO_REQUEST_PAGE, GUEST);
        commandAccess.put(GO_TO_GUEST_ROOM_PAGE, GUEST);
        commandAccess.put(OFFER, MANAGER);
        commandAccess.put(SEND_EMAIL, MANAGER);
        commandAccess.put(UPDATE_GUEST, MANAGER);
        commandAccess.put(DELETE_REQUEST, MANAGER);
        commandAccess.put(ADD_APARTMENT, MANAGER);
        commandAccess.put(UPDATE_REQUEST, MANAGER);
        commandAccess.put(SHOW_GUEST_DATA, MANAGER);
        commandAccess.put(DELETE_APARTMENT, MANAGER);
        commandAccess.put(UPDATE_APARTMENT, MANAGER);
        commandAccess.put(PUSH_OFFER_TO_USER, MANAGER);
        commandAccess.put(UPDATE_OFFER_STATUS, MANAGER);
        commandAccess.put(GO_TO_OFFER_PROJECT_PAGE, MANAGER);
        commandAccess.put(GO_TO_OFFER_MANAGEMENT_PAGE, MANAGER);
        commandAccess.put(GO_TO_GUEST_MANAGEMENT_PAGE, MANAGER);
        commandAccess.put(GO_TO_REQUEST_MANAGEMENT_PAGE, MANAGER);
        commandAccess.put(GO_TO_TIMESHEET_MANAGEMENT_PAGE, MANAGER);
        commandAccess.put(GO_TO_APARTMENT_MANAGEMENT_PAGE, MANAGER);
        commandAccess.put(FIND_REQUESTS_UNRESPONDED_STATUS_ONLY, MANAGER);

    }

    @Override
    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestServlet;
        HttpServletResponse response = (HttpServletResponse) responseServlet;
        HttpSession session = request.getSession(true);

        if (filterConfig.getInitParameter(PARAMETER_FILTER_ACTIVE).equalsIgnoreCase(TRUE.toString())) {
            String command = request.getParameter(PARAMETER_COMMAND);
            Role commandAccessRole = commandAccess.get(command);


            if (command == null) {
                filterChain.doFilter(requestServlet, responseServlet);
                LOGGER.debug("Null command in the access filter");

            } else {

                if (command != null & commandAccessRole == null) {
                    response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_LOGIN_PAGE);
                } else {
                    String guestRoleId = (String) session.getAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID);
                    if (guestRoleId == null || guestRoleId.isEmpty()) {
                        guestRoleId = String.valueOf(ANONYMOUS.ordinal());
                        LOGGER.debug("Set default access level in the access filter");

                    }
                    int roleInt = Integer.parseInt(guestRoleId);
                    if (commandAccessRole.ordinal() <= roleInt) {
                        filterChain.doFilter(requestServlet, responseServlet);

                    } else {
                        response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_LOGIN_PAGE);
                    }
                }
            }
        } else {
            filterChain.doFilter(requestServlet, responseServlet);
        }

    }
}

