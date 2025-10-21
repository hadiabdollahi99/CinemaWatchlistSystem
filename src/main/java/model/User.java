package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
//<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..." alt="Test Image">

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
    private byte[] profileUrl;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserMovie> watchlist = new ArrayList<>();

    public String getProfilePictureBase64() {
        if (profileUrl != null && profileUrl.length > 0) {
            return Base64.getEncoder().encodeToString(profileUrl);
        }
        return null;
    }


}

