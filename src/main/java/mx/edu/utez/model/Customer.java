package mx.edu.utez.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlElement
    private String addressLine1;
    @XmlElement
    private String addressLine2;
    @XmlElement
    private String city;
    @XmlElement
    private String contactFirstName;
    @XmlElement
    private String contactLastName;
    @XmlElement
    private String country;
    @XmlElement
    private double creditLimit;
    @XmlElement
    private String customerName;
    @XmlElement
    private int customerNumber;
    @XmlElement
    private String phone;
    @XmlElement
    private String postalCode;
    @XmlElement
    private int salesRepEmployeeNumber;
    @XmlElement
    private String state;

    public Customer(String addressLine1, String addressLine2, String city, String contactFirstName, String contactLastName, String country, double creditLimit, String customerName, int customerNumber, String phone, String postalCode, int salesRepEmployeeNumber, String state) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.country = country;
        this.creditLimit = creditLimit;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.phone = phone;
        this.postalCode = postalCode;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.state = state;
    }

    public Customer() {
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
