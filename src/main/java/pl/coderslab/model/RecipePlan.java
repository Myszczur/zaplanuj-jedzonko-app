package pl.coderslab.model;

public class RecipePlan {

    private int recipeID;
    private int id;
    private String mealName;
    private int displayOrder;
    private int dayNameId;
    private int planId;

    public RecipePlan() {
    }

    public RecipePlan(int recipeID, int id, String mealName, int displayOrder, int dayNameId, int planId) {
        this.recipeID = recipeID;
        this.id = id;
        this.mealName = mealName;
        this.displayOrder = displayOrder;
        this.dayNameId = dayNameId;
        this.planId = planId;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(int dayNameId) {
        this.dayNameId = dayNameId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipePlan{" +
                "recipeID=" + recipeID +
                ", id=" + id +
                ", mealName='" + mealName + '\'' +
                ", displayOrder=" + displayOrder +
                ", dayNameId=" + dayNameId +
                ", planId=" + planId +
                '}';
    }
}
