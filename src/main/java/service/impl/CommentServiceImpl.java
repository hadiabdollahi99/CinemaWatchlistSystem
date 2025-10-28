package service.impl;

import model.Comment;
import repository.CommentRepository;
import service.CommentService;

import java.util.List;

public class CommentServiceImpl extends BaseServiceImpl<Long, Comment, CommentRepository> implements CommentService {

    protected CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository baseRepository) {
        super(baseRepository);
        this.commentRepository = baseRepository;
    }

    @Override
    public List<Comment> findCommentByMovieId(Long movieId) {
        return commentRepository.findCommentByMovieId(movieId);
    }
}
