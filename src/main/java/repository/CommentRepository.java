package repository;

import model.Comment;
import model.Movie;

import java.util.List;

public interface CommentRepository extends BaseRepository<Long, Comment>{
    List<Comment> findCommentByMovieId (Long movieId);
}
