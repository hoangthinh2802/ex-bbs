package com.example.domain;

/**
 * コメント情報を扱うドメイン
 * 
 * @author hvthinh
 *
 */
public class Comment {
	/**コメントID */
	private Integer id;
	/**名前 */
	private String name;
	/**内容 */
	private String content;
	/** アリストのID*/
	private String articleId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
