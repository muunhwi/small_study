package com.smallstudy.repo;

import com.smallstudy.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    @Query("SELECT a FROM CategoryItem a JOIN FETCH a.category b WHERE b.id = a.category.id")
    List<CategoryItem> findAllCategoryItemsWithCategory();
}
