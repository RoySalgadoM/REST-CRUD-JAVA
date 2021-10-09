package mx.edu.utez.controller;
import mx.edu.utez.model.products.DaoProducts;
import mx.edu.utez.model.products.Products;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
@Path("/products")
public class ServiceProducts {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getProducts(){
        return new DaoProducts().findAll();
    }

    @GET
    @Path("/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getProductsById(@PathParam("productCode") String productCode){
        return new DaoProducts().findProductById(productCode);
    }
    @POST
    @Path("/save/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products updateProducts(MultivaluedMap<String, String> formParams,@PathParam("productCode") String productCode){
        if(new DaoProducts().products(false,getParams(productCode,formParams),productCode)){
            return new DaoProducts().findProductById(productCode);
        }
        return null;
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products createProducts(MultivaluedMap<String, String> formParams){
        String productCode = formParams.get("productCode").get(0);
        if(new DaoProducts().products(true,getParams(productCode,formParams),productCode)){
            return new DaoProducts().findProductById(productCode);
        }
        return null;
    }
    @POST
    @Path("/delete/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> createCustomer(@PathParam("productCode") String productCode){
        if(new DaoProducts().deleteProduct(productCode)){
            return new DaoProducts().findAll();
        }
        return null;
    }
    private Products getParams(String productCode, MultivaluedMap<String, String> formParams){
        int quantityInStock  = Integer.parseInt(formParams.get("quantityInStock").get(0));
        double buyPrice = Double.parseDouble(formParams.get("buyPrice").get(0));
        double msrp = Double.parseDouble(formParams.get("msrp").get(0));
        Products products = new Products(productCode,formParams.get("productName").get(0),formParams.get("productLine").get(0),formParams.get("productScale").get(0),formParams.get("productVendor").get(0),formParams.get("productDescription").get(0),quantityInStock,buyPrice,msrp);
        return products;
    }
}
