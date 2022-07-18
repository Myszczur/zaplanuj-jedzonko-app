package pl.coderslab.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recipe {
    private int nameId;
    private String name;
    private String ingredients;
    private String description;
    //Date created;
    private String created;
    // Date update;
    private String updated;
    private int preparationTime;

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    private String preparation;
    private int adminId;

    public Recipe(){}
    public Recipe(int nameId, String name, String ingredients, String description, String created, String updated, int preparationTime, String preparation, int adminId) {
        this.nameId = nameId;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparationTime = preparationTime;
        this.preparation = preparation;
        this.adminId = adminId;
    }


    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.created = timeStamp;

    }

    public String getUpdate() {
        return updated;
    }

    public void setUpdate(String updated) {
        this.updated = updated;
    }



    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {

        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "nameId=" + nameId +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", preparationTime=" + preparationTime +
                ", preparation='" + preparation + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}

