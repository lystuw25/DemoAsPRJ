package dal;
import dal.DBContext;
import entity.assignment.Department;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DepartmentDBContext extends DBContext{
    public ArrayList<Department> list() {
        ArrayList<Department> depts = new ArrayList<>();
        PreparedStatement command = null;
        try {
            String sql = "SELECT did, dname FROM Department";

            command = connection.prepareStatement(sql);
            ResultSet rs = command.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                depts.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dal.DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                command.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(dal.DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return depts;
    }

    public ArrayList<Department> get(String type) {
        ArrayList<Department> depts = new ArrayList<>();
        PreparedStatement command = null;
        try {
            String sql = "SELECT * FROM [Department] WHERE [type] = ?";
            command = connection.prepareStatement(sql);
            command.setString(1, type);
            ResultSet rs = command.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                d.setType(type);
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(dal.DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                command.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(dal.DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return depts;
    }
}
