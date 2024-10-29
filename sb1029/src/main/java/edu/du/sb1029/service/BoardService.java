package edu.du.sb1029.service;

import edu.du.sb1029.entity.Board;
import edu.du.sb1029.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board getBoardDetail(Integer boardIdx) {
		return boardRepository.findById(boardIdx).orElse(null);
	}

	public void saveBoard(Board board) {
		if (board.getBoardIdx() == null) {
			board.setCreatedDatetime(LocalDateTime.now().toString());
			board.setHitCnt(0);
			board.setDeletedYn("N");
		} else {
			board.setUpdatedDatetime(LocalDateTime.now().toString());
		}
		boardRepository.save(board);
	}
}
