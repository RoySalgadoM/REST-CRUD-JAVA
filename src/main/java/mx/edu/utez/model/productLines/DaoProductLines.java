package mx.edu.utez.model.productLines;

import mx.edu.utez.model.products.Products;
import mx.edu.utez.util.ConnectionMysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProductLines {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    public boolean productsLines(boolean isCreate, ProductLines productLines, String productLine){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into productLines(productLine,textDescription,htmlDescription,image )" +
                        " values(?,?,?,?); ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,productLines.getProductLine());
                pstm.setString(2,productLines.getTextDescription());
                pstm.setString(3,productLines.getHtmlDescription());
                pstm.setBlob(4,productLines.getBinary().getInputStream());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update productLines set textDescription=?,htmlDescription=?,image=?" +
                        " where productLine=?;";
                pstm = con.prepareStatement(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1,productLines.getTextDescription());
                pstm.setString(2,productLines.getHtmlDescription());
                pstm.setBlob(3,productLines.getBinary().getInputStream());
                pstm.setString(4,productLines.getProductLine());
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException | IOException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<ProductLines> findAll() {
        List<ProductLines> productLines = new ArrayList<>();
        String query = "select productLine, textDescription,htmlDescription,image from products;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProductLines productLine = new ProductLines();
                productLine.setProductLine(rs.getString("productLine"));
                productLine.setTextDescription(rs.getString("textDescription"));
                productLine.setHtmlDescription(rs.getString("htmlDescription"));

                productLines.add(productLine);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return productLines;
    }
    public Products findProductById(String productCode) {
        String query = "select productCode, productName,productLine,productScale,productVendor,productDescription,quantityInStock,buyPrice,MSRP from products where productCode = ?;";
        Products product= new Products();
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,productCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                product.setProductCode(rs.getString("productCode"));
                product.setProductName(rs.getString("productName"));
                product.setProductLine(rs.getString("productLine"));
                product.setProductScale(rs.getString("productScale"));
                product.setProductVendor(rs.getString("productVendor"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setQuantityInStock(rs.getInt("quantityInStock"));
                product.setBuyPrice(rs.getDouble("buyPrice"));
                product.setMsrp(rs.getDouble("MSRP"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return product;
    }
    public boolean deleteProduct(String productCode){
        boolean state = false;
        String query = "delete from products where productCode = ?;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,productCode);
            state = pstm.executeUpdate()==1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
