package by.epam.heritage.ap.controller;

import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_COMMAND;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final ProviderCommand providerCommand = new ProviderCommand();


    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().init();
        } catch (PoolException e) {
            logger.error("Connection pool does not initialized ", e);
            throw new RuntimeException("Connection pool does not initialized ", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().clearConnectionQueue();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            process(request, response);
        } catch (IOException e) {
            logger.error("Controller exception ", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            process(request, response);
        } catch (IOException e) {
            logger.error("Controller exception ", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Commandable command = providerCommand.getCommands(commandName);
        try {
            command.execute(request, response);
        } catch (IOException | ServletException | ServiceException e) {
            logger.error("Controller exception ", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);


        }
    }}


