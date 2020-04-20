package com.volkodav4ik.dao;

import com.google.gson.Gson;
import com.volkodav4ik.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDao {

    private static final Gson gson = new Gson();

    public static String listToJson(List<User> list) {
        return gson.toJson(list);
    }

    public static String toJson(User user) {
        return gson.toJson(user);
    }

    public static User fromJson(String jsonStr) {
        return gson.fromJson(jsonStr, User.class);
    }

    public static User addUser(User user) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        }
        return user;
    }

    public static List<User> getAllUsers() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            List<User> list = session.createQuery("FROM User", User.class).list();
            session.close();
            return list;
        }
    }

    public static void updateUser(User user) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        }
    }

    public static void removeUserByName(String name) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> list = session
                    .createQuery("FROM User WHERE lower(name) = lower(:name)", User.class)
                    .setParameter("name", name)
                    .list();
            User user = list.get(0);
            session.delete(user);
            transaction.commit();
            session.close();
        }
    }

    public static void removeUser(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session
                    .createQuery("FROM User WHERE id = :id ", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.delete(user);
            transaction.commit();
            session.close();
        }
    }

    public static User getUser(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session
                    .createQuery("FROM User WHERE id = :id ", User.class)
                    .setParameter("id", id)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
            transaction.commit();
            session.close();
            return user;
        }
    }

    public static User getUserByName(String name) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session
                    .createQuery("FROM User WHERE lower(name) = lower(:name)", User.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
            transaction.commit();
            session.close();
            return user;
        }
    }

}
