package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                                                            .addAnnotatedClass(User.class)
                                                                            .buildSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (`id` INT NOT NULL AUTO_INCREMENT," +
                                                                        "`name` VARCHAR(45) NOT NULL," +
                                                                        "`lastName` VARCHAR(45) NOT NULL," +
                                                                        "`age` INT NOT NULL,PRIMARY KEY (`id`)," +
                                                                        "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table created");

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table dropped");

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.getCurrentSession()){

            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("User with id " + id + " removed");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = List.of();

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            userList = session.createQuery("from User").list();
            session.getTransaction().commit();
            System.out.println("Users list loaded");

        } catch (Exception e) {

            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.createNativeQuery("DELETE FROM user").executeUpdate();
            System.out.println("Table cleaned");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
