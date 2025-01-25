package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.Category;
import com.apifinanceapp.financeapp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoriesByUser() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category category) {
        Category target = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            updateFields(target, category);
            return categoryRepository.save(target);
        }
        return null;
    }

    public String deleteCategory(String id) {

        Category target = categoryRepository.findById(id).orElse(null);

        if (target != null) {
            categoryRepository.deleteById(id);
            return "Category satisfactory deleted"; // Salir del bucle si se eliminó el usuario

        }

        return "Category not found"; // Devolver mensaje de usuario eliminado
    }

    private void updateFields(Category target, Category source) {
        // Actualizar solo los campos no nulos
        if (source.getName() != null)
            target.setName(source.getName());

    }
}
