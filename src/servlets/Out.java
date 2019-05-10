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

public class Out extends HttpServlet {
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
        Integer nombreDeMenu=(Integer)session.getAttribute("nbMenus");
        Integer nombreDeJour=(Integer)session.getAttribute("nbJours");


        // Lecture du formulaire precedent et creation des contraintes
        med.readConstraints(req);
        req.setAttribute("nombreDeJour", nombreDeJour);



        //Soit t l'arraylist contenant l'ensemble des groupes et sous-groupes
        /*
        for(int i=0;i<nbSlotsPetitDej;i++){
            //for(String s:t){
            //String tofind=s+"_petitDej_"+i;
            //Integer group=Integer.parseInt(req.getParameter("nbSlotsPetitDej"))
            // med.transfertGroupes(group,s,0,i));  0 correspondant au petit-déj
        }
*/


        // ------------------------------------------------------------------------------------------------------------
        // Modification des inputs
        //med.setDayNumber();

        // ------------------------------------------------------------------------------------------------------------
        // Récupération du résultat


        String result = med.getResultat(req);
        req.setAttribute( "result", result );
        req.getSession().setAttribute( "resultat", result );
        // Integer.parseInt((String)req.getSession().getAttribute("nombreDeMenu"))
        //req.setAttribute( "dayNumber", med.setDayNumber);


        // ------------------------------------------------------------------------------------------------------------
        // Affichage de la page
        this.getServletContext().getRequestDispatcher( "/WEB-INF/out.jsp" ).forward( req, resp );
    }
}
