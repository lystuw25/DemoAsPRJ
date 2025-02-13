package entity.assignment;
public class PlanCampaign {
    private int id;
    private Plan plan;
    private Product product;
    private int quantity;
    private int estimate;
    private float cost;

    public PlanCampaign() {
    }

    public PlanCampaign(int id, Plan plan, Product product, int quantity, int estimate, float cost) {
        this.id = id;
        this.plan = plan;
        this.product = product;
        this.quantity = quantity;
        this.estimate = estimate;
        this.cost = cost;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }
    
}
