package com.enosh.multi.controllers;

import com.enosh.multi.entities.Person;
import com.enosh.multi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/person")
public class PersonController {

    private PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/form")
    public String form() {
        return "person/form";
    }

    @PostMapping("/hello")
    public String hello(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("fn");
        String lastName = request.getParameter("ln");
        String password = request.getParameter("pwd");

        Person person = new Person(firstName, lastName, password);
        Person afterSave = repository.save(person);

        model.addAttribute(
                "greetings",
                "Hello " + afterSave.getFirstName() + "-" + afterSave.getLastName() +
                        " Your id is: " + afterSave.getId()
        );


        return "person/hello";
    }
}
