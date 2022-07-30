package AppDisney.peliculas.repository;

import AppDisney.peliculas.entity.GenderEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long>  {

    List<GenderEntity> findAll(Specification<GenderEntity> spec);

}
