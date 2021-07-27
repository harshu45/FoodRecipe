package com.omsakti.foodrecipe;

public class model_category {
    String str_id;
    String str_name;
    String str_image;
    String str_thing;
    String str_recipe;

    public model_category(String str_id, String str_name, String str_image, String str_thing, String str_recipe) {
        this.str_id = str_id;
        this.str_name = str_name;
        this.str_image = str_image;
        this.str_thing = str_thing;
        this.str_recipe = str_recipe;
    }

    public model_category() {
    }

    public String getStr_id() {
        return str_id;
    }

    public void setStr_id(String str_id) {
        this.str_id = str_id;
    }

    public String getStr_name() {
        return str_name;
    }

    public void setStr_name(String str_name) {
        this.str_name = str_name;
    }

    public String getStr_image() {
        return str_image;
    }

    public void setStr_image(String str_image) {
        this.str_image = str_image;
    }

    public String getStr_thing() {
        return str_thing;
    }

    public void setStr_thing(String str_thing) {
        this.str_thing = str_thing;
    }

    public String getStr_recipe() {
        return str_recipe;
    }

    public void setStr_recipe(String str_recipe) {
        this.str_recipe = str_recipe;
    }
}
