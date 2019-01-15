package gr.mini.blog.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AuthEvent {

    public enum Type { LOGIN, LOGOUT };

    @Getter
    private Type type;
}
