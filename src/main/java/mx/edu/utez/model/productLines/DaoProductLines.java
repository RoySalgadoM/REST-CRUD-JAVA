package mx.edu.utez.model.productLines;

import mx.edu.utez.model.products.Products;
import mx.edu.utez.util.ConnectionMysql;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
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
        String query = "select productLine, textDescription,htmlDescription,image from productLines;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProductLines productLine = new ProductLines();
                productLine.setProductLine(rs.getString("productLine"));
                productLine.setTextDescription(rs.getString("textDescription"));
                productLine.setHtmlDescription(rs.getString("htmlDescription"));
                Blob blob = rs.getBlob("image");
                if(blob != null){
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    productLine.setBase64Image(base64Image);
                    System.out.println(base64Image);
                }
                productLines.add(productLine);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return productLines;
    }
    public ProductLines findProductLineById(String productLine) {
        String query = "select productLine, textDescription,htmlDescription,image from productLines where productLine = ?;";
        ProductLines productsLine = new ProductLines();
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,productLine);
            rs = pstm.executeQuery();
            while (rs.next()) {
                productsLine.setProductLine(rs.getString("productLine"));
                productsLine.setTextDescription(rs.getString("textDescription"));
                productsLine.setHtmlDescription(rs.getString("htmlDescription"));
                Blob blob = rs.getBlob("image");
                if(blob != null){
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    productsLine.setBase64Image(base64Image);
                }
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return productsLine;
    }
    public boolean deleteProductLine(String productLine){
        boolean state = false;
        String query = "delete from productLines where productLine = ?;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,productLine);
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
