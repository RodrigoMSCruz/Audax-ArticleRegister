package mobi.audax.article_register.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ARTICLES")
@NoArgsConstructor
public class Articles{
    private int id;
    @Id
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
    private String slug;
    @Column(nullable = false)
    private LocalDateTime registeredAt;

    public int getId() {
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

    public Articles(String title, String resume, String text) {
        this.title = title;
        this.resume = resume;
        this.text = text;
        this.slug = title.replaceAll(" ", "-");
        this.registeredAt = LocalDate.now().atStartOfDay();
    }

}
