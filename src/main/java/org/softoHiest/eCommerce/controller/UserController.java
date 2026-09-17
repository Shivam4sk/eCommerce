package org.softoHiest.eCommerce.controller;

import org.softoHiest.eCommerce.dto.requestDto.UserRequestDto;
import org.softoHiest.eCommerce.dto.responseDto.UserResponseDto;
import org.softoHiest.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllusers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());// Logic to retrieve all users from the database
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        //return ResponseEntity.ok(userService.fetchUserById(id));

        return userService.fetchUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequestDto) {
        userService.addUser(userRequestDto);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequestDto updateUserReqDto){
        boolean update = userService.updateUser(id,updateUserReqDto);
        if(update)
            return ResponseEntity.ok("Update Successfully.");
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        boolean delete = userService.deleteUser(id);
        if(delete)
           return ResponseEntity.ok("User Deleted.");
        return ResponseEntity.notFound().build();
    }

}
