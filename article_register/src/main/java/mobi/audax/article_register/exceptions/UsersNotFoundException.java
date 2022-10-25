package mobi.audax.article_register.exceptions;

    public class UsersNotFoundException extends RuntimeException{
        
        public UsersNotFoundException(){
            super("User não encontrado.");
    }
    
}