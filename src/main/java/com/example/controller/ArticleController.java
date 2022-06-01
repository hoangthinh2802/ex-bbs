package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

/**
 * 記事情報を扱うコントローラ
 * 
 * @author hvthinh
 *
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事情報一覧を出力する処理を行う.
	 * 
	 * @param model
	 * @return 記事情報一覧画面
	 */
	@RequestMapping("/list")
	public String index(Model model) {
		List<Article> articleList = articleService.fillAll();
		for (Article article : articleList) {
			article.setCommentList(commentService.findByArticleID(article.getId()));
		}

		model.addAttribute("articleList", articleList);
		return "article/article-list";
	}

	/**
	 * 記事情報を投稿する.
	 * 
	 * @param form
	 * @param model
	 * @return 記事情報一覧画面
	 */
	@RequestMapping("/add")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.insert(article);
		return "redirect:/article/list";
	}

	/**
	 * コメントを投稿する.
	 * 
	 * @param form
	 * @param model
	 * @return 記事情報一覧画面
	 */
	@RequestMapping("/comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentService.insert(comment);
		return "redirect:/article/list";
	}

	/**
	 * 記事情報を削除する.
	 * 
	 * @param articleId
	 * @return 記事情報一覧画面
	 */
	@RequestMapping("/delete")
	public String deleteArticle(String articleId) {
		commentService.deleteByArticleId(Integer.parseInt(articleId));
		articleService.deleteById(Integer.parseInt(articleId));
		return "redirect:/article/list";
	}
}
