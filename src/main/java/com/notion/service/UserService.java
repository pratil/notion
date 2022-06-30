package com.notion.service;

import com.notion.dto.user.request.UpsertUserDto;
import com.notion.exceptions.user.UserNotFoundException;
import com.notion.model.User;
import com.notion.repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.notion.constants.Constants.UserException.USER_EMAIL_NOT_FOUND;
import static com.notion.constants.Constants.UserException.USER_ID_NOT_FOUND;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserDtoById(String id) {
        return getUserById(id);
    }

    public User saveUser(UpsertUserDto upsertUserDto) {
        return userRepository.save(upsertUserDto.getUser());
    }

    public User updateUser(String id, UpsertUserDto upsertUserDto) {
        User user = getUserById(id);
        User updatedUser = upsertUserDto.getUser();
        if (updatedUser.getFirstName() != null)
            user.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null)
            user.setLastName(updatedUser.getLastName());
        if (updatedUser.getMiddleName() != null)
            user.setMiddleName(updatedUser.getMiddleName());
        if (updatedUser.getPreferredName() != null)
            user.setPreferredName(updatedUser.getPreferredName());
        if (updatedUser.getEmail() != null)
            user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null)
            user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    public User deleteUser(String id) {
        User user = getUserById(id);
        user.setIsActive(false);
        return userRepository.save(user);
    }

    private User getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException(String.format(USER_ID_NOT_FOUND, id));
        return optionalUser.get();
    }

    private User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findById(email);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException(String.format(USER_EMAIL_NOT_FOUND, email));
        return optionalUser.get();
    }
}
