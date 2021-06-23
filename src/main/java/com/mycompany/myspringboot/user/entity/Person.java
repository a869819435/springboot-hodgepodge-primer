package com.mycompany.myspringboot.user.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@PropertySource(value = {"classpath:myperson.yml"})
@ConfigurationProperties(prefix = "person")
@Component
public class Person {
    private String name;

    private Integer age;

    private List<String> readBook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getReadBook() {
        return readBook;
    }

    public void setReadBook(List<String> readBook) {
        this.readBook = readBook;
    }
}
