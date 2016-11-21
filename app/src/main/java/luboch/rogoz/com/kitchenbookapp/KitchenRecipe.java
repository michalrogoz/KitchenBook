package luboch.rogoz.com.kitchenbookapp;

import java.util.ArrayList;

/**
 * Created by Mic on 2016-11-21.
 */

public class KitchenRecipe {
    private String title;
    private String author;
    private String category;
    private ArrayList<String> ingredients;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient){
        this.ingredients.add(ingredient);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
