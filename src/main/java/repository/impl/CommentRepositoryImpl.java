package repository.impl;

import model.Comment;
import model.Movie;
import repository.CommentRepository;
import util.EntityManagerProvider;

import java.util.List;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Long, Comment> implements CommentRepository {
    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public List<Comment> findCommentByMovieId(Long movieId) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("select c from Comment c where c.movie.id = :movieId", Comment.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }
}
