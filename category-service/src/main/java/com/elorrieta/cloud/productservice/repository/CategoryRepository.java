package com.elorrieta.cloud.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elorrieta.cloud.productservice.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
