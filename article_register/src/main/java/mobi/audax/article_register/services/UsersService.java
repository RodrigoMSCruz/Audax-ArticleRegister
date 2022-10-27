package mobi.audax.article_register.services;

import mobi.audax.article_register.models.Users;
import mobi.audax.article_register.dtos.UsersDto;
import mobi.audax.article_register.repositories.UsersRepository;
import mobi.audax.article_register.exceptions.UsersNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsersService {
    final UsersRepository usersRepository;
    final ArticlesService articlesService;

    public UsersService(UsersRepository usersRepository, ArticlesService articlesService){
        this.usersRepository = usersRepository;
        this.articlesService = articlesService;
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    public Users findById(Long id){
        Optional<Users> users = usersRepository.findById(id);
        if(users.isEmpty()){
            throw new UsersNotFoundException();
        }
        return users.get();
    }

    @Transactional
    public void save(UsersDto novoUsersDto){
        Users users = novoUsersDto.convertToUsers();
        usersRepository.save(users);
    }

    @Transactional
    public void update(Long id, Users novoUsersDto){
        Optional<Users> users = usersRepository.findById(id);
        if(users.isEmpty()){
            throw new UsersNotFoundException();
        }
        Users antigoUsers = users.get();
        antigoUsers.setUsername(novoUsersDto.getUsername());
        antigoUsers.setPassword(novoUsersDto.getPassword());
    }

    @Transactional
    public void delete(Long id){
        if (usersRepository.findById(id).isEmpty()){
            throw new UsersNotFoundException();
        }
        usersRepository.deleteById(id);
    }


}