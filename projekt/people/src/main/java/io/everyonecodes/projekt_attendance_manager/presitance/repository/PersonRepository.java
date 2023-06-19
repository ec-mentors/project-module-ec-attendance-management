package io.everyonecodes.projekt_attendance_manager.presitance.repository;
import io.everyonecodes.projekt_attendance_manager.presitance.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByUserName(String userName);

    Optional<Person> findOneByUserName(String username);

    List<Person> getAllByAuthorities(String authorities);
}
