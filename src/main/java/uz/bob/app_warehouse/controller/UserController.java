package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.User;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.payload.UserDto;
import uz.bob.app_warehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll(){

    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable Integer id){

    }

    @PostMapping
    public Result add(@RequestBody UserDto userDto){

    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody UserDto userDto){

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

    }

}
