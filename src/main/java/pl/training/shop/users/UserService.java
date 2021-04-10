package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.PagedResult;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User add(User user){
        return userRepository.save(user);
    }
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

    }
    public PagedResult<User> getByLastName(String lastNameFragment, int pageNumber, int pageSize){
        var userPage = userRepository.findByLastNameContaining(lastNameFragment, PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(userPage.getContent(), pageNumber, userPage.getTotalPages());
    }

    public PagedResult<User> getAll(int pageNumber, int pageSize){
        var userPage = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(userPage.getContent(), pageNumber, userPage.getTotalPages());
    }


}
