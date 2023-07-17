package ru.spring.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringDispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Этот абстрактный класс делает большую часть работы для нас
    // Нам нужно лишь добавить лишь некоторые ключевые настройки

    // Если бы мы наследовались от абстр класса WebApplicationInitializer для настройки диспатчера. У нас был
    // больший контроль над диспатчером, но пришлось бы писать гораздо больше кода чем при наследовании от
    // AbstractAnnotationConfigDispatcherServletInitializer.
    // AbstractAnnotationConfigDispatcherServletInitializer - опимизирует работу разработчика но контроля на сервлетом
    // меньше. Но работа удобнее.

    // в классы ниже, нужно передать конфиги
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }


    // Здесь нужно указать класс-конфигурацию которая заменит applicationContextMVC.xml
    // Вернулись после конфигурации SpringConfig - теперь вставляем конфиг в метод.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
        // Теперь Java класс который исполняет роль web.xml, он знает где находится SpringConfig.
    }

    // В методе getServletMapping нужно вернуть "/"
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
        // Это эквивалентно тегу, в файле web.xml
        // <servlet-mapping>
        //      <servlet-name>dispatcher</servlet-name>
        //      <url-pattern>/</url-pattern>
        // </servlet-mapping>
    }
    // теперь Java конфиг полностью эквивалентен web.xml
}
