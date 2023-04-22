package com.example.webapp.controller;
import com.example.webapp.model.Blog;
import com.example.webapp.model.Category;
import com.example.webapp.repository.CategoryData;
import com.example.webapp.repository.DataRepository;
import com.example.webapp.service.BlogService;
import com.example.webapp.service.CategoryService;
import com.example.webapp.service.CategoryServiceImp;
import com.example.webapp.service.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddBlogController {
    private final FileUploadService fileUpload;
    private final BlogService blogs;
    private final DataRepository dataRepository;
    private final CategoryService categoryService;
    private final CategoryData categoryData;
//    private int i=0;
    private Blog blog;

    @Bean("categoryBean1")
    public void setBeanForCategory(){
        blog = new Blog();
        blog.setCategoryList(categoryData.category());
    }
    @GetMapping("/add")
    public String add(Model model, Blog blog){
        List<Blog> blogs1 = blogs.findAll();
        model.addAttribute("blog1",blogs1);
        model.addAttribute("categories",categoryService.categoryData());
        //
        model.addAttribute("blog",blog);
        model.addAttribute("category",new Category());
        System.out.println("The Test: " + categoryService.categoryData());
        return "pages/addBlogForm";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("blog") @Valid Blog blog,
                          BindingResult error,
                          @RequestParam("file") MultipartFile multipartFile,
                          Model model) throws IOException {
        //blog = this.blog;
//        List<Blog> blogs1 = blogs.findAll();
        //list of Category
//        model.addAttribute("categories",categoryServiceImpl.categoryData());
//        System.out.println(blog.getCategoryList());
//s
////        dataRepository.getBlogs().remove(i);
//        dataRepository.getBlogs().add(i,new Blog(blog.getTitle(),blog.getAuthor(),blog.getDescription(),"Sport","p" + (i+1) + ".jpg"));
//        i++;
//        System.out.println(blog.getCategoryList());
        if(error.hasErrors() || multipartFile.isEmpty()){
            model.addAttribute("blog",blog);
            return "pages/addBlogForm";
        }
        blogs.save(blog,multipartFile);
        fileUpload.uploadSingle(multipartFile);
        return "redirect:/add";
    }
}
