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

public class Groupes extends HttpServlet {
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
            preconfiguration = new Preconfiguration("7","4");
        }


        // ------------------
        //Sauvegarde nombre de jours et menus
        int nombredeJour=Integer.parseInt(req.getParameter("nbJours"));
        int timeout=Integer.parseInt(req.getParameter("timeout"));
        int nombreDeSlots=Integer.parseInt(req.getParameter("nbSlots"));
        med.setNbJours(nombredeJour);
        med.setTimeout(timeout);
        med.setNbSlots(nombreDeSlots);
        req.getSession().setAttribute("nbJours", nombredeJour);
        req.getSession().setAttribute("timeout", timeout);
        req.getSession().setAttribute("nbSlots", nombreDeSlots);

        // -----------------------------------------------------------------------------------------------------------
        // Prise en compte des input
        /*
        int nbSlotsPetitDej=Integer.parseInt(req.getParameter("nbSlotsPetitDej"));
        int nbSlotsDej=Integer.parseInt(req.getParameter("nbSlotsDej"));
        int nbSlotsGouter =Integer.parseInt(req.getParameter("nbSlotsGouter"));
        int nbSlotsDiner=Integer.parseInt(req.getParameter("nbSlotsDiner"));

        med.setNbSlotsPetitDej(nbSlotsPetitDej);
        med.setNbSlotsDej(nbSlotsDej);
        med.setNbSlotsGouter(nbSlotsGouter);
        med.setNbSlotsDiner(nbSlotsDiner);

        //Sauvegarde de ces valeurs
        req.getSession().setAttribute("nbSlotsPetitDej",nbSlotsPetitDej);
        req.getSession().setAttribute("nbSlotsDej",nbSlotsDej);
        req.getSession().setAttribute("nbSlotsGouter",nbSlotsGouter);
        req.getSession().setAttribute("nbSlotsDiner",nbSlotsDiner);

        String groupsHTML=ImportCSV.groups(2808, med.getNbSlotsPetitDej(), med.getNbSlotsDej(), med.getNbSlotsGouter(), med.getNbSlotsDiner(),med);
    */

        int nbSlots=Integer.parseInt(req.getParameter("nbSlots"));

        med.setNbSlots(nbSlots);

        //Sauvegarde de ces valeurs
        req.getSession().setAttribute("nbSlots",nbSlots);

        String groupsHTML=ImportCSV.groups(2808, med.getNbSlots(), med.getNbSlots(), med.getNbSlots(), med.getNbSlots(),med,preconfiguration);




        if(groupsHTML!=null){
            req.setAttribute("groupes",groupsHTML);
        }
        else{
            req.setAttribute("groupes","");

        }

        // ------------------------------------------------------------------------------------------------------------
        // PRENDRE EN COMPTE NUTRIMENTS


        // ------------------------------------------------------------------------------------------------------------
        // Affichage de la page
        this.getServletContext().getRequestDispatcher( "/WEB-INF/groupes.jsp" ).forward( req, resp );
    }
}
