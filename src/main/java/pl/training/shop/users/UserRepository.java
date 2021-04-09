package pl.training.shop.users;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {

     Page<User> findByLastNameContaining(String lastNameFragment, Pageable pageable);

}
