package sg.edu.iss.team6.utility;

import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class EmailUtility {
    public String generateConfirmationLink(long enrollmentId) {
        // Construct the confirmation link URL
        String baseUrl = "http://localhost:2000/student/confirmEnrollment";
        String encodedEnrollmentId = URLEncoder.encode(String.valueOf(enrollmentId), StandardCharsets.UTF_8);
        return baseUrl + "?enrollmentId=" + encodedEnrollmentId;
    }

}
