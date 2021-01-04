package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DrinkRepository drinkRepository;

//    private static Map<Integer, User> usersRegister = new HashMap<>();
//
//    static {
//    User user1 = new User();
//    user1.setName("Mark");
//    user1.setActive(true);
//    user1.setAdult(false);
//    user1.setPocket(89.5);
//        usersRegister.put(user1.getId(), user1);
//    userRepository.save(user1);
//
//    User user2 = new User();
//    user2.setName("Lina");
//    user2.setActive(false);
//    user2.setAdult(true);
//    user2.setPocket(54.7);
//}
//        usersRegister.put(user2.getId(), user2);
//    }
//
//    private static Map<Integer, Drink> drinkMenu = new HashMap<>();
//    static {
//        Drink drink1 = new Drink();
//        drink1.setId(1);
//        drink1.setProductName("Vodka");
//        drink1.setForAdult(true);
//        drink1.setPrice(4.0);
//        drinkMenu.put(drink1.getId(), drink1);
//    }

    @RequestMapping(value = "/users")
//    public List<User> getUsers() {

//        return userRepository.findAll();
//        return new ResponseEntity<>(usersRegister.values(), HttpStatus.OK);
    public ResponseEntity<Collection<User>> getUser() {
        User user1 = new User();
        user1.setName("Mark");
        user1.setActive(true);
        user1.setAdult(false);
        user1.setPocket(89.5);
        userRepository.save(user1);
        Collection<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") Integer id) {
//        User user = usersRegister.get(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
        User user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/drink-menu")
    public ResponseEntity<Collection<Drink>> getDrinkMenu() {
        Drink drink1 = new Drink();
        drink1.setProductName("Vodka");
        drink1.setForAdult(true);
        drink1.setPrice(4.0);
        drinkRepository.save(drink1);
        Collection<Drink> drinks = drinkRepository.findAll();
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
}
