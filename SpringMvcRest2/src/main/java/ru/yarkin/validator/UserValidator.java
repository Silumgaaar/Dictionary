package ru.yarkin.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.yarkin.models.database.User;
import ru.yarkin.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "This field is required");
        if(user.getName().length() < 8 || user.getName().length() > 32){
            errors.rejectValue("name", "Username must be between 8 and 32 characters");
        }

        if(userService.getOne(user.getName())!=null){
            errors.rejectValue("name", "Such username already exists");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "This field is required");

        if (user.getPassword().length()<8 || user.getPassword().length()>32){
            errors.rejectValue("password", "Password must be over 8 characters");
        }

        if(!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","Password don't match");
        }
    }
}
