package com.semillero.controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Server;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HolaMundo extends HttpServlet{

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)

        throws ServletException, IOException {
            System.out.println("hola mundo");
        }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            System.out.println(" hola mundo");


    }
}
    
    

