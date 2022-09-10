package rentapi.jpacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
