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
}
