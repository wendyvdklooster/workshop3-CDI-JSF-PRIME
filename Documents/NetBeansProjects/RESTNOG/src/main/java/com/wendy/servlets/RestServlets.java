/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * see http://software.danielwatrous.com/restful-java-servlet/
 *
 * @author Wendy
 */
@WebServlet(name = "RestServlets", loadOnStartup = 1, urlPatterns = {"/registratieKlant", "/login", "/artikelbeheer"})
public class RestServlets extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        // Send to artikelen 
        if (userPath.equals("/artikelbeheer")) {
            userPath = "/medewerker/artikelen";
        }
        
        // Send to overzicht accountgegeven
        else if (userPath.equals ("/registratieKlant")){
            userPath = "/klant/accountgegevens";
        }
        
        
         // Use RequestDispatcher to forward request internally
        String url = "/WEB-INF" + userPath + ".html";

        getRequestDispatcherAndForwardInternally(request, response, url);
        
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // code 
    }

    
    public void getRequestDispatcherAndForwardInternally(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (ServletException ex) {
            ex.printStackTrace();
        }
}
    
    
    private class RestRequest {

        // Accommodate two requests, one for all resources, another for a specific resource
        private Pattern regExAllPattern = Pattern.compile("/resource");
        private Pattern regExIdPattern = Pattern.compile("/resource/([0-9]*)");

        private Integer id;

        public RestRequest(String pathInfo) throws ServletException {
            // regex parse pathInfo
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                return;
            }

            throw new ServletException("Invalid URI");
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}
