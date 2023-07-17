package ru.spring.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// Класс контроллер отмечается аннотацией @Controller
@Controller
public class HelloController {

    // В данной аннотации пишется URL который приведет к данному методу
    // когда пользователь введет запрос в браузере /hello-world, то этот запрос обработает DispatcherServlet
    // и направит его на правильный контроллер. Тоесть сюда, и тригернет метод отмеченный
    // аннотацией @GetMapping() c запросом "/hello-world"
    // метод в свою очередь может сделать все что угодно:
    // -    перенаправлять пользователя куда угодно
    // -    обращаться к модели
    // -    доставть данные из БД
    // -    показывать эти данные пользователю
    // -    все что угодно
    // Но в данном случае, метод вернет представление (шаблон)
    @GetMapping("/hello-world")
    public String sayHello(){
        // представления хранятся в папке WEB-INF, благодаря настройке thymeleaf можно не писать полный адрес
        // также благодаря настройке thymeleaf можно не указывать расширение файла.
        // настройки thymeleaf указанны в web.xml
        return "hello_world";
    }
}
