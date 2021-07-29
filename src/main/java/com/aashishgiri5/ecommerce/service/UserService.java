package com.aashishgiri5.ecommerce.service;

import com.aashishgiri5.ecommerce.dto.LoginRequest;
import com.aashishgiri5.ecommerce.dto.UserRequest;
import com.aashishgiri5.ecommerce.exception.BadException;
import com.aashishgiri5.ecommerce.model.Token;
import com.aashishgiri5.ecommerce.model.User;
import com.aashishgiri5.ecommerce.repo.TokenRepository;
import com.aashishgiri5.ecommerce.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MailSenderTest mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserServiceDetails myUserServiceDetails;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${spring.userinput.activationlink}")
    private String activationLink;

    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setIsenabled(false);
        user.setUserName(userRequest.getEmail());
        user.setFirstName((userRequest.getFirst_name()));
        user.setLastName((userRequest.getLast_name()));
        String encodedPassword = encodePassword(userRequest.getPassword());
        user.setPassword(encodedPassword);
//        System.out.println();
        log.info("this is user {}",user);

        userRepository.save(user);
        String tkn =generateToken(user);
        String link= activationLink+tkn;
        mailSender.sendMail("Please activate your account",link,user.getUserName());
    }



    private String encodePassword(String password)
    {
        return passwordEncoder.encode(password);
    }


    private String generateToken(User user) {
        String uniqueToken = UUID.randomUUID().toString();
        Token token=new Token();
        token.setToken(uniqueToken);
        token.setUser(user);
//      expire dte will be task for bug
        tokenRepository.save(token);
        return uniqueToken;
    }

    public Optional<User> getUserById(int userRequest) {
        return userRepository.findById(userRequest);
    }



    public void updateUser(Optional<User> user, UserRequest userRequest) {
        user.get().setUserName(userRequest.getEmail());
        user.get().setPassword(userRequest.getPassword());
        user.get().setFirstName(userRequest.getFirst_name());
        user.get().setLastName(userRequest.getLast_name());
        userRepository.save(user.get());
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    public void verify(String id) throws BadException {
        Optional<Token> token=tokenRepository.findByToken(id);
        token.orElseThrow(()->new BadException("invalid token"));
        token.ifPresent(t-> {
            try {
                fetchUserAndEnabled(token.get().getUser());
            } catch (BadException e) {
                e.printStackTrace();
            }
        });
    }

    private void fetchUserAndEnabled(User user) throws BadException {
        Optional<User> optionalUser=Optional.ofNullable(userRepository.findByUserName(user.getUserName())).orElseThrow(()->new BadException("user not found"));
        if(optionalUser.isPresent()){
            optionalUser.get().setIsenabled(true);
            userRepository.save(optionalUser.get());
        }
    }

    public String login(LoginRequest loginRequest) throws BadException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        }
        catch (AuthenticationException ex)
        {
            throw new BadException("Incorrect Credentials");
        }
        UserDetails userDetails=myUserServiceDetails.loadUserByUsername(loginRequest.getEmail());
        String jwToken=jwtUtils.generateToken(userDetails);
        return jwToken;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByUserName(email);
    }

    public User findByPassword(String password) {
        return userRepository.findByPassword(password);
    }
}
