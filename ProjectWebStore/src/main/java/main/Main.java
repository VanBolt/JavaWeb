package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import Servlets.SignIn;
import Servlets.SignUp;
import accounts.AccountService;
import dbService.dataSets.UsersDataSet;

public class Main {

	public static void main(String[] args) throws Exception {

		AccountService accountService = new AccountService();

        accountService.addNewUser(new UsersDataSet("admin", "admin"));
        accountService.addNewUser(new UsersDataSet("test","test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignIn(accountService)), "/user");
        context.addServlet(new ServletHolder(new SignUp(accountService)), "/registration");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html_pages");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
	}

}
