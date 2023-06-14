package sg.edu.iss.team6.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class EmailUtility {
    public String generateConfirmationLink(long studentId, long enrollmentId) {
        // Construct the confirmation link URL
        String baseUrl = "http://localhost:2000/student/confirmEnrollment";
        String encodedStudentId = URLEncoder.encode(String.valueOf(studentId), StandardCharsets.UTF_8);
        String encodedEnrollmentId = URLEncoder.encode(String.valueOf(enrollmentId), StandardCharsets.UTF_8);
        return baseUrl + "?studentId=" + encodedStudentId + "&enrollmentId=" + encodedEnrollmentId;
    }

}
