package gr.mini.blog.auth;

import com.vaadin.cdi.NormalUIScoped;
import gr.mini.blog.models.User;
import lombok.Getter;
import lombok.Setter;

@NormalUIScoped
public class UserInfo {

    @Getter @Setter
    private User user;
}
