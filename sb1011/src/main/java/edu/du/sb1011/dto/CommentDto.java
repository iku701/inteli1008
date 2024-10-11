package edu.du.sb1011.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private int commentIdx;                    // 댓글 ID (DB 컬럼명: comment_idx)
    private int commentBoardIdx;               // 게시글 ID (DB 컬럼명: comment_board_idx)
    private int parentCommentIdx;              // 부모 댓글 ID (DB 컬럼명: parent_comment_idx)
    private String commentContents;            // 댓글 내용 (DB 컬럼명: comment_contents)
    private String commentCreatorId;           // 댓글 작성자 ID (DB 컬럼명: comment_creator_id)
    private LocalDateTime commentCreatedDatetime;  // 댓글 작성일 (DB 컬럼명: comment_created_datetime)
    private String commentUpdaterId;           // 댓글 수정자 ID (DB 컬럼명: comment_updater_id)
    private LocalDateTime commentUpdatedDatetime;  // 댓글 수정일 (DB 컬럼명: comment_updated_datetime)
    private String deletedYn;                  // 삭제 여부 (DB 컬럼명: deleted_yn)
}
