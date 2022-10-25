package mobi.audax.article_register.repositories;

import mobi.audax.article_register.models.Articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, UUID> {
    
}
