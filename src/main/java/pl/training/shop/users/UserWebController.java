package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.common.PagedResult;

@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("add-user.html")
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView("add-user");
        modelAndView.addObject(new User());
        return modelAndView;
    }

    @GetMapping("show-users.html")
    public ModelAndView showUsers(@RequestParam(defaultValue = "0") int pageNumber
            , @RequestParam(defaultValue = "5") int pageSize){
        PagedResult<User> users = userService.getAll(pageNumber, pageSize);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
