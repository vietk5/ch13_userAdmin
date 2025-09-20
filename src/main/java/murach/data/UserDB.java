// src/main/java/murach/data/UserDB.java
package murach.data;

import jakarta.persistence.*;
import murach.business.User;

import java.util.List;

public class UserDB {

    public static List<User> selectUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM User u ORDER BY u.lastName, u.firstName",
                    User.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public static User selectUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void updateUser(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User existing = em.find(User.class, user.getUserId());
            if (existing != null) {
                existing.setFirstName(user.getFirstName());
                existing.setLastName(user.getLastName());
                // email giữ nguyên
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteUser(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User attached = em.find(User.class, user.getUserId());
            if (attached != null) {
                em.remove(attached);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
