package mobi.audax.article_register.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mobi.audax.article_register.dtos.UsersDto;
import mobi.audax.article_register.exceptions.UsersNotFoundException;
import mobi.audax.article_register.models.Users;
import mobi.audax.article_register.services.UsersService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    final UsersService usersService;

    public UsersController(UsersService usersServices){
        this.usersService = usersServices;
    }

    @GetMapping
    public List<Users> findAll(){
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UsersDto novoUsersDto){
        usersService.save(novoUsersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Novo usuário cadastrado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") Long id, @RequestBody UsersDto novoUsersDto){
        try{
            usersService.update(id, novoUsersDto);
        }
        catch(UsersNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dados do usuário atualizados com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            usersService.delete(id);
        }
        catch(UsersNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso.");
    }

}
