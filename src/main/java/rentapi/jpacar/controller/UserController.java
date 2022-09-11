package rentapi.jpacar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentapi.jpacar.domain.User;
import rentapi.jpacar.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user/regist")
    public ResponseEntity<Long> regist(@RequestBody User user) {
        log.debug("param check : {}, {}", user.getUserName(), user.getUserLevel());
        Long result = userService.regist(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("user/{no}")
    public ResponseEntity<User> user(@PathVariable("no") Long userId) {
        User result = userService.findUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(userService.findAllUser());
    }
}
