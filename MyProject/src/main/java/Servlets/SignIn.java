package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import accounts.AccountService;
import accounts.UserProfile;

public class SignIn extends HttpServlet {
	
	private final AccountService accountService;


	
	public SignIn(AccountService accountService) {
		this.accountService = accountService;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		String login = req.getParameter("login");
        String password = req.getParameter("password");
        

        if (login == null || password == null) {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null || !profile.getPassword().equals(password)) {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(req.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().println(json);
        res.setStatus(HttpServletResponse.SC_OK);
		
		
	}

}
