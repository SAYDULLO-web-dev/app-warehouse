package appwarehouse.repository;

import appwarehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Override
    @Query(value = "select t from Measurement t where t.active = true")
    List<Product> findAll();

    @Query("select p from Product p where p.category.id = ?1 and p.active = true ")
    List<Product> findAllByCategoryId(Long categoryId);

    @Query("select p from Product p where p.measurement.id = ?1 and p.active= true")
    List<Product> findAllByMeasurementId(Long measurementId);

    @Query("select p from Product p where p.name = ?1")
    Optional<Product> findByName(String name);
}
