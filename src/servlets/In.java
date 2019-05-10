package servlets;
import medimenu.ImportCSV;
import medimenu.Medimenu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class In extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Création de l'objet
        HttpSession session = req.getSession();
        Medimenu med = new Medimenu();
        session.setAttribute("chocoObject", med);

        // ------------------------------------------------------------------------------------------------------------
        // Affichage de la page
        this.getServletContext().getRequestDispatcher( "/WEB-INF/in.jsp" ).forward( req, resp );
    }
}
