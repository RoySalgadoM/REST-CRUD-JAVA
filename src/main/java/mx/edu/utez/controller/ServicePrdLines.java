package mx.edu.utez.controller;
import mx.edu.utez.model.productLines.DaoProductLines;
import mx.edu.utez.model.productLines.ProductLines;
import mx.edu.utez.model.products.DaoProducts;
import mx.edu.utez.model.products.Products;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
@Path("/productLines")
public class ServicePrdLines {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductLines> getProductsLines(){
        return new DaoProductLines().findAll();
    }

    @GET
    @Path("/{productLine}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductLines getProductsById(@PathParam("productLine") String productLine){
        return new DaoProductLines().findProductLineById(productLine);
    }
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLines createProducts(MultivaluedMap<String, String> formParams){
        String productLine = formParams.get("productLine").get(0);
        if(new DaoProductLines().productsLines(true,getParams(productLine,formParams),productLine)){
            return new DaoProductLines().findProductLineById(productLine);
        }
        return null;
    }

    @POST
    @Path("/save/{productLine}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLines updateProducts(MultivaluedMap<String, String> formParams,@PathParam("productLine") String productLine){
        if(new DaoProductLines().productsLines(false,getParams(productLine,formParams),productLine)){
            return new DaoProductLines().findProductLineById(productLine);
        }
        return null;
    }
    @POST
    @Path("/delete/{productLine}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductLines> deleteProductLine(@PathParam("productLine") String productLine){
        System.out.println('1');
        if(new DaoProductLines().deleteProductLine(productLine)){
            return new DaoProductLines().findAll();
        }
        return null;
    }
    private ProductLines getParams(String productLine, MultivaluedMap<String, String> formParams){
        ProductLines productLines = new ProductLines(productLine,formParams.get("textDescription").get(0),formParams.get("htmlDescription").get(0),null,formParams.get("image").get(0));
        return productLines;
    }
}
