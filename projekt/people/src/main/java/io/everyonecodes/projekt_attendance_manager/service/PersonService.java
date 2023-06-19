package io.everyonecodes.projekt_attendance_manager.service;

import io.everyonecodes.projekt_attendance_manager.presitance.domain.Person;
import io.everyonecodes.projekt_attendance_manager.presitance.repository.PersonRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@ConfigurationProperties("app-service")
public class PersonService {

    private String roleStudent;
    private String roleEmployee;
    private String message;
    private String message2;
    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;

    public void setRoleStudent(String roleStudent) {
        this.roleStudent = roleStudent;
    }

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public PersonService(PersonRepository personRepository, PasswordEncoder encoder) {
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    public Person addStudent(String userName, String password) {
        Person person = new Person();
        var password1 = encoder.encode(password);
        person.setUserName(userName);
        person.setPassword(password1);
        person.setAuthorities(Collections.singleton(roleStudent));
        return personRepository.save(person);
    }

    public Person addEmployee(String userName, String password) {
        Person person = new Person();
        var password1 = encoder.encode(password);
        person.setUserName(userName);
        person.setPassword(password1);
        person.setAuthorities(Collections.singleton(roleEmployee));
        return personRepository.save(person);
    }

    public List<Person> getAllStudents() {
        return personRepository.getAllByAuthorities(roleStudent);
    }

    public List<Person> getAllEmployee() {
        return personRepository.getAllByAuthorities(roleEmployee);
    }

    /*public void deleteStudent(String name) {
        Optional<Person> person1 = personRepository.findOneByUserName(name);
        if (person1.get().getAuthorities().toString().equals(roleStudent)) {
            personRepository.delete(person1.get());
            System.out.println(person1.get().getUserName() + " " + message2);
        } else {
            System.out.println(message);
        }
    }*/
    public void deleteStudent(String name) {
        Optional<Person> person1 = personRepository.findOneByUserName(name);
        if (person1.isPresent()) {
            Person person = person1.get();
            if (!person.getAuthorities().toString().equals(roleEmployee)) {
                personRepository.delete(person);
                System.out.println(person.getUserName() + " " + message2);
            } else {
                System.out.println(message);
            }
        } else {
            System.out.println("Person not found.");
        }
    }

    public void deleteEmployee(String name) {
        Optional<Person> person1 = personRepository.findOneByUserName(name);
        personRepository.delete(person1.get());
        System.out.println(person1.get().getUserName() + " " + message2);
    }

}
