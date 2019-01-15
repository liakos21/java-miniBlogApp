package gr.mini.blog.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Article")
public class Article extends BaseEntity {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String content;

    @ManyToOne
    @JoinColumn(name ="author_id")
    @Getter @Setter
    private User author;
}
