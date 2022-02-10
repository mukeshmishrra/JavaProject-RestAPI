package com.mukesh.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserResources {

//get request
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getAllUsers() {
		UserDao dao = new UserDao();
		return dao.getAllUsers();
	}
	
//Post Request
	@POST                                        //creating a resource
	@Consumes({MediaType.APPLICATION_JSON})
	public Users createUser(Users u) {
		 UserDao dao = new UserDao();
		 //System.out.println(u);
		 dao.saveData(u);
		 return u;
	}
	
//update request
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Users updateUserData(Users u) {
		UserDao dao = new UserDao();
		dao.updateUser(u);
		return u;
	}
	
	
//delete request
	@DELETE
	@Path("users/{id}")
	public void deleteUser(@PathParam("id") int id) {
		UserDao dao = new UserDao();
//		Users user = dao.getUserById(id);
//		if(user.getId()!=0) {
//			dao.deleteUser(id);
//			System.out.println("user deleted successfully!!");
//		}else {
//			System.out.println("invalid user id !! deletion failed");
//		}
//		
		
	//	return user;

	dao.deleteUser(id);

	}

}
