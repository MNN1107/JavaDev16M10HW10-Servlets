package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.ZoneOffset.UTC;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String timezone = request.getParameter("timezone");

        ZoneId zoneId;
        if(timezone != null && !timezone.isEmpty()){
            zoneId =  ZoneId.of(timezone);
        }else {
            zoneId =  ZoneId.of("UTC");
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        LocalDateTime currentTime = zonedDateTime.toLocalDateTime();

        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        String htmlResponse = "<html><body>";
        htmlResponse += "<h2>Current Time</h2>";
        htmlResponse += "<p>" + formattedTime + "</p>";
        htmlResponse += "</body></html>";

        response.getWriter().write(htmlResponse);
    }

}





