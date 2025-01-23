package com.apifinanceapp.financeapp.service;

import java.util.ArrayList;
import java.util.List;

import com.apifinanceapp.financeapp.model.Category;

public class CategoryService {

    Category category = new Category("1", "Gasto", null);
    List<Category> lista = new ArrayList<Category>();

    public List<Category> getCategoriesByUser() {
        lista.add(category);
        return lista;
    }

    public Category getCategoryById(String id) {
        System.out.println(id);
        return lista.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }

    public Category createCategory(Category category) {
        System.out.println(category);
        return category;
    }

    public Category updateCategory(String id, Category category) {
        System.out.println("ID: " + id + ", User: " + category);

        // Usar un solo stream para buscar y actualizar
        for (Category categoryx : lista) {
            if (category.getId().equals(id)) {
                updateFields(categoryx, category);
                return category;
            }
        }

        return null;
    }

    public String deleteCategory(String id) {

        System.out.println(id);

        for (Category category : lista) {
            if (category.getId().equals(id)) {
                lista.remove(category);
                return "Category satisfactory deleted"; // Salir del bucle si se eliminó
            }
        }

        return "Category not found"; // Devolver mensaje de eliminado
    }

    private void updateFields(Category target, Category source) {
        // Actualizar solo los campos no nulos
        if (source.getName() != null)
            target.setName(source.getName());

    }
}
