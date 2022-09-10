package rentapi.jpacar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
