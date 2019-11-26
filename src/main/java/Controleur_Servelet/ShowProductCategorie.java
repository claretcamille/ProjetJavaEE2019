/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur_Servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modele.DAO;
import Modele.DAOAdmin;
import Modele.DAOClient;
import Modele.DataSourceFactory;
import Modele.ProductEntity;
import java.util.LinkedList;

/**
 * @author Aymeric Prévost et Loic Leu
 */
@WebServlet(name = "ShowProductCategorie", urlPatterns = {"/ShowProductCategorie"})
public class ShowProductCategorie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ShowClientsInState</title>");
			out.println("</head>");
			out.println("<body>");
			try {
				// Créér le DAO avec sa source de données
				DAO dao = new DAO();
				List<ProductEntity> produits = dao.allProduct();
                                List<ProductEntity> categories = new LinkedList<>();
                                for(int i=0;i<produits.size();i++)
                                {
                                    if(!categories.contains(produits.get(i).getNom()))
                                    {
                                        categories.add(produits.get(i));
                                    }
                                }
				// Formulaire 
				out.println("<form action='ShowProductCategorie'>");
				out.println("<select name='categorie'>");

				for (ProductEntity s : categories) {
					// Mettre une catégorie dans la liste de choix			
					out.printf("<option value='%s'>%s</option>%n",
						s,
						s);
				}
				out.println("</select>");
				out.println("<input type='submit'>");
				out.println("</form>");
			} catch (Exception e) {
				out.printf("<h3>Erreur : %s</h3>", e.getMessage());
			}
			out.printf("<hr><a href='%s'>Retour au menu</a>",request.getContextPath());			
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
		}
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
