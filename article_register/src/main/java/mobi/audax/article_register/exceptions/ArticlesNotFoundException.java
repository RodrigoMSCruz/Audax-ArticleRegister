package mobi.audax.article_register.exceptions;

public class ArticlesNotFoundException extends RuntimeException{
    
    public ArticlesNotFoundException(){
        super("Article não encontrado.");
    }
    
}