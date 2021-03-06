package appwarehouse.repository;

import appwarehouse.entity.Measurement;
import appwarehouse.model.MeasurementDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Long> {

   @Override
   @Query(value = "select t from Measurement t where t.active = true")
   List<Measurement> findAll();

   Optional<Measurement> findByName(String name);
}
