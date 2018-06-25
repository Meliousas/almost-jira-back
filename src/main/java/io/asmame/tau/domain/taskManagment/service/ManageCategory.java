package io.asmame.tau.domain.taskManagment.service;

import io.asmame.tau.domain.taskManagment.entity.Category;
import io.asmame.tau.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageCategory {

    private CategoryRepository categoryRepository;
    private ManageTask manageTask;

    @Autowired
    public ManageCategory(CategoryRepository categoryRepository,@Lazy ManageTask manageTask) {
        this.categoryRepository = categoryRepository;
        this.manageTask = manageTask;
    }

    public void createNewCategory(Category category){
        categoryRepository.createNewCategory(category);

    }

    public void deleteCategory(Long categoryId, Long accountId){

        categoryRepository.deleteCategory(categoryId,accountId);
    }

    public List<Category> getAllCategoriesForUser(Long accountId){

        categoryRepository.getAllCategoriesForUser(accountId);
        return new ArrayList<>();
    }
}
