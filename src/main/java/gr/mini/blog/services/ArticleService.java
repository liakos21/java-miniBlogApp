package gr.mini.blog.services;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;

import gr.mini.blog.auth.UserInfo;
import gr.mini.blog.models.Article;

@Stateless
public class ArticleService {

    @Inject
    private UserInfo userInfo;

    @PersistenceContext
    private EntityManager em;

    public void save(Article article) {
        if (article.isPersisted()) {
            em.merge(article);
        } else {
            article.setAuthor(userInfo.getUser());
            em.persist(article);
        }
    }

    public void delete(Article article) {
        em.remove(em.contains(article) ? article : em.merge(article));
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a").getResultList();
    }
}
