package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
