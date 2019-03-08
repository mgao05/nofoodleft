package com.molly.api;

import com.molly.domain.User;
import com.molly.repository.UserRepository;
import com.molly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/users", "/api/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User generateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("List users");
        return userRepository.findAll();

        }
    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
        public List<User> generateUser (@RequestParam("lastName") String lastName){
        logger.debug(lastName);
        return userService.findByLastName(lastName);



    }
}
