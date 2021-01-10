package com.company;

import com.company.drink.Drink;
import com.company.drink.DrinkDTO;
import com.company.drink.DrinkRepository;
import com.company.order.*;
import com.company.user.User;
import com.company.user.UserDTO;
import com.company.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;

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

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection<UserDTO>> getUsers() {
//        https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot/31160173
        String details = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("aaa " + details);
        Collection<User> users = userRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            UserDTO userDTO = modelMapper.map(u, UserDTO.class);
            System.out.println(userDTO);
            usersDTO.add(userDTO);
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();

        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @RequestMapping(value = "/drink-menu", method = RequestMethod.GET)
    public ResponseEntity<Collection<DrinkDTO>> getDrinkMenu() {
        Collection<Drink> drinks = drinkRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();

        List<DrinkDTO> drinksDTO = new ArrayList<>();
        for (Drink d : drinks) {
            DrinkDTO drinkDTO = modelMapper.map(d, DrinkDTO.class);
            System.out.println(drinkDTO);
            drinksDTO.add(drinkDTO);
        }

        return new ResponseEntity<>(drinksDTO, HttpStatus.OK);
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
        Double sumPrice = drink.getPrice() * order.getAmount();
        System.out.println(sumPrice);
        if (user.getPocket() < sumPrice) {
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        if (drink.isForAdult() && !user.getIsAdult()) {
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        order.setUser(user);
        order.setDrink(drink);
        order.setPrice(sumPrice);
        user.setPocket(user.getPocket() - sumPrice);
        orderRepository.save(order);
        return new ResponseEntity<>(new Order(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/summary/all", method = RequestMethod.GET)
    public ResponseEntity<List<OrderSumDTO>> getOrders() {
        Collection<Order> orders = orderRepository.findAll();

        HashMap<Integer, List<OrderSumDTO>> orders1 = new HashMap<>();
        for (Order o : orders) {
            Drink drink = o.getDrink();
            System.out.println(drink);
            int drinkId = drink.getId();
            System.out.println(drinkId);

            ModelMapper modelMapper = new ModelMapper();
            OrderSumDTO orderDTO = modelMapper.map(o, OrderSumDTO.class);
            System.out.println(orderDTO);
            orderDTO.setUnitPrice(drink.getPrice());
            if(orders1.containsKey(drinkId)){
                orders1.get(drinkId).add(orderDTO);
            }else{
                List<OrderSumDTO> orderList = new ArrayList<>();
                orderList.add(orderDTO);
                orders1.put(drinkId, orderList);
            }
        }

        List<OrderSumDTO> sumOrders = new ArrayList<>();
        for(Integer i: orders1.keySet()){
            List<OrderSumDTO> a = orders1.get(i);
            OrderSumDTO order = new OrderSumDTO();
            int am = 0;
            double pr = 0.0;
            for(OrderSumDTO o: a){
                order.setProductName(o.getProductName());
                order.setUnitPrice(o.getUnitPrice());
                am+=o.getAmount();
                pr+=o.getPrice();
            }
            order.setPrice(pr);
            order.setAmount(am);
            sumOrders.add(order);
        }

        return new ResponseEntity<>(sumOrders, HttpStatus.OK);
    }

    @RequestMapping(value = "/summary/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderProductDTO>> getDrinkSummary(@PathVariable("id") Integer id) {
        Collection<Order> orders = orderRepository.findByDrinkId(id);
        System.out.println(orders);

        ModelMapper modelMapper = new ModelMapper();

        List<OrderProductDTO> ordersDTO = new ArrayList<>();
        for (Order o : orders) {
            OrderProductDTO orderDTO = modelMapper.map(o, OrderProductDTO.class);
            System.out.println(orderDTO);
            ordersDTO.add(orderDTO);
        }
        System.out.println(ordersDTO);

        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/summary/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderUserDTO>> getUserSummary(@PathVariable("id") Integer id) {
        Collection<Order> orders = orderRepository.findByUserId(id);

        ModelMapper modelMapper = new ModelMapper();

        List<OrderUserDTO> ordersDTO = new ArrayList<>();
        for (Order o : orders) {
            OrderUserDTO orderDTO = modelMapper.map(o, OrderUserDTO.class);
            System.out.println(orderDTO);
            ordersDTO.add(orderDTO);
        }

        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }
}
