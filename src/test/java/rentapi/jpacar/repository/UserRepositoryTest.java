package rentapi.jpacar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import rentapi.jpacar.domain.User;
import rentapi.jpacar.domain.UserLevel;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    public void 유저조회() throws Exception {

        User user = new User();
        user.setUserName("테스트1");
        user.setUserLevel(UserLevel.ROOKIE);

        User saveUser = userRepository.save(user);


        User findUser = userRepository.findById(saveUser.getUserId()).get();

        assertEquals(findUser.getUserName(), user.getUserName());
    }

}