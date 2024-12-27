package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

     @OneToOne(fetch = FetchType.LAZY)
     Travel travel;

    public User(String name, String email, String password, String phoneNumber, Travel travel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.travel = travel;
    }
}
