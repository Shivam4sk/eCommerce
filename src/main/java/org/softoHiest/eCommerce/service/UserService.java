package org.softoHiest.eCommerce.service;

import org.softoHiest.eCommerce.repository.UserRepository;
import org.softoHiest.eCommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private Long nextId = 1L;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<User> fetchUserById(Long id) {

//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id,User updateUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    existingUser.setEmail(updateUser.getEmail());
                    existingUser.setPhone(updateUser.getPhone());
                    existingUser.setRole(updateUser.getRole());
                    existingUser.setAddress(updateUser.getAddress());
                    existingUser.setUpdatedAt(updateUser.getUpdatedAt());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }
}
