package mobi.audax.article_register.services;


import mobi.audax.article_register.models.Articles;
import mobi.audax.article_register.dtos.ArticlesDto;
import mobi.audax.article_register.repositories.ArticlesRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ArticlesService {
    
    final ArticlesRepository articlesRepository;
    //final UserService userService;

    public ArticlesService(ArticlesRepository articlesRepository /* , UserService userService*/){
        this.articlesRepository = articlesRepository;
        //this.userService = userService;
    }

    public List<Articles> findAll(){
        return articlesRepository.findAll();
    }

    public Articles findById(UUID id){
        Optional<Articles> articles = articlesRepository.findById(id);
        if(articles.isEmpty()){
            //throw new ArticlesNotFoundException();
        }
        return articles.get();
    }

    @Transactional
    public void save(ArticlesDto novoArticlesDto /* , UUID userId*/){
        //Users users = usuarioService.findById(userId);
        Articles articles = novoArticlesDto.convertToArticles();
        //articles.setUsers(users);
        articlesRepository.save(articles);
    }

    @Transactional
    public void update(UUID id, ArticlesDto novoArticlesDto){
        Optional<Articles> articles = articlesRepository.findById(id);
        if(articles.isEmpty()){
            //throw new ArticlsnotFoundException();
        }
        Articles antigoArticles = articles.get();
        antigoArticles.setTitle(novoArticlesDto.getTitle());
        antigoArticles.setResume(novoArticlesDto.getResume());
        antigoArticles.setText(novoArticlesDto.getText());
    }

    @Transactional
    public void delete(UUID id){
        if(articlesRepository.findById(id).isEmpty()){
            //throw new ArticlesNotFoundException();
        }
        articlesRepository.deleteById(id);
    }

}
