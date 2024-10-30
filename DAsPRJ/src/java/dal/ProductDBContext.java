package dal;
import dal.DBContext;
import entity.assignment.Department;
import entity.assignment.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDBContext extends DBContext {
    public ArrayList<Product> list() {
        ArrayList<Product> products = new ArrayList<>();
        PreparedStatement command = null;
        try {
            String sql = "SELECT [ProductID]\n"
                    + "      ,[ProductName]\n"
                    + "  FROM [Product]";

            command = connection.prepareStatement(sql);
            ResultSet rs = command.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setName(rs.getString("ProductName"));
                products.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                command.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }

}
