package mobi.audax.ArticlesRegister.exceptions;

public class ArticlesNotFoundException extends RuntimeException{
    
    public ArticlesNotFoundException(){
        super("Article não encontrado.");
    }
    
}