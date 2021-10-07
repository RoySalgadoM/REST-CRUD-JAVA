package mx.edu.utez.model.products;

import mx.edu.utez.util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProducts {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    public boolean products(boolean isCreate, Products products, String productCode){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into products(productCode, productName,productLine,productScale,productVendor,productDescription,quantityInStock,buyPrice,MSRP)" +
                        " values(?,?,?,?,?,?,?,?,?); ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,products.getProductCode());
                pstm.setString(2,products.getProductName());
                pstm.setString(3,products.getProductLine());
                pstm.setString(4,products.getProductScale());
                pstm.setString(5,products.getProductVendor());
                pstm.setString(6,products.getProductDescription());
                pstm.setInt(7,products.getQuantityInStock());
                pstm.setDouble(8,products.getBuyPrice());
                pstm.setDouble(9,products.getMsrp());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update products set productName=?,productLine=?,productScale=?,productVendor=?,productDescription=?,quantityInStock=?,buyPrice=?,MSRP=?" +
                        " where productCode=?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1,products.getProductName());
                pstm.setString(2,products.getProductLine());
                pstm.setString(3,products.getProductScale());
                pstm.setString(4,products.getProductVendor());
                pstm.setString(5,products.getProductDescription());
                pstm.setInt(6,products.getQuantityInStock());
                pstm.setDouble(7,products.getBuyPrice());
                pstm.setDouble(8,products.getMsrp());
                pstm.setString(9,products.getProductCode());
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<Products> findAll() {
        List<Products> products = new ArrayList<>();
        String query = "select productCode, productName,productLine,productScale,productVendor,productDescription,quantityInStock,buyPrice,MSRP from products;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setProductCode(rs.getString("productCode"));
                product.setProductName(rs.getString("productName"));
                product.setProductLine(rs.getString("productLine"));
                product.setProductScale(rs.getString("productScale"));
                product.setProductVendor(rs.getString("productVendor"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setQuantityInStock(rs.getInt("quantityInStock"));
                product.setBuyPrice(rs.getDouble("buyPrice"));
                product.setMsrp(rs.getDouble("MSRP"));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
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
