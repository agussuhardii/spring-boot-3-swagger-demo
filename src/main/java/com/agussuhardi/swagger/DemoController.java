package com.agussuhardi.swagger;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agussuhardi
 * @created 19/06/23/06/2023 :19.33
 * @project spring-boot-3-swagger-demo
 */

@RestController
@RequestMapping
public class DemoController {

    @GetMapping("/")
    public String base(){
        return "Hello";
    }
    @GetMapping("/user")
    public String anonymousUser(){
        return "hello anonymous";
    }
    @GetMapping("/user/{name}")
    public String user(@PathVariable String name){
        return "hello "+name;
    }
}
