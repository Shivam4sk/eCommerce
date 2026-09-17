package org.softoHiest.eCommerce.service;

import org.softoHiest.eCommerce.dto.requestDto.AddressRequestDto;
import org.softoHiest.eCommerce.dto.requestDto.UpdateUserReqDto;
import org.softoHiest.eCommerce.dto.requestDto.UserRequestDto;
import org.softoHiest.eCommerce.dto.responseDto.UserResponseDto;
import org.softoHiest.eCommerce.model.Address;
import org.softoHiest.eCommerce.model.User;
import org.softoHiest.eCommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private Long nextId = 1L;

    public List<UserResponseDto> fetchAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponseDto)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequestDto userRequestDto){
        User user = new User();
        updateUserFromRequest(user,userRequestDto);
        userRepository.save(user);
    }

    private void updateUserFromRequest(User user, UserRequestDto userRequestDto) {
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        if(userRequestDto.getAddress() != null){
            Address address = new Address();
            address.setStreet(userRequestDto.getAddress().getStreet());
            address.setCity(userRequestDto.getAddress().getCity());
            address.setState(userRequestDto.getAddress().getState());
            address.setCountry(userRequestDto.getAddress().getCountry());
            address.setZipcode(userRequestDto.getAddress().getZipcode());
            user.setAddress(address);
        }

    }

    public Optional<UserResponseDto> fetchUserById(Long id) {

//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
        return userRepository.findById(id)
                .map(this::mapToUserResponseDto);
    }

    public boolean updateUser(Long id, UserRequestDto updateUserReqDto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                   updateUserFromRequest(existingUser, updateUserReqDto);
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



    private UserResponseDto mapToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setRole(user.getRole());

        if(user.getAddress() != null){
            AddressRequestDto  addressRequestDto = new AddressRequestDto();
            addressRequestDto.setStreet(user.getAddress().getStreet());
            addressRequestDto.setCity(user.getAddress().getCity());
            addressRequestDto.setState(user.getAddress().getState());
            addressRequestDto.setCountry(user.getAddress().getCountry());
            addressRequestDto.setZipcode(user.getAddress().getZipcode());
            userResponseDto.setAddress(addressRequestDto);
        }

        return userResponseDto;
    }
}
