package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメントの情報を扱うサービス.
 * 
 * @author hvthinh
 *
 */
@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * Idでコメントリストを取得する.
	 * 
	 * @param articleId
	 * @return コメントリスト
	 */
	public List<Comment> findByArticleID(Integer articleId) {
		return commentRepository.filndByArticleId(articleId);
	}

	/**
	 * コメントを投稿する.
	 * 
	 * @param comment
	 */
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}
	
	public void deleteByArticleId(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
	}

}
