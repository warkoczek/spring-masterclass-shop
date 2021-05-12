package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.ExceptionTransferObject;

import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("${apiPrefix}/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<UserTransferObject> addUser(@Valid @RequestBody UserTransferObject userTransferObject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        User user = userMapper.toUser(userTransferObject);
        Long userId = userService.add(user).getId();
        URI locationUri = uriBuilder.requestUriWithId(userId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable Long id){
        User user = userService.getById(id);
        UserTransferObject userTransferObject = userMapper.toUserTransferObject(user);
        return ResponseEntity.ok(userTransferObject);

    }

    @GetMapping
    public PagedResultTransferObject<UserTransferObject> getUsersByLastName(
            @RequestParam String lastNameFragment,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize){
        var users = userService.getByLastName(lastNameFragment, pageNumber, pageSize);
        return userMapper.toUserTransferObjectsPage(users);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionTransferObject> onUserNotFound(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionTransferObject("User not found"));
    }
}
