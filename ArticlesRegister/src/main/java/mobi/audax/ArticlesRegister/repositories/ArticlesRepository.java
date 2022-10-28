package mobi.audax.ArticlesRegister.repositories;

import mobi.audax.ArticlesRegister.models.Articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    
}
