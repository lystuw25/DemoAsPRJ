/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.accesscontrol.Feature;
import entity.accesscontrol.Role;
import entity.accesscontrol.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A A
 */
public class AccountDBContext extends DBContext {

    public User Login(String user, String pass) {
        User acc = new User();
        String sql = "Select * From [User] Where UserName = ? AND password = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                acc.setUsername(rs.getString("UserName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public ArrayList<Role> getRoles(String username) {
        String sql = "SELECT r.RoleID,r.RoleName,f.FeatureID,f.FeatureName,f.url\n"
                + "FROM [User] u \n"
                + "INNER JOIN UserRole ur ON ur.UserName = u.UserName\n"
                + "INNER JOIN [Role] r ON r.RoleID = ur.RoleID\n"
                + "INNER JOIN RoleFeature rf ON r.RoleID = rf.RoleID\n"
                + "INNER JOIN Feature f ON f.FeatureID = rf.FeatureID\n"
                + "WHERE u.UserName = ? \n"
                + "ORDER BY r.RoleID, f.FeatureID ASC";

        PreparedStatement stm = null;
        ArrayList<Role> roles = new ArrayList<>();
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            Role c_role = new Role();
            c_role.setId(-1);
            while (rs.next()) {
                int rid = rs.getInt("RoleID");
                if (rid != c_role.getId()) {
                    c_role = new Role();
                    c_role.setId(rid);
                    c_role.setName(rs.getString("RoleName"));
                    roles.add(c_role);
                }

                Feature f = new Feature();
                f.setId(rs.getInt("FeatureID"));
                f.setName(rs.getString("FeatureName"));
                f.setUrl(rs.getString("url"));
                c_role.getFeatures().add(f);
                f.setRoles(roles);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return roles;
    }
}
