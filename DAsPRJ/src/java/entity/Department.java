package entity;
import java.util.ArrayList;
public class Department {

    private int id;
    private String name;
    private ArrayList<Employee> emps = new ArrayList<>();

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

    public ArrayList<Employee> getEmps() {
        return emps;
    }

    public void setEmps(ArrayList<Employee> emps) {
        this.emps = emps;
    }
    
}
