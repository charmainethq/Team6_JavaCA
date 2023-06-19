package sg.edu.iss.team6.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.iss.team6.model.User;

@Component
public class UserValidator implements Validator {

    private static final int MIN_PASSWORD_LENGTH = 10;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

        if (!isValidUsername(user.getUsername())) {
            errors.rejectValue("username", "username.invalidFormat", "Invalid username format");
        }

        if (user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            errors.rejectValue("password", "password.minLength", "Password should be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
    }

    private boolean isValidUsername(String username) {
        String[] parts = username.split("_");
        if (parts.length != 3) {
            return false;
        }
        String role = parts[0];
        String userId = parts[1];
        String lastName = parts[2];
        return (role.equals("adm") || role.equals("lec") || role.equals("stu"))
                && userId.matches("\\d+")
                && lastName.matches("[a-zA-Z]+");
    }
}


