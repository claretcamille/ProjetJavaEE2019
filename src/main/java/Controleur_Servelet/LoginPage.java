/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur_Servelet;

import Modele.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import Modele.DAO;
import Modele.DAOClient;
import Modele.ClientEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

/**
 *
 * @author Aymeric Prévost
 */
@WebServlet(name = "LoginPage", urlPatterns = {"/LoginPage"})
public class LoginPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		String action = request.getParameter("action");
		if (null != action)
                {
                    switch (action)
                    {
                        case "login":
                            checkLogin(request);
                            break;
                            
                        case "logout":
                            doLogout(request);
                            break;
                    }
		}

		// Est-ce que l'utilisateur est connecté ?
		// On cherche l'attribut userName dans la session
		String userName = findUserInSession(request);
		String jspView;
		if (null == userName)
                {
                        // L'utilisateur n'est pas connecté
			// On choisit la page de login
			jspView = "login.jsp";
		}
                else if(userName.equals("admin") == true)
                {
                    jspView = "FirstPageAdmin.html";
                    Cookie ck=new Cookie("code",userName);  
                    response.addCookie(ck);  
                }
                else
                { 
                    jspView = "FirstPageClient.html?";
                    Cookie ck=new Cookie("code",userName);  
                    response.addCookie(ck); 
                }
		// On va vers la page choisie
		request.getRequestDispatcher(jspView).forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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

	private void checkLogin(HttpServletRequest request) throws SQLException {
		// Les paramètres transmis dans la requête
		String loginParam = request.getParameter("loginParam");
		String passwordParam = request.getParameter("passwordParam");
                
                if(loginParam.equals("admin") && passwordParam.equals("admin"))
                {
                    HttpSession session = request.getSession(true); // démarre la session
                    session.setAttribute("userName", "admin");
                }
                else
                {
                    try{
                        // Le login/password défini dans web.xml
                        DAO dao = new DAO(DataSourceFactory.getDataSource());
                        DAOClient daoC = dao.toConnectClient(loginParam, passwordParam);
                        ClientEntity Clients = daoC.getClient().get(0);
                        String login = Clients.getContactClient();
                        String password = Clients.getCodeClient();
                        String userName = Clients.getCodeClient();
                
                        if ((login.equals(loginParam) && (password.equals(passwordParam))))
                        {
                            // On a trouvé la combinaison login / password
                            // On stocke l'information dans la session
                            HttpSession session = request.getSession(true); // démarre la session
                            session.setAttribute("userName", userName);
                        }
                    }
                    catch(NullPointerException e)
                    {
                        // On positionne un message d'erreur pour l'afficher dans la JSP
                        request.setAttribute("errorMessage", "Login / Password incorrect ! Veuillez essayer de nouveau");
                    }
                }
        }

	private void doLogout(HttpServletRequest request) {
		// On termine la session
		HttpSession session = request.getSession(false);
		if (session != null)
                {
                    session.invalidate();
		}
	}

	private String findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
                if(session == null)
                {
                    return null;
                }
                else if("admin".equals((String) session.getAttribute("userName")) == true)
                {
                    return "admin";
                }
                else
                {
                    return (String) session.getAttribute("userName");
                }
		//return (session == null) ? null : (String) session.getAttribute("userName");
	}
}