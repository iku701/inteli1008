package edu.du.sb1011.service;

import edu.du.sb1011.dto.CommentDto;
import java.util.List;

public interface CommentService {

    List<CommentDto> selectCommentListByBoardIdx(int commentBoardIdx) throws Exception;

    void insertComment(CommentDto comment) throws Exception;

    void updateComment(CommentDto comment) throws Exception;

    void deleteComment(int commentIdx) throws Exception;

    CommentDto selectCommentByIdx(int commentIdx) throws Exception;
}
