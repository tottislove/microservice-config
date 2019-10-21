package com.xc.domain;

import com.xc.vo.UserTableVo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author totti
 * @date 2019/10/21.
 */
@Entity
@Table(name = "user")
public class User {

    private int id;
    private String name;
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public UserTableVo toVo() {
        UserTableVo vo = new UserTableVo();
        vo.setAge(this.getAge());
        vo.setId(this.getId());
        vo.setName(this.getName());
        return vo;
    }
}
