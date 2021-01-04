package com.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    private static Map<String, User> usersRegister = new HashMap<>();
    static {
        User user1 = new User();
        user1.setId("1");
        user1.setName("Mark");
        user1.setActive(true);
        user1.setAdult(false);
        user1.setPocket(89.5);
        usersRegister.put(user1.getId(), user1);

        User user2 = new User();
        user2.setId("2");
        user2.setName("Lina");
        user2.setActive(false);
        user2.setAdult(true);
        user2.setPocket(54.7);
        usersRegister.put(user2.getId(), user2);
    }

    @RequestMapping(value = "/users")
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(usersRegister.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") String id){
        User user = usersRegister.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
