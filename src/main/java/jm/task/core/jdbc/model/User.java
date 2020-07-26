package jm.task.core.jdbc.model;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User  { //extends UserDaoHibernateImpl
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { id: " + id +
                " username: " + name +
                " lastname: " + lastName +
                " age: " + age + "}";
    }

//    @Override
//    public void createUsersTable() {
//
//    }
//
//    @Override
//    public void dropUsersTable() {
//
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//
//    }
//
//    @Override
//    public void removeUserById(long id) {
//
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//
//    }
}
