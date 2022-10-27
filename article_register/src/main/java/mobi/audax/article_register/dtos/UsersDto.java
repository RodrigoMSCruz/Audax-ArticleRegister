package mobi.audax.article_register.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import mobi.audax.article_register.models.Users;

@Getter
@Setter
public class UsersDto {
    @NotBlank(message = "O nome de usuário não pode ser vazio")
    @Size(min = 3, max = 150)
    private String username;
    @NotBlank(message = "O campo de senha não pode ser vazio")
    @Size(min = 8)
    private String password;

    public Users convertToUsers(){
        return new Users(username,password);
    }
    
}
