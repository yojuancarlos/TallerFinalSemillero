package com.semillero;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.semillero.controlador.HolaMundo;


public class App 
{
    public static void main( String[] args )
    {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8081);
        server.setHandler(new DefaultHandler());

        ServletContextHandler context = new ServletContextHandler();

        context.setContextPath("/");
        //context.addServlet(HolaMundo.class, "/hola/*");
        //context.addServlet(PersonaController.class, "/persona/*");

        server.setHandler(context);

        try{
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

