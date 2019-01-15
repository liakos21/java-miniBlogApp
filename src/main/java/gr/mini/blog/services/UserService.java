package gr.mini.blog.services;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gr.mini.blog.models.User;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User findUserByUsername(String username) {
        return (User) em.createQuery("select u from User u where u.username = :username").setParameter("username", username).getSingleResult();
    }

    public void save(User user) {
        if (user.isPersisted()) {
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    public Optional<User> auth(String username, String password) {
        return Optional.ofNullable(em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
            .setParameter("username", username)
            .setParameter("password", password)
            .getSingleResult());
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }
}
