package ru.spring.example.config;


// Это будет конфиг Java класс,

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

 // данная аннотация значит, что данное приложение является SpringMVC приложение, не просто приложение
 // @EnableWebMvc - Эта аннотация равносильна <mvc:annotation-driven/> в xml конфигурации
// интерфейс WebMvcConfigurer реализуется в том случае если мы хотим под себя настроить Spring MVC
// В нашем случае нам нужно вместо стандартного шаблонизатора, использовать шаблонизатор thymeleaf
// поэтому мы реализуем интерфейс WebMvcConfigurer в спринг конфиге, и реализуем его метод
// configureViewResolvers() в котором мы задаем шаблонизатор, в нашем случае thymeleaf
@Configuration
@ComponentScan("ru.spring.example")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    // Через конструктор с помощью аннотации @Autowired, внедряем зависимость от ApplicationContext
    // ApplicationContext будет внедрен спрингом за нас.
    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // Выше внедренный ApplicationContext используем в бине templateResolver() для настройки thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/"); // как в xml конфигурации, задаем папку для представлений
        templateResolver.setSuffix(".html"); // расширение для представлений
        return templateResolver;
    }

    // создаем бин templateEngine(), где мы производим конфигурацию наших представлений
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // метод ниже от интерфейса
    // В этом методе мы передаем Spring MVC информацию о том что мы хотим использовать шаблонизатор thymeleaf
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    // Конфигурация в этом файле полностью эквивалентна конфигу xml
    // Конфиг на java
}
