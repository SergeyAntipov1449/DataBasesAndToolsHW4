package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;


//Создайте базу данных (например, SchoolDB).
// В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
// Настройте Hibernate для работы с вашей базой данных.
// Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
// Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
// Убедитесь, что каждая операция выполняется в отдельной транзакции.

public class Main
{
    public static void main(String[] args)
    {

        // Создание фабрики сессий
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class).buildSessionFactory())
        {

            //создаём сам курс
            Course course = new Course("SomeCourse1", 45);

            // Вставка данных
            insertCourse(sessionFactory, course);

            // Чтение данных
            readCourse(sessionFactory, course);

            // Обновление данных
            updateCourse(sessionFactory, course);

            // Удаление данных
            deleteCourse(sessionFactory, course);
        }
    }

    private static void insertCourse(SessionFactory sessionFactory, Course course)
    {
        try (Session session = sessionFactory.getCurrentSession())
        {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            System.out.println("Inserted");
        }
    }

    private static void readCourse(SessionFactory sessionFactory, Course course)
    {
        try (Session session = sessionFactory.getCurrentSession())
        {
            session.beginTransaction();
            Course readCourse = session.get(Course.class, course.getId());
            session.getTransaction().commit();
            System.out.println("Read");
        }
    }

    private static void updateCourse(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course updatecourse = session.get(Course.class, course.getId());
            course.setTitle("Новое название");
            course.setDuration(30);
            session.getTransaction().commit();
            System.out.println("Updated");
        }
    }

    private static void deleteCourse(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course deletedcourse = session.get(Course.class, course.getId());
            session.delete(deletedcourse);
            session.getTransaction().commit();
            System.out.println("Deleted");
        }
    }
}