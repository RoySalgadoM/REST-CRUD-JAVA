package mx.edu.utez.controller;
import mx.edu.utez.model.Customer;
import mx.edu.utez.model.DaoCustomer;
import mx.edu.utez.model.offices.DaoOffices;
import mx.edu.utez.model.offices.Offices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
@Path("/offices")
public class ServiceOffices {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offices> getOffices(){
        return new DaoOffices().findAll();
    }

    @GET
    @Path("/{officeCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Offices getOfficeById(@PathParam("officeCode") String officeCode){
        return new DaoOffices().findOfficeById(officeCode);
    }
    @POST
    @Path("/save/{officeCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Offices updateOffices(MultivaluedMap<String, String> formParams,@PathParam("officeCode") String officeCode){
        if(new DaoOffices().offices(false,getParams(officeCode,formParams),officeCode)){
            return new DaoOffices().findOfficeById(officeCode);
        }
        return null;
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Offices createOffices(MultivaluedMap<String, String> formParams){
        String officeCode = formParams.get("officeCode").get(0);
        if(new DaoOffices().offices(true,getParams(officeCode,formParams),officeCode)){
            return new DaoOffices().findOfficeById(officeCode);
        }
        return null;
    }
    @POST
    @Path("/delete/{officeCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offices> deleteOffice(@PathParam("officeCode") String officeCode){
        if(new DaoOffices().deleteOffice(officeCode)){
            return new DaoOffices().findAll();
        }
        return null;
    }
    private Offices getParams(String officeCode, MultivaluedMap<String, String> formParams){
        Offices offices = new Offices(officeCode,formParams.get("city").get(0),formParams.get("phone").get(0),formParams.get("addressLine1").get(0),formParams.get("addressLine2").get(0),formParams.get("state").get(0),formParams.get("country").get(0),formParams.get("postalCode").get(0),formParams.get("territory").get(0));
        return offices;
    }
}
