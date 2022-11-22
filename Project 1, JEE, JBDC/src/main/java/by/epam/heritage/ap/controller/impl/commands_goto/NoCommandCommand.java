package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_HOME_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_MESSAGE_FAIL;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_INVALID_DATA;

public class NoCommandCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(NoCommandCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(true).setAttribute(ATTRIBUTE_MESSAGE_FAIL,  MESSAGE_INVALID_DATA);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_HOME_PAGE);
        logger.debug("NO Command");

        dispatcher.forward(request, response);



    }
}
