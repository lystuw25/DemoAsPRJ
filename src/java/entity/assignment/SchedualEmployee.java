/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.assignment;

import entity.accesscontrol.Employee;

/**
 *
 * @author A A
 */
public class SchedualEmployee {
    private int id;
    private Employee employee;
    private Attendence attendence;
    private SchedualCampaign sc;
    private int quantity;

    public SchedualEmployee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Attendence getAttendence() {
        return attendence;
    }

    public void setAttendence(Attendence attendence) {
        this.attendence = attendence;
    }

    public SchedualCampaign getSc() {
        return sc;
    }

    public void setSc(SchedualCampaign sc) {
        this.sc = sc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
