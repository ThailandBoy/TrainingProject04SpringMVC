package ru.spring.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @GetMapping()
    public String index(Model model) {
        // всех людей из ДАО, и передадим их на представление в thymeleaf
        // список людей будем передавать через Модель
        return null;
    }

    // в GetMapping, мы поместим любое число. С помошью @PathVariable мы извлечем этот id из URL
    // и получим к нему доступ, внутри этого метода
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // Получим одного человека из ДАО и передадим этого человека на отображение в представление
        return null;
    }
}
