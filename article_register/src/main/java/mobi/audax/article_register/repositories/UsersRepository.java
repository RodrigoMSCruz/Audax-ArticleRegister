package mobi.audax.article_register.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mobi.audax.article_register.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{
    
}
