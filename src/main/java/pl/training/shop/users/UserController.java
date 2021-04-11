package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(User user){
        Long userId = userService.add(user).getId();
        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }
}
