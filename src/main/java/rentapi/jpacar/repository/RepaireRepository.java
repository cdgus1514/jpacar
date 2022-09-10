package rentapi.jpacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.Repaire;

@Repository
public interface RepaireRepository extends JpaRepository<Repaire, Long> {
}
