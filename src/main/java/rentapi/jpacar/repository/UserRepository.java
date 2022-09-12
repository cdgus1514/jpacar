package rentapi.jpacar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
