package pl.training.shop.users;

import lombok.*;
import pl.training.shop.common.validator.Name;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Table(name = "users", indexes = @Index(name = "email", columnList = "email"))
@Entity
@Builder
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Za-z]+")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Name
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;


}
