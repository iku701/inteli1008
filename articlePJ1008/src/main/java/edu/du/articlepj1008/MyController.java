package edu.du.articlepj1008;

import edu.du.articlepj1008.dto.ArticleListModel;
import edu.du.articlepj1008.dto.Article;
import edu.du.articlepj1008.service.ArticleNotFoundException;
import edu.du.articlepj1008.service.ListArticleService;
import edu.du.articlepj1008.service.ReadArticleService;
import edu.du.articlepj1008.service.WriteArticleService;
import edu.du.articlepj1008.service.WritingRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private ListArticleService listService;

    @Autowired
    private ReadArticleService readService;

    @Autowired
    private WriteArticleService writeService;

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumberString = request.getParameter("p");
        int pageNumber = 1;
        if (pageNumberString != null && pageNumberString.length() > 0) {
            pageNumber = Integer.parseInt(pageNumberString);
        }

        logger.debug("Requesting page: {}", pageNumber);

        ArticleListModel articleListModel = listService.getArticleList(pageNumber);
        model.addAttribute("listModel", articleListModel);

        if (articleListModel.getTotalPageCount() > 0) {
            int beginPageNumber = (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
            int endPageNumber = beginPageNumber + 9;
            if (endPageNumber > articleListModel.getTotalPageCount()) {
                endPageNumber = articleListModel.getTotalPageCount();
            }
            logger.debug("Begin page: {}, End page: {}", beginPageNumber, endPageNumber);
            model.addAttribute("beginPage", beginPageNumber);
            model.addAttribute("endPage", endPageNumber);
        }
        return "list";
    }

    @GetMapping("/read")
    public String readArticle(Model model, HttpServletRequest request) {
        String articleIdString = request.getParameter("id");
        if (articleIdString == null || articleIdString.isEmpty()) {
            return "redirect:/list"; // id가 없는 경우 목록 페이지로 리다이렉트
        }

        int articleId = Integer.parseInt(articleIdString);
        try {
            Article article = readService.getArticle(articleId); // 메서드 호출 수정
            if (article == null) {
                return "redirect:/list"; // 해당 id의 게시물이 없는 경우 목록 페이지로 리다이렉트
            }
            model.addAttribute("article", article);
        } catch (ArticleNotFoundException e) {
            return "redirect:/list"; // 글을 찾지 못한 경우
        }
        return "read";
    }

    @GetMapping("/writeForm")
    public String writeForm(Model model) {
        model.addAttribute("writingRequest", new WritingRequest());
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(WritingRequest writingRequest) {
        writeService.write(writingRequest);
        return "redirect:/list";
    }


}