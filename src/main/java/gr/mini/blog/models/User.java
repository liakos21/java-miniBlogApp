package gr.mini.blog.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User extends BaseEntity {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "author")
    @Getter @Setter
    private List<Article> articles;
}
