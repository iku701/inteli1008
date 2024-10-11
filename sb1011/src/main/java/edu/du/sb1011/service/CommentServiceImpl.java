package edu.du.sb1011.service;

import edu.du.sb1011.dto.CommentDto;
import edu.du.sb1011.mapper.CommentMapper;
import edu.du.sb1011.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDto> selectCommentListByBoardIdx(int commentBoardIdx) throws Exception {
        return commentMapper.selectCommentListByBoardIdx(commentBoardIdx);
    }

    @Override
    public void insertComment(CommentDto comment) throws Exception {
        commentMapper.insertComment(comment);
    }

    @Override
    public void updateComment(CommentDto comment) throws Exception {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(int commentIdx) throws Exception {
        commentMapper.deleteComment(commentIdx);
    }

    @Override
    public CommentDto selectCommentByIdx(int commentIdx) throws Exception {
        return commentMapper.selectCommentByIdx(commentIdx);
    }
}
