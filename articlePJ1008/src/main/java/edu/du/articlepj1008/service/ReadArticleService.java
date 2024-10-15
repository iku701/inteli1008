package edu.du.articlepj1008.service;

import java.sql.Connection;
import java.sql.SQLException;

import edu.du.articlepj1008.dao.ArticleDao;
import edu.du.articlepj1008.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadArticleService {
	@Autowired
	ArticleDao articleDao;

	public Article readArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, true);
	}

	private Article selectArticle(int articleId, boolean increaseCount)
			throws ArticleNotFoundException {


		Article article = articleDao.selectById(articleId);
		if (article == null) {
			throw new ArticleNotFoundException(
					"해당 글이 없습니다: " + articleId);
		}
		if (increaseCount) {
			articleDao.increaseReadCount(articleId);
			article.setReadCount(article.getReadCount() + 1);
		}
		return article;
	}

	public Article getArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, false);
	}
}