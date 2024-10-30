package entity;
import entity.accesscontrol.User;
import entity.assignment.Department;
import java.sql.*;
public class Employee {
    private int id;
    private String name;
    private boolean gender;
    private String address;
    private double salary;
    private Department dept;
    private Date dob;
    private User createdby;
    private User updatedby;
    private java.util.Date updatedtime;

    public User getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(User updatedby) {
        this.updatedby = updatedby;
    }

    public java.util.Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(java.util.Date updatedtime) {
        this.updatedtime = updatedtime;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }
    
    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
}
