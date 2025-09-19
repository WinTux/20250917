package com.wintux.recursos;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Telefono") // http://localhost:8080/Telefono
public class TelefonoRecurso {
    List<String> listaTelefonos = new ArrayList<>();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getListaTelefonos(){
        return  Response.ok(listaTelefonos).build();
    }
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response agregarTelefono(String telf){
        listaTelefonos.add(telf);
        return Response.created(URI.create("/Telefono/"+telf)).build();
    }
    @PUT
    @Path("/{actual}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN) //http://localhost:8080/Telefono/Sony?nombre=Sonia
    public Response modificarTelefono(@PathParam("actual") String actual, @QueryParam("nombre") String nuevo){
        listaTelefonos = listaTelefonos.stream().map(t -> {
            if(t.equals(actual)){return nuevo;}
            else{return t;}
        }).collect(Collectors.toList());
        return Response.ok(listaTelefonos).build();
    }
    @DELETE
    @Path("{eliminable}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarTelefono(@PathParam("eliminable") String nombre){
        if(listaTelefonos.remove(nombre))
            return Response.ok(listaTelefonos).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
