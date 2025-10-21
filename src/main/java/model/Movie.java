package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Movie extends BaseEntity<Long> implements Serializable {
    private String title;
    private String Genre;
    private String description;
    private Integer Duration;
    private LocalDate releaseDate;
    private Double rating;
    private byte[] moviePicture;

    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<UserMovie> userMovies = new ArrayList<>();


    public String getProfilePictureBase64() {
        if (moviePicture != null && moviePicture.length > 0) {
            return Base64.getEncoder().encodeToString(moviePicture);
        }
        return null;
    }
}
