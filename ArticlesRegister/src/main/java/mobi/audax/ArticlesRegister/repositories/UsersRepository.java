package mobi.audax.ArticlesRegister.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mobi.audax.ArticlesRegister.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    
}
