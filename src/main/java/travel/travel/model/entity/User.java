package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import travel.travel.model.enums.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     String email;
     String password;
     String phoneNumber;

    @Enumerated(EnumType.STRING)
    Role role;

     @OneToOne(fetch = FetchType.LAZY)
     Travel travel;

    public User(String name, String email, String password, String phoneNumber, Role role, Travel travel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.travel = travel;
    }
}
