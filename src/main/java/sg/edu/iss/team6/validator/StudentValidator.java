package sg.edu.iss.team6.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
@Component
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        if (!isValidUsername(student.getUser().getUsername())) {
            errors.rejectValue("username", "error.username.invalidFormat",
                    "Invalid username format");
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
