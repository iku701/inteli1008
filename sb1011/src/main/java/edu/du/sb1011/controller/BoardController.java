package edu.du.sb1011.controller;

import edu.du.sb1011.dto.BoardDto;
import edu.du.sb1011.dto.CommentDto;
import edu.du.sb1011.service.BoardService;
import edu.du.sb1011.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/")
	public String index() throws Exception{
		return "redirect:/board/openBoardList.do";
	}

	@GetMapping("/board/openBoardList.do")
	public String openBoardList(Model model) throws Exception{
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list", list);
		return "/board/boardList";
	}

	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}

	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}

	@GetMapping("/board/openBoardDetail.do")
	public String openBoardDetail(@RequestParam int boardIdx, Model model) throws Exception {
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		model.addAttribute("board", board);

		List<CommentDto> commentList = commentService.selectCommentListByBoardIdx(boardIdx);
		if (commentList == null) {
			commentList = new ArrayList<>(); // Null 방지
		}
		model.addAttribute("commentList", commentList);

		return "/board/boardDetail";
	}


	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
