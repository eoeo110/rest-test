/**
 * 
 */
package com.tuji.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tuji.demo.bean.User;
import com.tuji.demo.service.UserService;
import com.tuji.demo.util.Util;

/**
 * @author <a
 *         href="mailto:zhangtuo@statepower.net;wallellen@hotmail.com">Walle</a>
 * 
 *         2013��10��29��
 */
@Path("json")
@Consumes(MediaType.APPLICATION_JSON)
public class UserResourceJson {
	
	
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String getAllUsers() {
        	System.out.print("a1");
                return Util.toJson(UserService.getInstance().getUsers());
        }

        @GET
        @Path("{name}")
        @Produces(MediaType.APPLICATION_JSON)
        public String getUsersByNames(@PathParam("name") String name) {
        	System.out.print("a2");
                return Util.toJson(UserService.getInstance().getUsersByName(name));
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        public void createUser(String user) {
        	System.out.print(user);
               // return Util.toJson(UserService.getInstance().createUser(
               //                 (User) Util.fromJson(user)));
        }

        @PUT
        @Path("{user}")
        @Produces(MediaType.APPLICATION_JSON)
        public String updateUser(String user) {
        	System.out.print("a4");
                return Util.toJson(UserService.getInstance().updateUser(
                                (User) Util.fromJson(user)));
        }

        @DELETE
        @Path("{name}")
        @Produces(MediaType.APPLICATION_JSON)
        public String deleteUser(@PathParam("name") String name) {
        	System.out.print("a5");
                return Util.toJson(UserService.getInstance().deleteUser(
                                new User(name, "123")));
        }
}