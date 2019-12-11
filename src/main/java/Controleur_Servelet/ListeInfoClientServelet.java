/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur_Servelet;

import Modele.DAOClient;
import Modele.DataSourceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camilleclaret
 */
@WebServlet(name = "ListeInfoClientServelet", urlPatterns = {"/ListeInfoClientServelet"})
public class ListeInfoClientServelet extends HttpServlet {

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
        Cookie ck[]=request.getCookies();
        String clientCode=ck[0].getValue();
        String action = request.getParameter("action");
        Properties resultat = new Properties();
     
            
        
			
        try{
            DAOClient daoC = new DAOClient(DataSourceFactory.getDataSource(), clientCode);
            resultat.put("records", daoC.getClient());
            if (null != action) {
                switch(action){
                    case "ModifAdresse":
                        this.changeAdresse(request, daoC);
                        break;
                    case "ModifContact":
                        this.changeContact(request, daoC);
                        break;
                } 
            }
        } catch(SQLException ex){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resultat.put("records", Collections.EMPTY_LIST);
            resultat.put("message", ex.getMessage());
        }
        
        try (PrintWriter out = response.getWriter()) {
            // On spécifie que la servlet va générer du JSON
            response.setContentType("application/json;charset=UTF-8");
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
            out.println(gson.toJson(resultat));
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

 
    private void changeAdresse(HttpServletRequest request, DAOClient daoC) throws SQLException {
       String[] val = {"adresse","ville","code_postal","pays"};
       String adresse = request.getParameter("adresse");
       String ville = request.getParameter("ville");
       String pays = request.getParameter("pays");
       String codePostal = request.getParameter("code");
       String[] valSaisie = {adresse,ville, codePostal, pays};
       for(int i = 0; i < val.length;i++){
           daoC.modifInfoClient(val[i], valSaisie[i]);
       }
       
    }

    private void changeContact(HttpServletRequest request, DAOClient daoC) throws SQLException {
       String[] val = {"contact","fonction","telephone","fax"};
       String contact = request.getParameter("contact");
       String fonc = request.getParameter("fonction");
       String tel = request.getParameter("tel");
       String faxe = request.getParameter("faxe");
       String[] valSaisie = {contact,fonc, tel, faxe};
       for(int i = 0; i < val.length;i++){
           daoC.modifInfoClient(val[i], valSaisie[i]);
       }
    }

   

}
