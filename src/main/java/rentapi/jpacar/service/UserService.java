package rentapi.jpacar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentapi.jpacar.domain.User;
import rentapi.jpacar.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public Long regist(User user) {
        User saveU = User.Regist(user);
        User result = userRepository.save(saveU);
        return result.getUserId();
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
