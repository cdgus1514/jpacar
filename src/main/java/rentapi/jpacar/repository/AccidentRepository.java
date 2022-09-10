package rentapi.jpacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.Accident;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {
}
