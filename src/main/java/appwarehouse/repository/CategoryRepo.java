package appwarehouse.repository;

import appwarehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    //@Query("select c from Category c where c.parentCategory is null")
    @Query("select c from Category c where c.parentCategory is null and c.active = true")
    List<Category> findAllParentCategories();

    @Query("select c from Category c where c.parentCategory = :parentCategory and c.active = true")
    List<Category> findAllChildren(Category parentCategory);

    Optional<Category> findByName(String name);
}
