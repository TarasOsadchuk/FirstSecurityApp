package ua.osadchuk.springcourse.FirstSecurityApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.osadchuk.springcourse.FirstSecurityApp.models.Person;
import ua.osadchuk.springcourse.FirstSecurityApp.services.RegistrationServices;
import ua.osadchuk.springcourse.FirstSecurityApp.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationServices registrationServices;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationServices registrationServices) {
        this.personValidator = personValidator;
        this.registrationServices = registrationServices;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationServices.register(person);
        return "redirect:/auth/login";
    }
}
