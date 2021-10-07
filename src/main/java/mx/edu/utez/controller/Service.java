package mx.edu.utez.controller;

import mx.edu.utez.model.Customer;
import mx.edu.utez.model.DaoCustomer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
@Path("/customer")
public class Service {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(){
        return new DaoCustomer().findAll();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomersById(@PathParam("id") int customerNumber){
        return new DaoCustomer().findCustomerById(customerNumber);
    }
    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customer updateCustomer(MultivaluedMap<String, String> formParams,@PathParam("id") int customerNumber){
        if(new DaoCustomer().customer(false,getParams(customerNumber,formParams),customerNumber)){
            return new DaoCustomer().findCustomerById(customerNumber);
        }
        return null;
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customer createCustomer(MultivaluedMap<String, String> formParams){
        int customerNumber = Integer.parseInt(formParams.get("customerNumber").get(0));
        if(new DaoCustomer().customer(true,getParams(customerNumber,formParams),customerNumber)){
            return new DaoCustomer().findCustomerById(customerNumber);
        }
        return null;
    }
    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> deleteCustomer(@PathParam("id") int customerNumber){
        if(new DaoCustomer().deleteCustomer(customerNumber)){
            return new DaoCustomer().findAll();
        }
        return null;
    }

    private Customer getParams(int customerNumber, MultivaluedMap<String, String> formParams){
        double creditLimit = Double.parseDouble(formParams.get("creditLimit").get(0));
        int salesRepEmployeeNumber = Integer.parseInt(formParams.get("salesRepEmployeeNumber").get(0));
        Customer customer = new Customer(formParams.get("addressLine1").get(0),formParams.get("addressLine2").get(0),formParams.get("city").get(0),formParams.get("contactFirstName").get(0),formParams.get("contactLastName").get(0),formParams.get("country").get(0),creditLimit,formParams.get("customerName").get(0),customerNumber,formParams.get("phone").get(0),formParams.get("postalCode").get(0),salesRepEmployeeNumber,formParams.get("state").get(0));
        return customer;
    }

}
