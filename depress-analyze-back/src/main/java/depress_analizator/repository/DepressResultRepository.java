package depress_analizator.repository;

import depress_analizator.model.entity.DepressResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepressResultRepository extends JpaRepository<DepressResult,Integer> {

}
