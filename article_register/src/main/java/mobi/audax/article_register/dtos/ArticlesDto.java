package mobi.audax.article_register.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import mobi.audax.article_register.models.Articles;

@Getter
@Setter
public class ArticlesDto {
    
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 30, max = 70)
    private String title;
    @NotBlank(message = "O assunto não pode ser vazio")
    @Size(min = 50, max = 100)
    private String resume;
    @NotBlank(message = "O texto não pode ser vazio")
    @Size(min = 200)
    private String text;

    public Articles convertToArticles(){
        return new Articles(this.title, this.resume, this.text);
    }
}
