package edu.du.sb1011.mapper;

import edu.du.sb1011.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto> selectCommentListByBoardIdx(@Param("comment_board_idx") int commentBoardIdx);

    void insertComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteComment(@Param("comment_idx") int commentIdx);

    CommentDto selectCommentByIdx(@Param("comment_idx") int commentIdx);
}
