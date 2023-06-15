package sg.edu.iss.team6.controller.exception;

public class EnrollmentNotFound extends Exception {
    private static final long serialVersionUID = 1L;

    public EnrollmentNotFound() {}

    public EnrollmentNotFound(String msg) {
        super(msg);
    }
}
