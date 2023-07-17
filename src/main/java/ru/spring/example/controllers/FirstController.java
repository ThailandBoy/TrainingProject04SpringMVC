package ru.spring.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// теперь для перехода в /helloPage нужно писать /giga/helloPage
@Controller
@RequestMapping("/giga")
public class FirstController {

    // В этом методе хотим принимать get запросы

    // Если требуется только получить параметры из URL, используйте @RequestParam
    // но в этом случае требуется обязательно ввести параметры, иначе ошибка 400 bad request
    // чтобы убрать необходимость передачи параметров используйте @RequestParam(value = "name", required = false)
    @GetMapping("/helloPage")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname, Model model) {
        // строку ниже, сейчас добавим в модель
        // System.out.println("Hello " + name + surname);
        model.addAttribute("message", "Hello " + name + surname);

        // Хорошая практика отмечать название папки как в примере ниже. Тоесть helloPage будет лежать в папке first
        // по адресу WEB-INF/views/first
        return "first/helloPage";
    }

    // в HttpServletRequest содержится ВСЯ информация о запросе.
    // использовать HttpServletRequest лищь для того чтобы получить из него параметры, это плохая практика
    @GetMapping("/goodByePage")
    public String goodByePage(HttpServletRequest request) {
        // ?name=Igor&surname=Myasnov данным образом передаем параметры в URL
        // и получаем параметры из запроса через getParameter()
        // в запросе есть имя парметра и его значение.
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello! "+name+" "+surname);
        return "first/goodByePage";
    }
}
