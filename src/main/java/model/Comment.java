package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Comment extends BaseEntity<Long> implements Serializable {

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
