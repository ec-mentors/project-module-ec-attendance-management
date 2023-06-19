package io.everyonecodes.projekt_attendance_manager.presitance.endpoint;

import io.everyonecodes.projekt_attendance_manager.presitance.domain.Person;
import io.everyonecodes.projekt_attendance_manager.service.PersonService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PersonEndpoint {

    private final PersonService personService;

    public PersonEndpoint(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/employees")
    @Secured("ROLE_ADMIN")
    public Person addEmployee(@RequestBody Map<String, String> requestBody) {
        String userName = requestBody.get("userName");
        String password = requestBody.get("password");

        return personService.addEmployee(userName, password);
    }

    @PostMapping("/students")
    @Secured("ROLE_EMPLOYEE")
    public Person addStudent(@RequestBody Map<String, String> requestBody) {
        String userName = requestBody.get("userName");
        String password = requestBody.get("password");

        return personService.addStudent(userName, password);
    }

    @DeleteMapping("/employees/{userName}")
    @Secured("ROLE_ADMIN")
    public void deleteEmployee(@PathVariable String userName) {
        personService.deleteEmployee(userName);
    }

    @DeleteMapping("/students/{userName}")
    @Secured("ROLE_EMPLOYEE")
    public void deleteStudent(@PathVariable String userName) {
        personService.deleteStudent(userName);
    }

    @GetMapping("/employees")
    @Secured("ROLE_ADMIN")
    public List<Person> getAllEmployees() {
        return personService.getAllEmployee();
    }

    @GetMapping("/students")
    @Secured("ROLE_EMPLOYEE")
    public List<Person> getAllStudents() {
        return personService.getAllStudents();
    }
}
