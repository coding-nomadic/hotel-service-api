package com.hotel.service.app.services;

import com.hotel.service.app.constants.ServiceConstants;
import com.hotel.service.app.entities.User;
import com.hotel.service.app.exceptions.UserAlreadyExistsException;
import com.hotel.service.app.exceptions.UserNotFoundException;
import com.hotel.service.app.models.UserDto;
import com.hotel.service.app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, ServiceConstants.ERROR_CODE));
        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        if(userRepository.existsByUserName(userDto.getUserName())){
            throw new UserAlreadyExistsException(ServiceConstants.USER_ALREADY_FOUND+" with "+userDto.getUserName(), ServiceConstants.ERROR_CODE);
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExistsException(ServiceConstants.USER_ALREADY_FOUND+" with "+userDto.getEmail(), ServiceConstants.ERROR_CODE);
        }
        if(userRepository.existsByContact(userDto.getContact())){
            throw new UserAlreadyExistsException(ServiceConstants.USER_NOT_FOUND+" with "+userDto.getContact(), ServiceConstants.ERROR_CODE);
        }
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, ServiceConstants.ERROR_CODE));
        updateUserFields(user, userDto);
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, ServiceConstants.ERROR_CODE));
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private void updateUserFields(User user, UserDto userDto) {
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
    }
}
