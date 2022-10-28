package mobi.audax.ArticlesRegister.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ARTICLES")
@NoArgsConstructor
public class Articles{
    
    @Id
    @GeneratedValue
    private long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    public long getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
        this.slug = title.replaceAll(" ", "-");
    }
    
    public String getResume() {
        return resume;
    }
    
    public void setResume(String resume) {
        this.resume = resume;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getSlug() {
        return slug;
    }
    
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
    
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Articles(String title, String resume, String text) {
        this.title = title;
        this.resume = resume;
        this.text = text;
        this.slug = title.replaceAll(" ", "-");
        this.registeredAt = LocalDate.now().atStartOfDay();
    }

}
