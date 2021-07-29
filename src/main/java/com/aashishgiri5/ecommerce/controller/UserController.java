package com.aashishgiri5.ecommerce.controller;

import com.aashishgiri5.ecommerce.dto.LoginRequest;
import com.aashishgiri5.ecommerce.dto.UserRequest;
import com.aashishgiri5.ecommerce.exception.BadException;
import com.aashishgiri5.ecommerce.model.User;
import com.aashishgiri5.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    public String helloworld()
    {
        return "helloooo";
    }

    @PostMapping(value = "/create", produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser (@RequestBody @Valid UserRequest userRequest, Errors errors)
    {   JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
//        one methods
        if(errors.hasErrors()){
          List<ObjectError> err= errors.getAllErrors();
          for(ObjectError objectError:err)
          {
              jsonArray.put(objectError.getDefaultMessage());
          }
          jsonObject.put("statusCode",404);
          jsonObject.put("error",jsonArray);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());

        }
//        other method
//        if (result.hasErrors()){
//            List<ObjectError> errors1= result.getAllErrors();
//            for (ObjectError error: errors1){
//
//            }
//        }
       Optional<User> user= userService.findByEmail(userRequest.getEmail());

        if(user.isPresent())
        {
            jsonObject.put("statusCode",409);
            jsonObject.put("error",userRequest.getEmail()+"Username already exists.");
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        }
        userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(buildCustomJson(HttpStatus.OK,"User created successfully").toString());
    }
    @GetMapping(value="/getUser/{userId}",produces = "application/json")
    public ResponseEntity<?> getUser (@PathVariable int userId)
   {
    JSONObject jsonObject=new JSONObject();
      Optional<User> user =userService.getUserById(userId);
      if (user.isPresent())
      {
          return ResponseEntity.status(HttpStatus.OK).body(user);
      }
       jsonObject.put("statuscode",404);
       jsonObject.put("message","User not found");
       return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
   }

   @PutMapping(value="/update/{userId}",produces="application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest, @PathVariable int userId)
   {
       Optional<User> user=userService.getUserById(userId);
       userService.updateUser(user,userRequest);
       JSONObject jsonObject=new JSONObject();
       jsonObject.put("statuscode",200);
       jsonObject.put("message","successfully updated");
       return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());

   }

   @DeleteMapping(value="/delete/{userId}",produces= "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable int userId)
   {
       Optional<User> user = userService.getUserById(userId);
       JSONObject jsonObject=new JSONObject();


       if(user.isPresent())
       {
           userService.deleteUserById(userId);

           jsonObject.put("statuscode",200);
           jsonObject.put("message","successfully deleted " +userId);
           return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
       }
       else{
           jsonObject.put("statuscode",404);
           jsonObject.put("message","User not found");
           return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
       }


   }

    private JSONObject buildCustomJson(HttpStatus httpStatus, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statuscode",httpStatus);
        jsonObject.put("message",message);
        return jsonObject;
    }

    @GetMapping(value="/verifyToken/{id}")
   public ResponseEntity<?>verifyToken(@PathVariable String id) throws BadException {
        userService.verify(id);
        return ResponseEntity.ok("Verified");
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest)throws Exception
    {
        String jwt=userService.login(loginRequest);
        return ResponseEntity.ok(jwt);
    }
}
