package ir.moke.jsf.api;

import ir.moke.jsf.exception.InvalidDataException;
import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Produces({"application/json", "application/xml"})
@Stateless
public class UserAPI {

    @EJB
    private UserService userService;

    @GET
    @Path("/list")
    public List<User> list() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    public User find(@PathParam("id") long id) {
        User user = userService.find(id);
        if (user == null) {
            return null;
        } else
            return user;
    }

    @PUT
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response add(User user) throws InvalidDataException {
        if (!userService.isExist(user.getUsername())) {
            userService.save(user);
            return Response.ok().entity("Ok").build();
        } else {
            String response = String.format("User %s already exist\n", user.getUsername());
            return Response.status(Response.Status.CONFLICT).entity(response).build();
        }
    }
}