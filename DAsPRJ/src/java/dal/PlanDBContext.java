package dal;
import dal.DBContext;
import entity.Employee;
import entity.assignment.Attendence;
import entity.assignment.Department;
import entity.assignment.Plan;
import entity.assignment.PlanCampaign;
import entity.assignment.Product;
import entity.assignment.SchedualCampaign;
import entity.assignment.SchedualEmployee;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.List;

public class PlanDBContext extends DBContext{
    public ArrayList<PlanCampaign> getDetailPlans() {
        ArrayList<PlanCampaign> plans = new ArrayList<>();
        String sql = "SELECT pc.PlanCampnID, pc.PlanID, pc.Quantity, pc.Estimate, "
                + "p.PlanName, p.StartDate, p.EndDate, pro.ProductID, pro.ProductName, "
                + "d.DepartmentID, d.DepartmentName, d.type "
                + "FROM [PlanCampain] pc "
                + "JOIN [Plan] p ON p.PlanID = pc.PlanID "
                + "JOIN Product pro ON pro.ProductID = pc.ProductID "
                + "JOIN Department d ON d.DepartmentID = p.DepartmentID";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plan p = new Plan();
                p.setId(rs.getInt("PlanID"));
                p.setName(rs.getString("PlanName"));
                p.setStart(rs.getDate("StartDate"));
                p.setEnd(rs.getDate("EndDate"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                d.setType(rs.getString("type"));
                p.setDept(d);

                Product pro = new Product();
                pro.setId(rs.getInt("ProductID"));
                pro.setName(rs.getString("ProductName"));

                PlanCampaign pc = new PlanCampaign();
                pc.setId(rs.getInt("PlanCampnID"));
                pc.setPlan(p);
                pc.setProduct(pro);
                pc.setQuantity(rs.getInt("Quantity"));
                pc.setEstimate(rs.getInt("Estimate"));

                plans.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    public Product getProductName(int id) {
        Product pro = new Product();
        String sql = "SELECT * FROM Product\n"
                + "WHERE ProductID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setName(rs.getString("ProductName"));
                pro = p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;

    }

    public PlanCampaign getTimePlansByID(int id) {
        PlanCampaign plan = new PlanCampaign();
        String sql = "SELECT pc.PlanCampnID,pc.Quantity,pc.Estimate,p.PlanID,p.PlanName,p.StartDate,p.EndDate,pro.ProductName,d.DepartmentID,d.DepartmentName,d.type,\n"
                + "pro.ProductID,pro.ProductName\n"
                + "FROM [PlanCampain] pc \n"
                + "JOIN [Plan] p ON p.PlanID = pc.PlanID\n"
                + "JOIN Product pro ON pro.ProductID = pc.ProductID\n"
                + "JOIN Department d ON d.DepartmentID = p.DepartmentID\n"
                + "WHERE pc.PlanCampnID =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plan p = new Plan();
                p.setId(rs.getInt("PlanID"));
                p.setName(rs.getString("PlanName"));
                p.setStart(rs.getDate("StartDate"));
                p.setEnd(rs.getDate("EndDate"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                d.setType(rs.getString("type"));
                p.setDept(d);

                Product pro = new Product();
                pro.setId(rs.getInt("ProductID"));
                pro.setName(rs.getString("ProductName"));

                PlanCampaign pc = new PlanCampaign();
                pc.setId(rs.getInt("PlanCampnID"));
                pc.setPlan(p);
                pc.setProduct(pro);
                pc.setQuantity(rs.getInt("Quantity"));
                pc.setEstimate(rs.getInt("Estimate"));

                plan = pc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plan;
    }

    public ArrayList<SchedualEmployee> getEmployeesFromSC(int campID, Date date) {
        ArrayList<SchedualEmployee> scs = new ArrayList<>();
        String sql = "SELECT sc.ScID,sc.Date,sc.Shift,sc.Quantity AS scQuantity,pc.PlanID,se.SchEmpID,se.Quantity\n"
                + ",e.EmployeeID,e.EmployeeName,e.gender,e.address,e.dob,e.salary,d.DepartmentID,d.DepartmentName  FROM SchedualCampaign sc\n"
                + "JOIN PlanCampain pc ON pc.PlanCampnID = sc.PlanCampnID\n"
                + "JOIN Product p ON p.ProductID = pc.ProductID\n"
                + "JOIN SchedualEmployee se ON se.ScID = sc.ScID\n"
                + "JOIN Employee e ON e.EmployeeID = se.EmployeeID\n"
                + "JOIN Department d  ON d.DepartmentID = e.DepartmentID\n"
                + "where sc.PlanCampnID =? AND sc.Date = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, campID);
            stm.setDate(2, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PlanCampaign pc = new PlanCampaign();
                pc.setId(rs.getInt("PlanID"));

                Employee e = new Employee();
                e.setId(rs.getInt("EmployeeID"));
                e.setName(rs.getString("EmployeeName"));
                e.setGender(rs.getBoolean("gender"));
                e.setAddress(rs.getString("address"));
                BigDecimal salaryBD = rs.getBigDecimal("salary");
                e.setSalary(salaryBD.doubleValue());

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                e.setDept(d);

                SchedualCampaign sc = new SchedualCampaign();
                sc.setId(rs.getInt("ScID"));
                sc.setDate(rs.getDate("Date"));
                sc.setShift(rs.getInt("Shift"));
                sc.setQuantity(rs.getInt("scQuantity"));
                sc.setPlanCampain(pc);

                SchedualEmployee se = new SchedualEmployee();
                se.setId(rs.getInt("SchEmpID"));
                se.setQuantity(rs.getInt("Quantity"));
                se.setEmployee(e);
                se.setSc(sc);
                scs.add(se);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scs;

    }

    public List<SchedualCampaign> getInfoScById(int id) {
        List<SchedualCampaign> scs = new ArrayList<>();
        String sql = "select * from SchedualCampaign sc\n"
                + "WHERE  sc.PlanCampnID =?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SchedualCampaign sc = new SchedualCampaign();
                sc.setId(rs.getInt("ScID"));
                sc.setDate(rs.getDate("Date"));
                sc.setShift(rs.getInt("Shift"));
                sc.setQuantity(rs.getInt("Quantity"));
                scs.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scs;
    }

    public SchedualEmployee getEmInfoByDay(int scheEmID) {
        SchedualEmployee se = new SchedualEmployee();
        String sql = "SELECT att.AttendentID,att.Quantity AS attQuantity,att.Alpha,se.SchEmpID,se.Quantity AS seQuantity,e.EmployeeID,e.EmployeeName,e.gender,e.dob,\n"
                + "e.address,d.DepartmentID,d.DepartmentName,d.type,sc.Date,sc.Quantity,sc.Shift\n"
                + "FROM Attendence att\n"
                + "JOIN SchedualEmployee se ON se.EmployeeID = att.AttendentID\n"
                + "JOIN Employee e ON e.EmployeeID = se.EmployeeID\n"
                + "JOIN Department d ON d.DepartmentID = e.DepartmentID \n"
                + "JOIN SchedualCampaign sc ON sc.ScID = se.ScID\n"
                + "Where se.SchEmpID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, scheEmID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("EmployeeID"));
                e.setName(rs.getString("EmployeeName"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getString("address"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                e.setDept(d);

                Attendence att = new Attendence();
                att.setId(rs.getInt("AttendentID"));
                att.setAlpha(rs.getFloat("Alpha"));
                att.setQuantity(rs.getFloat("attQuantity"));

                SchedualCampaign sc = new SchedualCampaign();
                sc.setDate(rs.getDate("Date"));
                sc.setQuantity(rs.getInt("Quantity"));
                sc.setShift(rs.getInt("Shift"));

                se.setId(rs.getInt("SchEmpID"));
                se.setEmployee(e);
                se.setQuantity(rs.getInt("seQuantity"));
                se.setSc(sc);
                se.setAttendence(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return se;
    }

    public ArrayList<Department> getDepartments() {
        ArrayList<Department> departs = new ArrayList<>();
        String sql = "SELECT * FROM Department";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                d.setName(rs.getString("type"));
                departs.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departs;
    }
    public void insert(Plan entity) {
        try {
            connection.setAutoCommit(false);

            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([PlanName]\n"
                    + "           ,[StartDate]\n"
                    + "           ,[EndDate]\n"
                    + "           ,[DepartmentID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, entity.getName());
            stm_insert_plan.setDate(2, entity.getStart());
            stm_insert_plan.setDate(3, entity.getEnd());
            stm_insert_plan.setInt(4, entity.getDept().getId());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as PlanID";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("PlanID"));
            }

            for (PlanCampaign campain : entity.getCampaigns()) {
                String sql_insert_campain = "INSERT INTO [PlanCampain]\n"
                        + "           ([PlanID]\n"
                        + "           ,[ProductID]\n"
                        + "           ,[Quantity]\n"
                        + "           ,[Estimate])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";

                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, entity.getId());
                stm_insert_campain.setInt(2, campain.getProduct().getId());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getCost());
                stm_insert_campain.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean insertSC(int planCamID, Date date, int shift, int quantity) {
        try {
           String sql = "INSERT INTO [dbo].[SchedualCampaign] " +
                     "([PlanCampnID], [Date], [Shift], [Quantity]) " +
                     "VALUES (?, ?, ?, ?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, planCamID);
            stm.setDate(2, date);
            stm.setInt(3, shift);
            stm.setInt(4, quantity);

            int rowsAffected = stm.executeUpdate();
            if(rowsAffected >0){
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
