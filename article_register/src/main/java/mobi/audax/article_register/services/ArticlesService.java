package mobi.audax.article_register.services;


import mobi.audax.article_register.models.Articles;
import mobi.audax.article_register.models.Users;
import mobi.audax.article_register.dtos.ArticlesDto;
import mobi.audax.article_register.exceptions.ArticlesNotFoundException;
import mobi.audax.article_register.repositories.ArticlesRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticlesService {
    
    final ArticlesRepository articlesRepository;
    final UsersService usersService;

    public ArticlesService(ArticlesRepository articlesRepository , UsersService usersService){
        this.articlesRepository = articlesRepository;
        this.usersService = usersService;
    }

    public List<Articles> findAll(){
        return articlesRepository.findAll();
    }
    
     
    public Articles findById(long id){
        Optional<Articles> articles = articlesRepository.findById(id);
        if(articles.isEmpty()){
            throw new ArticlesNotFoundException();
        }
        return articles.get();
    }
    
    @Transactional
    public void save(ArticlesDto novoArticlesDto, Long userId){
        Users users = usersService.findById(userId);
        Articles articles = novoArticlesDto.convertToArticles();
        articles.setUsers(users);
        articlesRepository.save(articles);
    }

    @Transactional
    public void update(Long id, ArticlesDto novoArticlesDto){
        Optional<Articles> articles = articlesRepository.findById(id);
        if(articles.isEmpty()){
            throw new ArticlesNotFoundException();
        }
        Articles antigoArticles = articles.get();
        antigoArticles.setTitle(novoArticlesDto.getTitle());
        antigoArticles.setResume(novoArticlesDto.getResume());
        antigoArticles.setText(novoArticlesDto.getText());
    }

    @Transactional
    public void delete(Long id){
        if(articlesRepository.findById(id).isEmpty()){
            throw new ArticlesNotFoundException();
        }
        articlesRepository.deleteById(id);
    }

}
