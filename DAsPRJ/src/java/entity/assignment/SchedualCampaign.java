/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.assignment;
import java.sql.Date;
/**
 *
 * @author A A
 */
public class SchedualCampaign {
    private int id;
    private Date date;
    private int shift;
    private int quantity;
    private PlanCampaign planCampain;

    public SchedualCampaign() {
    }

    public SchedualCampaign(int id, Date date, int shift, int quantity, PlanCampaign planCampain) {
        this.id = id;
        this.date = date;
        this.shift = shift;
        this.quantity = quantity;
        this.planCampain = planCampain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PlanCampaign getPlanCampain() {
        return planCampain;
    }

    public void setPlanCampain(PlanCampaign planCampain) {
        this.planCampain = planCampain;
    }
}
