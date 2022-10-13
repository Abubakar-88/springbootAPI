package com.ecommerce.springBootEcommerce.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

//  @Autowired
//  UserRepository userRepository;
//




//  @GetMapping("/all")
//  public ResponseEntity<List> getAllCustomer() {
//    return  new ResponseEntity<List>((List)userRepository.findAll(), HttpStatus.OK);
//
//  }

//  @GetMapping("/customer")
//  public ResponseEntity<List> getAllCustomer(){
//    return  new ResponseEntity<List>((List)userRepository.findAll(), HttpStatus.OK);
//  }





  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "Customer Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
