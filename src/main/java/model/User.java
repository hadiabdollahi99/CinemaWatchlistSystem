package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "Users")
public class User extends BaseEntity<Long> implements Serializable {
    private String username;
    private String email;
    private String password;
    private String resetToken;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserMovie> watchlist = new ArrayList<>();


}

