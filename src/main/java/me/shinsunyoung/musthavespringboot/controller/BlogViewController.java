package me.shinsunyoung.musthavespringboot.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.musthavespringboot.domain.Article;
import me.shinsunyoung.musthavespringboot.dto.ArticleListViewResponse;
import me.shinsunyoung.musthavespringboot.dto.ArticleViewResponse;
import me.shinsunyoung.musthavespringboot.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

}
