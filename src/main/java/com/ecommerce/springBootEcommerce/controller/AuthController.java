package com.ecommerce.springBootEcommerce.controller;

import com.ecommerce.springBootEcommerce.exception.CustomerNotFoundException;

import com.ecommerce.springBootEcommerce.models.ERole;
import com.ecommerce.springBootEcommerce.models.Role;
import com.ecommerce.springBootEcommerce.models.User;
import com.ecommerce.springBootEcommerce.payload.request.LoginRequest;
import com.ecommerce.springBootEcommerce.payload.request.SignupRequest;
import com.ecommerce.springBootEcommerce.payload.response.JwtResponse;
import com.ecommerce.springBootEcommerce.payload.response.MessageResponse;
import com.ecommerce.springBootEcommerce.repository.RoleRepository;
import com.ecommerce.springBootEcommerce.repository.UserRepository;
import com.ecommerce.springBootEcommerce.security.jwt.JwtUtils;
import com.ecommerce.springBootEcommerce.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


  @Autowired
  @Qualifier("authenticationManagerBean")
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;



  @Autowired
  UserDetailsService userDetailsService;


//  @Autowired
//   private CustomerService customerService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;


  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(),
                        userDetails.getFirstName(),
                         userDetails.getLastName(),
                         userDetails.getContact(),
                         userDetails.getAddress(),
                         roles)
    );
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws RuntimeException {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getContact(),
            signUpRequest.getAddress()


    );


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }



 @GetMapping("/users")
 public ResponseEntity<List> getAllCustomer(){
    return  new ResponseEntity<List>((List)userRepository.findAll(), HttpStatus.OK);
 }




 @GetMapping("/user/{id}")
 @PreAuthorize("hasRole('USER')")
    public ResponseEntity getCustomerById(@PathVariable long id) throws CustomerNotFoundException {

        if (userRepository.findById(id).isPresent()) {

            return new ResponseEntity(userRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new CustomerNotFoundException().getMessage(), HttpStatus.NOT_FOUND);

        }

    }


 @PutMapping("/user/{id}")
 @PreAuthorize("hasRole('USER')")
    public User updatedCustomer(@RequestBody User user,
                                    @PathVariable long id){
      return userRepository.save(user);
}


    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteCustomer(@PathVariable long id) throws CustomerNotFoundException{
//        Customer customer;
//        if(userRepository.findById(id).isPresent()){
//            userRepository.deleteById(id);
//        }else {
//            throw new CustomerNotFoundException();
//        }
//           return null;
//

        if (userRepository.findById(id).isPresent()) {

            return new ResponseEntity(userRepository.deleteById(id), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(new CustomerNotFoundException().getMessage(), HttpStatus.NOT_FOUND);

        }








    }






}
