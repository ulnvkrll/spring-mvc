package ru.maxima.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.dao.PersonDAO;
import ru.maxima.model.Person;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAdminPage(Model model, @ModelAttribute("person") Person person) {
        List<Person> allPeople = personDAO.getAllPeople();
        model.addAttribute("allPeople", allPeople);
    return "admin-page";
    }

    @PostMapping()
    public String makeAdmin(@ModelAttribute("person") Person person) {
        personDAO.upgradeToAdmin(person);
        return "redirect:/people";
    }
}
