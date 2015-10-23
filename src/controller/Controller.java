package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.commands.Command;
import controller.commands.SearchCommand;
import controller.commands.ViewRoomsCommand;
import controller.commands.WelcomeCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns="/control", displayName="/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,Command> commands;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        commands = new HashMap<String,Command>();
       
        commands.put("search", new SearchCommand());
        commands.put("viewRooms", new ViewRoomsCommand());
        commands.put("welcome", new WelcomeCommand());
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response){
		Command cmd = resolveCommand(request);
		try {
			String next = cmd.execute(request, response);
			RequestDispatcher ds = getServletContext().getRequestDispatcher(next);
			ds.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Command resolveCommand(HttpServletRequest request){
		String action = request.getParameter("action");
		return (Command) commands.get(action);
	}

}
