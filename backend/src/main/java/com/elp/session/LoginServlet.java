package com.elp.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.elp.entity.Student;
import com.elp.entity.Trainer;
import com.elp.service.StudentService;
import com.elp.service.TrainerService;


/**
 * @author Rishi Jagariya
 *
 */
@WebServlet("/userlogin2")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	StudentService studentService;
  
	@Autowired
	TrainerService trainerService;
	
    public LoginServlet() { super(); }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	System.out.println("into do post");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String userType = request.getParameter("usertype");
    	
    	System.out.println(username + " " + password + " " + userType);

    	String message = null;
        if (userType.equals("trainer")) {
//        	//checkUser will check username and password in database
//        	message = trainerService.checkUser(username, password);
//        	
//        	switch(message) {
//        		case "success" : break;
//        		case "usernameIncorrect" : break;
//        		case "passwordIncorrect" : break;
//        		default : System.out.println(message);
//        	}


        	System.out.println("i'm here");

            Trainer trainer = trainerService.getTrainerByUsername(username);
            
            if (trainer == null) {
                message = "Username is incorrect";
                
            } else if (trainer.getPassword() == password) {
                message = "User logged in successfully";
                
            } else {
                message = "Password is incorrect";
            }
            
        	
        	HttpSession oldSession = request.getSession(false);
        	
        	if(oldSession != null) {
        		oldSession.invalidate();
        	}
        	HttpSession newSession = request.getSession(true);
        	
        	//TODO: save the session & token(JWT) to Cookies (client)
        	
        } else if (userType.equals("student")) {
        	
          	System.out.println("i'm here - student ");
            Student student = studentService.getStudentByUsername(username);
            
            if (student == null) {
                message = "Username is incorrect";
                
            } else if (student.getPassword().equals(password)) {
                message = "User logged in successfully";
                
            } else {
                message = "Password is incorrect";
            }
            
            HttpSession oldSession = request.getSession(false);
        	
        	if(oldSession != null) {
        		oldSession.invalidate();
        	}
        	
        	System.out.println("i'm here - 2 ");
        	HttpSession newSession = request.getSession(true);
        	System.out.println("i'm here - 3 ");
        }
    	
		doGet(request, response);
	}

}
