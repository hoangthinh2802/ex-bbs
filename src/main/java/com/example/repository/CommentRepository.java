package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントの情報を扱うリポジトリ
 * 
 * @author hvthinh
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** DBテーブルネーム */
	private static final String TABLE_COMMENTS = "comments";

	/**
	 * コメントのローマッパー
	 */
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setArticleId(rs.getInt("article_id"));
		comment.setContent(rs.getString("content"));
		return comment;
	};

	/**
	 * idでコメントリストを取得する.
	 * 
	 * @param articleId
	 * @return コメントリスト
	 */
	public List<Comment> filndByArticleId(Integer articleId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,content,article_id FROM " + TABLE_COMMENTS
				+ " WHERE article_id =:articleId ORDER BY id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);

		List<Comment> commentList = template.query(sql.toString(), param, COMMENT_ROW_MAPPER);
		return commentList;
	}

	/**
	 * コメントを投稿する.
	 * 
	 * @param comment
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO " + TABLE_COMMENTS + "(name,content,article_id) VALUES(:name,:content,:articleId);");
		template.update(sql.toString(), param);
	}

	/**
	 * 記事のIDコメントを削除する.
	 * 
	 * @param articleId
	 */
	public void deleteByArticleId(Integer articleId) {
		System.out.println(articleId);
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		String sql = "DELETE FROM " + TABLE_COMMENTS + " WHERE article_id=:articleId";
		template.update(sql, param);
	}
}
