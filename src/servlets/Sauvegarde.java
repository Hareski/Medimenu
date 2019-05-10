package servlets;
import com.sun.xml.internal.ws.wsdl.writer.document.Import;
import medimenu.ImportCSV;
import medimenu.Medimenu;
import medimenu.Enregistrement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Sauvegarde extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Medimenu med=(Medimenu) req.getSession().getAttribute("chocoObject");
        if (med == null){
            med = new Medimenu();
        }

        if (req.getParameter("regime") != null) {
            med.setSauvegardeRegime(true);
            if (req.getParameter("nomRegime") != null && !req.getParameter("nomRegime").equals("")) {
                med.setNomRegime(req.getParameter("nomRegime"));
            } else {
                med.setNomRegime("regime");
            }
        }

        if (req.getParameter("menuNutritionniste") != null) {
            med.setSauvegardeMenuNutritionniste(true);
            if (req.getParameter("nomMenuNutritionniste") != null&& !req.getParameter("nomMenuNutritionniste").equals("")) {
                med.setNomMenuNutritionniste(req.getParameter("nomMenuNutritionniste"));
            } else {
                med.setNomMenuNutritionniste("menuNutritionniste");
            }

        }

        if (req.getParameter("menuUtilisateur") != null) {
            med.setSauvegardeMenuUtilisateur(true);
            if (req.getParameter("nomMenuUtilisateur") != null && !req.getParameter("nomMenuUtilisateur").equals("")) {
                med.setNomMenuUtilisateur(req.getParameter("nomMenuUtilisateur"));
            } else {
                med.setNomMenuUtilisateur("menuUtilisateur");
            }
        }

        if((Integer)req.getSession().getAttribute("nbJours") !=null && (Integer)req.getSession().getAttribute("nbSlots") !=null) {
            String contenu = Enregistrement.contenuSauvegardeRegime((Integer) req.getSession().getAttribute("nbJours"), (Integer) req.getSession().getAttribute("nbSlots"), med.getGroupesCoches(),med.getContraintesNutriments());
            String sauvegarde = med.sauvegarde(contenu);
            req.setAttribute("sauvegarde", sauvegarde);
        }
        req.setAttribute("detailsNutriments", med.getImprimableNutritioniste().replaceAll("(\r\n|\n)", "<br/>").replaceAll(" ", "&#160;"));
        this.getServletContext().getRequestDispatcher( "/WEB-INF/sauvegarde.jsp" ).forward( req, resp );
    }
}