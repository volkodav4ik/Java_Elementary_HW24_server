package com.volkodav4ik.api;

import com.volkodav4ik.dao.UserDao;
import com.volkodav4ik.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserToDbApi {

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String json) {
        User user = UserDao.fromJson(json);
        User savedUser = UserDao.addUser(user);
        if (savedUser != null && !savedUser.getName().equals("")) {
            String resultJson = "{\"result\": \"Added User with id: " + savedUser.getId() + "\"}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"Failed to add user\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @POST
    @Path("/delete/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserByName(String json) {
        String name = UserDao.fromJson(json).getName();
        User user = UserDao.getUserByName(name);
        if (user != null) {
            UserDao.removeUserByName(name);
            String resultJson = "{\"result\": \"User with name " + name + " was deleted\"}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"Failed to delete user\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @POST
    @Path("/delete/id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(String json) {
        int id = UserDao.fromJson(json).getId();
        User user = UserDao.getUser(id);
        if (user != null) {
            UserDao.removeUser(id);
            String resultJson = "{\"result\": \"User with id = " + id + " was deleted\"}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"Failed to delete user\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String json) {
        User recivedUser = UserDao.fromJson(json);
        if (UserDao.getUser(recivedUser.getId()) != null) {
            UserDao.updateUser(recivedUser);
            String resultJson = "{\"result\": \"User with id = " + recivedUser.getId() + " was updated\"}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"Failed to update user\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @POST
    @Path("/get/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(String json) {
        String name = UserDao.fromJson(json).getName();
        User user = UserDao.getUserByName(name);
        if (user != null) {
            String resultJson = UserDao.toJson(user);
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"There is no User with name :" + name + "\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @POST
    @Path("/get/id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(String json) {
        int id = UserDao.fromJson(json).getId();
        User user = UserDao.getUser(id);
        if (user != null) {
            String resultJson = UserDao.toJson(user);
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"There is no User with ID:" + id + "\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        List<User> list = UserDao.getAllUsers();
        if (!list.isEmpty()) {
            String resultJson = UserDao.listToJson(list);
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"List of users is empty\"}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        }
    }

}
