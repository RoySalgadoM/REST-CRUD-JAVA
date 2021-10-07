package mx.edu.utez.model;

import mx.edu.utez.util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCustomer {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean customer(boolean isCreate,Customer customer,int customerNumber){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit)" +
                        " values(?,?,?,?,?,?,?,?,?,?,?,?,?); ";
                pstm = con.prepareStatement(query);
                pstm.setInt(1,customer.getCustomerNumber());
                pstm.setString(2,customer.getCustomerName());
                pstm.setString(3,customer.getContactLastName());
                pstm.setString(4,customer.getContactFirstName());
                pstm.setString(5,customer.getPhone());
                pstm.setString(6,customer.getAddressLine1());
                pstm.setString(7,customer.getAddressLine2());
                pstm.setString(8,customer.getCity());
                pstm.setString(9,customer.getState());
                pstm.setString(10,customer.getPostalCode());
                pstm.setString(11,customer.getCountry());
                pstm.setInt(12,customer.getSalesRepEmployeeNumber());
                pstm.setDouble(13,customer.getCreditLimit());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update customers set customerName=?,contactLastName=?,contactFirstName=?,phone=?,addressLine1=?,addressLine2=?,city=?,state=?,postalCode=?,country=?,salesRepEmployeeNumber=?,creditLimit=?" +
                        " where customerNumber=?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1,customer.getCustomerName());
                pstm.setString(2,customer.getContactLastName());
                pstm.setString(3,customer.getContactFirstName());
                pstm.setString(4,customer.getPhone());
                pstm.setString(5,customer.getAddressLine1());
                pstm.setString(6,customer.getAddressLine2());
                pstm.setString(7,customer.getCity());
                pstm.setString(8,customer.getState());
                pstm.setString(9,customer.getPostalCode());
                pstm.setString(10,customer.getCountry());
                pstm.setInt(11,customer.getSalesRepEmployeeNumber());
                pstm.setDouble(12,customer.getCreditLimit());
                pstm.setInt(13,customerNumber);
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<>();
        String query = "select customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit from customers;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomerNumber(rs.getInt("customerNumber"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setContactLastName(rs.getString("contactLastName"));
                customer.setContactFirstName(rs.getString("contactFirstName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddressLine1(rs.getString("addressLine1"));
                customer.setAddressLine2(rs.getString("addressLine2"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setPostalCode(rs.getString("postalCode"));
                customer.setCountry(rs.getString("country"));
                customer.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(rs.getDouble("creditLimit"));
                customers.add(customer);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return customers;
    }

    public Customer findCustomerById(int customerNumber){
        String query = "select customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit from customers where customerNumber=?;";
        Customer customer = new Customer();
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setInt(1,customerNumber);
            rs = pstm.executeQuery();
            if (rs.next()){
                customer.setCustomerNumber(rs.getInt("customerNumber"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setContactLastName(rs.getString("contactLastName"));
                customer.setContactFirstName(rs.getString("contactFirstName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddressLine1(rs.getString("addressLine1"));
                customer.setAddressLine2(rs.getString("addressLine2"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setPostalCode(rs.getString("postalCode"));
                customer.setCountry(rs.getString("country"));
                customer.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(rs.getDouble("creditLimit"));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return customer;
    }
    public boolean deleteCustomer(int costumerNumber){
        boolean state = false;
        String query = "delete from customers where customerNumber = ?;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setInt(1,costumerNumber);
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
