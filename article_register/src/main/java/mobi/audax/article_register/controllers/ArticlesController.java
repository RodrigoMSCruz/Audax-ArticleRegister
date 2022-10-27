package mobi.audax.article_register.controllers;

import mobi.audax.article_register.dtos.ArticlesDto;
import mobi.audax.article_register.exceptions.ArticlesNotFoundException;
import mobi.audax.article_register.models.Articles;
import mobi.audax.article_register.services.ArticlesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    final ArticlesService articlesService;
    
    public ArticlesController(ArticlesService articleService){
        this.articlesService = articleService;
    }
    

    @GetMapping
    public List<Articles> findAll(){
        return articlesService.findAll();
    }

    @PostMapping("{id}")
    public ResponseEntity<String> save(@PathVariable(value = "id") Long articlesId, @RequestBody ArticlesDto articlesNovo){
        articlesService.save(articlesNovo, articlesId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Artigo cadastrado com sucesso.");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") Long articlesId, @RequestBody ArticlesDto articlesAtualizar){
        try{
            articlesService.update(articlesId, articlesAtualizar);    
        }
        catch(ArticlesNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Artigo atualizado com sucesso.");    
    }

    @DeleteMapping(value = "{/id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long articlesId){
        try{
            articlesService.delete(articlesId);
        }
        catch(ArticlesNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();    
    }

}
