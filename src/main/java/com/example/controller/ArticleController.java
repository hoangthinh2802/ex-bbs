package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.service.ArticleService;

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

	/**
	 * 記事情報一覧を出力する処理を行う.
	 * 
	 * @param model
	 * @return 記事情報一覧画面
	 */
	@RequestMapping("/list")
	public String index(Model model) {
		List<Article> articleList = articleService.fillAll();
		model.addAttribute("articleList", articleList);

		return "article/article-list";
	}
	
//	@RequestMapping("/detail")
//	public String 
}
