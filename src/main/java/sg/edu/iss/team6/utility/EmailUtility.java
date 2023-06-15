package sg.edu.iss.team6.utility;

import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class EmailUtility {
    public String generateConfirmationLink(long studentId, long classId) {
        // Construct the confirmation link URL
        String baseUrl = "http://localhost:2000/student/confirmEnrollment";
        String encodedStudentId = URLEncoder.encode(String.valueOf(studentId), StandardCharsets.UTF_8);
        String encodedEnrollmentId = URLEncoder.encode(String.valueOf(classId), StandardCharsets.UTF_8);
        return baseUrl + "?studentId=" + encodedStudentId + "&classId=" + encodedEnrollmentId;
    }

}
