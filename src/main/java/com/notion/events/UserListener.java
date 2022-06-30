package com.notion.events;

import com.notion.exceptions.user.InvalidUserException;
import com.notion.model.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.notion.constants.Constants.UserException.EMPTY_NAME_EXCEPTION;

@Component
public class UserListener extends AbstractMongoEventListener<User> {

//    private final UserRepository userRepository;
//
//    public UserListener(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    private boolean isInvalid(String string) {
        return string == null || string.trim().isEmpty();
    }

    private String defaultPreferredName(User user) {
        if (user.getPreferredName() != null)
            return user.getPreferredName();
        if (user.getFirstName() != null)
            return user.getFirstName();
        if (user.getLastName() != null)
            return user.getLastName();
        if (user.getMiddleName() != null)
            return user.getMiddleName();
        throw new InvalidUserException(EMPTY_NAME_EXCEPTION);
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        if (isInvalid(event.getSource().getFirstName()) && isInvalid(event.getSource().getLastName()))
            throw new InvalidUserException(EMPTY_NAME_EXCEPTION);
        event.getSource().setPreferredName(defaultPreferredName(event.getSource()));
        event.getSource().setIsActive(event.getSource().getIsActive() != null ? event.getSource().getIsActive() : true);
//        event.getSource().setPassword(new BCryptPasswordEncoder().encode(event.getSource().getPassword()));
        event.getSource().setPassword(event.getSource().getPassword());
        Long timestamp = new Date().getTime();
        if (event.getSource().getCreatedAt() == null)
            event.getSource().setCreatedAt(timestamp);
        event.getSource().setUpdatedAt(timestamp);
        super.onBeforeConvert(event);
    }

}
