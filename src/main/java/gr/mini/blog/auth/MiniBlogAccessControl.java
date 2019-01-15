package gr.mini.blog.auth;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

import com.vaadin.cdi.access.AccessControl;

@Alternative
@Priority(Interceptor.Priority.APPLICATION + 10)
public class MiniBlogAccessControl extends AccessControl {

    @Inject
    private UserInfo userInfo;

    @Override
    public boolean isUserSignedIn() {
        return userInfo.getUser() != null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return true;
    }

    @Override
    public String getPrincipalName() {
        return isUserSignedIn() ? userInfo.getUser().getUsername() : "";
    }
}
