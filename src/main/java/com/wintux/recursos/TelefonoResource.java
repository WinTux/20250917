package com.wintux.recursos;

import com.wintux.modelos.Telefono;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.Body;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/moviles")
public class TelefonoResource {
    List<Telefono> listaMoviles = new ArrayList<>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaMoviles(){
        return Response.ok(listaMoviles).build();
    }
    @GET
    @Path("/{id}")
    public Response getMovilById(@PathParam("id") int id){
        for(Telefono t : listaMoviles)
            if(t.getId() == id)
                return Response.ok(t).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearMovil(Telefono movil){
        listaMoviles.add(movil); // http://localhost:8080/moviles/10
        return Response.created(URI.create("/moviles/"+movil.getId())).build();
    }
    @PUT
    @Path("/{actual}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarMovil(@PathParam("actual") int id, Telefono movil){
        for(Telefono t : listaMoviles)
            if(t.getId() == id){
                t.setMarca(movil.getMarca());
                t.setNombre(movil.getNombre());
                t.setRam(movil.getRam());
                t.setAlmacenamiento_interno(movil.getAlmacenamiento_interno());
                return Response.ok(t).build();
            }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Path("{eliminable}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarMovil(@PathParam("eliminable") int id){
        for(Telefono t : listaMoviles)
            if(t.getId() == id){
                listaMoviles.remove(t);
                return Response.noContent().build();
            }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
