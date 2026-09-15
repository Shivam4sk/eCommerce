package org.softoHiest.eCommerce;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();

    private Long nextId = 1L;

    public List<User> fetchAllUsers(){
        return userList;
    }

    public void addUser(User user){
        user.setId(nextId++);
        userList.add(user);
    }

    public Optional<User> fetchUserById(Long id) {

//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public boolean updateUser(Long id,User updateUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setName(updateUser.getName());
                    existingUser.setEmail(updateUser.getEmail());
                    return true;
                }).orElse(false);
    }

    public boolean deleteUser(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> {
                    userList.remove(user);
                    return true;
                }).orElse(false);
    }
}
