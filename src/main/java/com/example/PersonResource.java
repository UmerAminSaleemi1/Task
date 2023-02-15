package com.example;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService service;

    @GET
    @Path("listWithName")
    public List<Person> listWithName() {
        return service.getAll();
    }

    @GET
    @Path("ById/{id}")
    public Person getById(@PathParam("id") String id) {
        return service.getById(Long.parseLong(id));
    }

    @GET
    @Path("ByLiveLocation")
    public List<Person> byLiveLocation() {
        return service.getByLiveLocation();
    }
}