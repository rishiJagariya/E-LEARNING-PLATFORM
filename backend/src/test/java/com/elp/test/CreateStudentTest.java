package com.elp.test;

import static org.junit.Assert.assertNotNull;


import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.elp.controller.UserController;
import com.elp.entity.Student;
import com.elp.entity.User;
import com.elp.model.wrapper.ResponseMsgObject;

import junit.framework.Assert;

public class CreateStudentTest {
	
	    @Autowired
	    private CreateStudent createStudent;
	    @Test
	    public void CreateStudent() throws SQLException
	    {
	        boolean res=true;
	   CreateStudent cs=new CreateStudent();
	        boolean b=cs.CreateStudent("abc@gmail.com"," 123456");
	        assertEquals(res,b);
         }
	 private void assertEquals(boolean res, boolean b) {
			// TODO Auto-generated method stub
			
		}
	 
	 
	
	
	@Autowired
		private  User user;
		@Test
		@Rollback(false)

	 	public void testCreateUser() {
	 	UserController uc=new UserController();
	 		User user =new User(1,"student","rameshnaidu","ram@56678","ramesh","naidu","03-02-2021","9624267899");
			System.out.println(user);
			ResponseEntity<ResponseMsgObject> response=uc.createUser(user);
			assertNotNull(response);
	 		}
			
	 	
		}








		