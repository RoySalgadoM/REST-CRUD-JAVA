package mx.edu.utez.model.offices;

import mx.edu.utez.model.Customer;
import mx.edu.utez.util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoOffices {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    public boolean offices(boolean isCreate, Offices offices, String officeCode){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into offices(officeCode, city,phone,addressLine1,addressLine2,state,country,postalCode,territory)" +
                        " values(?,?,?,?,?,?,?,?,?); ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,offices.getOfficeCode());
                pstm.setString(2,offices.getCity());
                pstm.setString(3,offices.getPhone());
                pstm.setString(4,offices.getAddressLine1());
                pstm.setString(5,offices.getAddressLine2());
                pstm.setString(6,offices.getState());
                pstm.setString(7,offices.getCountry());
                pstm.setString(8,offices.getPostalCode());
                pstm.setString(9,offices.getTerritory());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update offices set city=?,phone=?,addressLine1=?,addressLine2=?,state=?,country=?,postalCode=?,territory=?" +
                        " where officeCode=?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1,offices.getCity());
                pstm.setString(2,offices.getPhone());
                pstm.setString(3,offices.getAddressLine1());
                pstm.setString(4,offices.getAddressLine2());
                pstm.setString(5,offices.getState());
                pstm.setString(6,offices.getCountry());
                pstm.setString(7,offices.getPostalCode());
                pstm.setString(8,offices.getTerritory());
                pstm.setString(9,offices.getOfficeCode());
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<Offices> findAll() {
        List<Offices> offices = new ArrayList<>();
        String query = "select officeCode, city,phone,addressLine1,addressLine2,state,country,postalCode,territory from offices;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Offices office = new Offices();
                office.setOfficeCode(rs.getString("officeCode"));
                office.setCity(rs.getString("city"));
                office.setPhone(rs.getString("phone"));
                office.setAddressLine1(rs.getString("addressLine1"));
                office.setAddressLine2(rs.getString("addressLine2"));
                office.setState(rs.getString("state"));
                office.setCountry(rs.getString("country"));
                office.setPostalCode(rs.getString("postalCode"));
                office.setTerritory(rs.getString("territory"));
                offices.add(office);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return offices;
    }
    public Offices findOfficeById(String officeCode) {
        String query = "select officeCode, city,phone,addressLine1,addressLine2,state,country,postalCode,territory from offices where officeCode = ?;";
        Offices office = new Offices();
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,officeCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                office.setOfficeCode(rs.getString("officeCode"));
                office.setCity(rs.getString("city"));
                office.setPhone(rs.getString("phone"));
                office.setAddressLine1(rs.getString("addressLine1"));
                office.setAddressLine2(rs.getString("addressLine2"));
                office.setState(rs.getString("state"));
                office.setCountry(rs.getString("country"));
                office.setPostalCode(rs.getString("postalCode"));
                office.setTerritory(rs.getString("territory"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return office;
    }
    public boolean deleteOffice(String officeCode){
        boolean state = false;
        String query = "delete from offices where officeCode = ?;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setString(1,officeCode);
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
