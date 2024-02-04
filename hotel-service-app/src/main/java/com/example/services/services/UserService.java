package com.example.services.services;

import com.example.services.constants.ServiceConstants;
import com.example.services.entities.User;
import com.example.services.exceptions.UserNotFoundException;
import com.example.services.models.UserDto;
import com.example.services.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, "102"));
        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, "102"));
        updateUserFields(user, userDto);
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ServiceConstants.USER_NOT_FOUND, "102"));
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
        user.setName(userDto.getName());
        user.setEmailId(userDto.getEmailId());
        user.setPhoneNo(userDto.getPhoneNo());
    }
}
