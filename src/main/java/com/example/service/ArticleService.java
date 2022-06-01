package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事情報を扱うサービス.
 * 
 * @author hvthinh
 *
 */

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 全件記事情報を取得する.
	 * 
	 * @return
	 */
	public List<Article> fillAll() {
		return articleRepository.fillAll();
	}

	/**
	 * 記事情報を投稿する.
	 * 
	 * @param article
	 */
	public void insert(Article article) {
		articleRepository.insert(article);
	}
}
