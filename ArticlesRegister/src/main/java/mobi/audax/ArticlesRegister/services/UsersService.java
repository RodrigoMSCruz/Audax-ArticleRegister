package mobi.audax.ArticlesRegister.services;

import mobi.audax.ArticlesRegister.models.Users;
import mobi.audax.ArticlesRegister.dtos.UsersDto;
import mobi.audax.ArticlesRegister.repositories.UsersRepository;
import mobi.audax.ArticlesRegister.exceptions.UsersNotFoundException;

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
    public void update(Long id, UsersDto novoUsersDto){
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
