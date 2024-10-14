package edu.du.sb1014_2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardSummaryDTO {
    private Integer boardIdx;
    private String title;
    private Integer hitCnt;
    private LocalDateTime createdDatetime;

    public BoardSummaryDTO(Integer boardIdx, String title, Integer hitCnt, LocalDateTime createdDatetime) {
        this.boardIdx = boardIdx;
        this.title = title;
        this.hitCnt = hitCnt;
        this.createdDatetime = createdDatetime;
    }

}
