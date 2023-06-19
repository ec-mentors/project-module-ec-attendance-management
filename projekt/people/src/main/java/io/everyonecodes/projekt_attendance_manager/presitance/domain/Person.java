package io.everyonecodes.projekt_attendance_manager.presitance.domain;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "userName", unique = true)
    private String userName;

    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "authorities")
    private Set<String> authorities;

    public Person(Long id, String userName, String password, Set<String> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(userName, person.userName) && Objects.equals(password, person.password) && Objects.equals(authorities, person.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, authorities);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
