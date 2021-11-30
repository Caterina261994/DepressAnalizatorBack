package depress_analizator.repository;

import depress_analizator.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Photo, Integer> {
}
