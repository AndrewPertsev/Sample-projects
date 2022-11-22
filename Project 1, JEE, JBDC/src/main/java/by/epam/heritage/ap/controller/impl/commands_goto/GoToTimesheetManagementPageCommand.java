package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_TIMESHEET_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GoToTimesheetManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToTimesheetManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            List<Timesheet> timesheets = ServiceFactory.getInstance().getTimesheetService().findAll();
            request.setAttribute(ATTRIBUTE_TIMESHEET, timesheets);

        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.getSession(true).setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_TIMESHEET_MANAGEMENT_PAGE);
        dispatcher.forward(request, response);

    }
}