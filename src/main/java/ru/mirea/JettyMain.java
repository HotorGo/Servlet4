package ru.mirea;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyMain {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();
        handler.setResourceBase(".");
        server.setHandler(handler);
        handler.addServlet(DefaultServlet.class, "/");
        handler.addServlet(TodoServlet.class, "/todo.html");
        server.start();
    }
}
