package com.molly.api;
import com.molly.domain.User;
import com.molly.extend.security.JwtTokenUtil;
import com.molly.repository.UserRepository;
import com.molly.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(method = RequestMethod.POST)
    public User generateUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("List users");
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id){
        logger.debug("path variable:" +id);
        return userService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"firstName"})
    public List<User> findByFirstName (@RequestParam("firstName") String firstName){
        logger.debug(firstName);
        return userService.findByFirstName(firstName);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
        public List<User> findByLastName (@RequestParam("lastName") String lastName){
        logger.debug(lastName);
        return userService.findByLastName(lastName);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"email"})
    public List<User> findByEmail (@RequestParam("email") String email){
        logger.debug(email);
        return userService.findByFirstName(email);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        logger.info("username" + username + "password" + password);
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    username,
                    password
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            SecurityContextHolder.getContext().setAuthentication(authentication);

//            try {
                final UserDetails userDetails = userService.findByUsername(username);
                final String token = jwtTokenUtil.generateToken(userDetails);
                return token;
//                return ResponseEntity.ok(new JwtAuthenticationResponse(token), HttpStatus.OK);
//            }
//            catch (Exc e){
//                logger.error("System can't find user by email or username",e);
//                return null;
//            }
           }
        catch (AuthenticationException ex){
        logger.error("error message",ex);
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure, please check your username and password");
        }
      return null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User getUser(@RequestBody User user){
        logger.debug("test");
        return userService.createUser(user);
    }




}
