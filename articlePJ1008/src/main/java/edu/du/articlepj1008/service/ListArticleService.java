package edu.du.articlepj1008.service;

import java.util.List;

import edu.du.articlepj1008.dao.ArticleDao;
import edu.du.articlepj1008.dto.Article;
import edu.du.articlepj1008.dto.ArticleListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListArticleService {

	@Autowired
	private ArticleDao articleDao;

	public static final int COUNT_PER_PAGE = 10;

	public ArticleListModel getArticleList(int requestPageNumber) {
		if (requestPageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
		}

		int totalArticleCount = articleDao.selectCount();

		if (totalArticleCount == 0) {
			return new ArticleListModel();
		}

		int totalPageCount = calculateTotalPageCount(totalArticleCount);

		int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE;
		int endRow = firstRow + COUNT_PER_PAGE;

		List<Article> articleList = articleDao.select(firstRow, endRow);

		return new ArticleListModel(articleList, requestPageNumber, totalPageCount, firstRow, endRow);
	}

	private int calculateTotalPageCount(int totalArticleCount) {
		if (totalArticleCount == 0) {
			return 0;
		}
		int pageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		return pageCount;
	}
}