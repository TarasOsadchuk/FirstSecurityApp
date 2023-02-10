package ua.osadchuk.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.osadchuk.springcourse.FirstSecurityApp.models.Person;
import ua.osadchuk.springcourse.FirstSecurityApp.repositories.PeopleRepository;

@Service
public class RegistrationServices {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }
}
