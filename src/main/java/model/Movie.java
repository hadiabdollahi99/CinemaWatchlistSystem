package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST)
    private List<UserMovie> userMovies = new ArrayList<>();
}
