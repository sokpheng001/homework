package com.example.webapp.repository;

import com.example.webapp.model.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryData {
    public List<Category> category(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Sport"));
        categories.add(new Category("Anime"));
        categories.add(new Category("Metal Health"));
        return categories;
    }
}
