package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	// TODO Auto-generated method stub
	request.getSession().invalidate();
	return "/welcome.jsp";
    }

}
