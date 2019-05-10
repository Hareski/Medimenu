package servlets;
import medimenu.ImportCSV;
import medimenu.Medimenu;
import medimenu.Preconfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Base extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        this.getServletContext().getRequestDispatcher( "/WEB-INF/base.jsp" ).forward( req, resp );
    }
}
