package edu.du.articlepj1008.service;

import edu.du.articlepj1008.dao.ArticleDao;
import edu.du.articlepj1008.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WriteArticleService {

	@Autowired
	private ArticleDao articleDao;

	public void write(WritingRequest request) {
		Article article = new Article();
		article.setWriterName(request.getWriterName());
		article.setPassword(request.getPassword());
		article.setTitle(request.getTitle());
		article.setContent(request.getContent());

		// 서버 측에서 설정해야 하는 필드들
		article.setPostingDate(new Timestamp(System.currentTimeMillis())); // 현재 시간을 게시일로 설정
		article.setReadCount(0); // 초기 조회수 설정
		article.setGroupId(0); // 이 값이 적절히 설정되었는지 확인 필요
		article.setSequenceNumber("0"); // 초기 시퀀스 번호 설정

		articleDao.insert(article); // DAO를 통해 데이터베이스에 저장
	}
}