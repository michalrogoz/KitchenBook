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

    public KitchenRecipe(){
        ingredients = new ArrayList<String>();
    }

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

//    public ArrayList<String> getIngredients() {
//        return ingredients;
//    }
    public String getIngredients() {
        String result = "";
        for (String ing:ingredients) {
            result = result + ing + ", ";
        }
        return  result;
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

    @Override
    public String toString() {
        return
        "Title: " + this.getTitle() + "\n" +
        "Author: " + this.getAuthor() + "\n" +
        "Category: " + this.getCategory() + "\n" +
        "Ingriendients: " + this.getIngredients()+ "\n" +
        "Description: " + this.getDescription();
    }
}
