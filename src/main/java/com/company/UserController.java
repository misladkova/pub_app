package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    OrderRepository orderRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
//    {
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }


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
    public ResponseEntity<Collection<User>> getUser() {
//        https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot/31160173
        String details = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("aaa " + details);
        Collection<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @RequestMapping(value = "/drink-menu")
    public ResponseEntity<Collection<Drink>> getDrinkMenu() {
        Collection<Drink> drinks = drinkRepository.findAll();
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    @RequestMapping(value = "/drink-menu/add", method = RequestMethod.POST)
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        drinkRepository.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        String currentUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUsername);
        User user = userRepository.findByUsername(currentUsername);
        System.out.println(user);
        Drink drink = drinkRepository.findByProductName(order.getProductName());
        System.out.println(drink);
        Double sumPrice = drink.getPrice()*order.getAmount();
        System.out.println(sumPrice);
        if(user.getPocket()<sumPrice) {
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        if(drink.isForAdult() && !user.getIsAdult()){
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        order.setUser(user);
//        order.setDrink(drink);
        order.setPrice(sumPrice);
        user.setPocket(user.getPocket()-sumPrice);
        orderRepository.save(order);
        return new ResponseEntity<>(new Order(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/summary/all")
    public ResponseEntity<Collection<Order>> getOrders(){
        Collection<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/summary/product/{id}")
    public ResponseEntity<Collection<OrderProductDTO>> getDrinkSummary(@PathVariable("id") Integer id) {
        Collection<Order> orders = orderRepository.findByDrinkId(id);
        System.out.println(orders);

        ModelMapper modelMapper = new ModelMapper();

        List<OrderProductDTO> ordersDTO = new ArrayList<>();
        for (Order o: orders){
            OrderProductDTO orderDTO = modelMapper.map(o, OrderProductDTO.class);
            System.out.println(orderDTO);
            ordersDTO.add(orderDTO);
        }
        System.out.println(ordersDTO);

        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/summary/user/{id}")
    public ResponseEntity<Collection<OrderUserDTO>> getUserSummary(@PathVariable("id") Integer id){
        Collection<Order> orders = orderRepository.findByUserId(id);

        ModelMapper modelMapper = new ModelMapper();

        List<OrderUserDTO> ordersDTO = new ArrayList<>();
        for (Order o: orders){
            OrderUserDTO orderDTO = modelMapper.map(o, OrderUserDTO.class);
            System.out.println(orderDTO);
            ordersDTO.add(orderDTO);
        }

        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }
}
