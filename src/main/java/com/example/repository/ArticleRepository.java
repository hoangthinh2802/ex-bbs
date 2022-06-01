package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事の情報を扱うリポジトリ.
 * 
 * @author hvthinh
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** DBテーブルネーム */
	private static final String TABLE_ARTICLES = "articles";

	/**
	 * 記事情報のローマッパー.
	 */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};

	/**
	 * 記事情報をID順で全件取得する.
	 * 
	 * @return 記事一覧情報
	 */
	public List<Article> fillAll() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name,content  FROM " + TABLE_ARTICLES + " ORDER BY id");
		List<Article> articleList = template.query(sql.toString(), ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 記事情報を登録する.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO " + TABLE_ARTICLES + "(name,content) VALUES(:name,:content);");
		template.update(sql.toString(), param);
	}

	/**
	 * 記事情報を削除する.
	 * 
	 * @param id
	 */
	public void deleteById(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String sql = "DELETE FROM " + TABLE_ARTICLES + " WHERE id=:id";
		template.update(sql, param);
	}
}
