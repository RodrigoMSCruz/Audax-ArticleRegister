package mobi.audax.ArticlesRegister.controllers;

import mobi.audax.ArticlesRegister.dtos.ArticlesDto;
import mobi.audax.ArticlesRegister.exceptions.ArticlesNotFoundException;
import mobi.audax.ArticlesRegister.models.Articles;
import mobi.audax.ArticlesRegister.services.ArticlesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping("/{id}")
    public ResponseEntity<String> save(@PathVariable(value = "id") Long id, @RequestBody ArticlesDto articlesNovo){
        articlesService.save(id, articlesNovo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Artigo cadastrado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") Long articlesId, @RequestBody ArticlesDto articlesAtualizar){
        try{
            articlesService.update(articlesId, articlesAtualizar);    
        }
        catch(ArticlesNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Artigo atualizado com sucesso.");    
    }

    @DeleteMapping("{/id}")
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
