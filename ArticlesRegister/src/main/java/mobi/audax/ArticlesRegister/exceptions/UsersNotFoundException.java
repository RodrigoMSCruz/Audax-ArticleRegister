package mobi.audax.ArticlesRegister.exceptions;

    public class UsersNotFoundException extends RuntimeException{
        
        public UsersNotFoundException(){
            super("User não encontrado.");
    }
    
}