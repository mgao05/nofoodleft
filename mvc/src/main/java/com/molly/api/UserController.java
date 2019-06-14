package com.molly.api;
import com.molly.domain.LoginInfo;
import com.molly.domain.ResponseToken;
import com.molly.domain.User;
import com.molly.extend.security.JwtTokenUtil;
import com.molly.repository.UserRepository;
import com.molly.service.UserService;
import com.molly.service.jms.MessageSQSService;
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
    private MessageSQSService messageSQSService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam(value = "user_id") Long userId){
        messageSQSService.sendMessage(String.valueOf(userId),1);
    }

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
    public List<User> findByFirstName (@RequestParam("firstName") String firstName) throws NullPointerException, NotFoundException{
        logger.debug(firstName);
        List<User> domainUsers = null;
        try{
            domainUsers = userService.findByFirstName(firstName);
            return domainUsers;
        }catch (Exception repositoryProblem){
            logger.debug("There is exception here!");
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
        public List<User> findByLastName (@RequestParam("lastName") String lastName) throws NullPointerException, NotFoundException{
        logger.debug(lastName);
        List<User> domainUsers = null;
        try{
            domainUsers = userService.findByLastName(lastName);
            return domainUsers;
        }catch (Exception repositoryProblem){
            logger.debug("Got exception");
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = {"email"})
    public User findByEmail (@RequestParam("email") String email) throws NullPointerException, NotFoundException{
        logger.debug("trying to find user by email" + email);
        User domainUser = null;
        try{
            domainUser = userService.findByEmail(email);
            return domainUser;
        }catch(Exception repositoryProblem){
            logger.debug("catch Exception");
            return null;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginInfo requestEntity){
        logger.info("username" + requestEntity.getUsername() + "is trying to login");
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    requestEntity.getUsername(),
                    requestEntity.getPassword()
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            try {
                final UserDetails userDetails = userService.findByUsername(requestEntity.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                ResponseToken responseToken = new ResponseToken(token);
//                responseToken.setToken(token);
                return ResponseEntity.ok(responseToken);
          //      return ResponseEntity.ok(new JwtAuthenticationResponse(token), HttpStatus.OK);
            }
            catch (Exception e){
                logger.error("System can't find user by email or username",e);
                return null;
            }
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
