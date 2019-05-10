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

public class NouvelleConfiguration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ------------------------------------------------------------------------------------------------------------
        // Recuperation de la session
        HttpSession session = req.getSession();
        Medimenu med=(Medimenu) session.getAttribute("chocoObject");
        if (med == null){
            this.getServletContext().getRequestDispatcher( "/WEB-INF/in.jsp" ).forward( req, resp );
            return;
        }

        Preconfiguration preconfiguration= new Preconfiguration("3","4");

        if (req.getParameter("configuration")!=null){
            String chemin="/medimenu/Regimes/"+req.getParameter("configuration");
            System.out.println(chemin);
            preconfiguration.setPreconfigurationFromAFile(chemin);
        }

        String nbJ_preconfig=preconfiguration.getNbJours();
        String nbS_preconfig=preconfiguration.getNbSlots();

        req.getSession().setAttribute("nbJ_preconfig",nbJ_preconfig);
        req.getSession().setAttribute("nbS_preconfig",nbS_preconfig);

        System.out.println("ATT "+req.getSession().getAttribute("nbS_preconfig"));
        req.getSession().setAttribute("preconfiguration",preconfiguration);

        // ------------------------------------------------------------------------------------------------------------
        // Affichage de la page
        this.getServletContext().getRequestDispatcher( "/WEB-INF/nouvelleConfiguration.jsp" ).forward( req, resp );
    }
}
