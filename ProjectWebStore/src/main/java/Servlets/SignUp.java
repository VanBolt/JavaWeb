package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import accounts.AccountService;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

public class SignUp extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final AccountService accountService;

    public SignUp(AccountService accountService) {
        this.accountService = accountService;
    }


	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		String login = req.getParameter("login");
        String password = req.getParameter("password");
        
        DBService service = new DBService();
        UsersDataSet dataSet = new UsersDataSet();
       /* try {
			dataSet = service.getUser(login);
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/


        accountService.addNewUser(new UsersDataSet(login,password));
        
        if(login!=null && password!=null){
        try {
			service.addUser(login, password);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
        }
        
        if (login == null || password == null) {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        dataSet= accountService.getUserByLogin(login);
        if (dataSet == null || !dataSet.getPassword().equals(password)) {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(req.getSession().getId(), dataSet);
        Gson gson = new Gson();
        String json = gson.toJson(dataSet);
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().println(json);
        res.setStatus(HttpServletResponse.SC_OK);
		
		
	}

}
