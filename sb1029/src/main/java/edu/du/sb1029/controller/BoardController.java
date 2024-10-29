package edu.du.sb1029.controller;

import edu.du.sb1029.entity.Board;
import edu.du.sb1029.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/openBoardList")
	public String openBoardList(Pageable pageable, Model model) {
		Page<Board> list = boardService.getBoardList(pageable);
		model.addAttribute("list", list);
		return "boardList";
	}

	@GetMapping("/openBoardDetail/{boardIdx}")
	public String openBoardDetail(@PathVariable("boardIdx") Integer boardIdx, Model model, RedirectAttributes redirectAttributes) {
		Optional<Board> board = Optional.ofNullable(boardService.getBoardDetail(boardIdx));
		if (board.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
			return "redirect:/board/openBoardList";
		}
		model.addAttribute("post", board.get());
		return "boardDetail";
	}

	@GetMapping("/openBoardWrite")
	public String openBoardWrite() {
		return "boardWrite";
	}

	@PostMapping("/saveBoard")
	public String saveBoard(Board board, RedirectAttributes redirectAttributes) {
		try {
			boardService.saveBoard(board);
			redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 저장되었습니다.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "게시글 저장에 실패했습니다.");
		}
		return "redirect:/board/openBoardList";
	}
}
