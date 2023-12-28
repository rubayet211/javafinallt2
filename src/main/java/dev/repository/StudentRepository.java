package dev.repository;

import dev.domain.Gender;
import dev.domain.Student;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory ){
        this.sessionFactory = sessionFactory;
    }

    public void createStudent(Student student) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.save(student);

    }

    public List<Student> findAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> userQuery= session.createQuery("from Student", Student.class);
        return userQuery.getResultList();
    }
    public Student findById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    public void updateStudentById(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    public void deleteById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Student student = findById(id);
        session.delete(student);
    }



    public long getStudentCount() {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Student").uniqueResult();
        return count != null ? count : 0;
    }



}
