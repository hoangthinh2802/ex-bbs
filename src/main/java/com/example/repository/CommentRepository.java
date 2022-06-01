package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントの情報を扱うリポジトリ
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
	
	public List<Comment> fillAllComment(Integer articleId){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,content,article_id FROM " + TABLE_COMMENTS + " WHERE article_id =:articleId ORDER BY id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		List<Comment> commentList =  template.query(sql.toString(), param,COMMENT_ROW_MAPPER);
		return commentList;
	}
}
