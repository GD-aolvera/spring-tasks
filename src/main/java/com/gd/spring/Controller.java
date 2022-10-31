package com.gd.spring;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private List<String> names = new ArrayList<>();

    @GetMapping
    public String handleRequest(){
        return "This is our first Spring app! Check /names for CRUD functionality with Swagger at /swagger-ui/index.html";
    }

    @GetMapping("/names")
    public String printNameList(){
        return names.toString();
    }

    @PostMapping("/names")
    public String hello(@RequestBody String name){
        names.add(name);
        return "Added name: " + name + " to the list!";
    }

    @PutMapping("/names/{index}")
    public String updateName(@RequestBody String name, @PathVariable int index) {
        String previousName = names.get(index);
        names.set(index, name);
        return "Updated name " + previousName + " with " + name + " at index " + index;
    }

    @DeleteMapping("/names/{index}")
    public String deleteName(@PathVariable int index) throws IndexOutOfBoundsException {
        names.remove(index);
        return "removed " + names.get(index);
    }

}
