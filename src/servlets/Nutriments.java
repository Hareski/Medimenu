package servlets;
import medimenu.ImportCSV;
import medimenu.Medimenu;
import medimenu.Preconfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Nutriments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperation de la session
        HttpSession session = req.getSession();
        Medimenu med=(Medimenu) session.getAttribute("chocoObject");
        if (med == null){
            this.getServletContext().getRequestDispatcher( "/WEB-INF/in.jsp" ).forward( req, resp );
            return;
        }


        Preconfiguration preconfiguration=(Preconfiguration) req.getSession().getAttribute("preconfiguration");
        if (preconfiguration == null){
            preconfiguration = new Preconfiguration("3","4");
        }


        // Lecture du formulaire precedent et initialisation des variables
        String nutrimentsHTML= ImportCSV.getColumnsHTML(68,med,preconfiguration);
        ImportCSV.importCSV(68,med); //NBCOL
        med.initialisationVariablesChoco(req);


        if(nutrimentsHTML!=null) {
            req.setAttribute("nutriments", nutrimentsHTML);
        }
        else{
            req.setAttribute("nutriments","");
        }

        // ------------------------------------------------------------------------------------------------------------
        // Affichage de la page
        this.getServletContext().getRequestDispatcher( "/WEB-INF/nutriments.jsp" ).forward( req, resp );
    }
}
