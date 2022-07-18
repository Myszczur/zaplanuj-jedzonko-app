package pl.coderslab.dao;

public class Plan {
    private int planId;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public Plan() {
    }

    public Plan(int planId, String name, String description, String created, int adminId) {
        this.planId = planId;
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}

