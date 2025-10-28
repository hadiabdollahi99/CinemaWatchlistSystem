package service;

import model.Comment;

import java.util.List;

public interface CommentService extends BaseService<Long, Comment>{
    List<Comment> findCommentByMovieId (Long movieId);
}
