package Service;

import DTO.SignUpDTO;
import Repository.SignUpRepository;
import Repository.SignUpRepositoryImpl;

public class SignUpService {
    private final SignUpRepository signUpRepository;

    public SignUpService() {
        this.signUpRepository = new SignUpRepositoryImpl();
    }

    public boolean registerUser(SignUpDTO signUpDTO) {
        if (signUpDTO.getName().isEmpty() || signUpDTO.getLastName().isEmpty() ||
                signUpDTO.getEmail().isEmpty() || signUpDTO.getPassword().isEmpty() ||
                signUpDTO.getUniversity().isEmpty() || signUpDTO.getUniversityYear().isEmpty()) {
            return false;
        }

        // Validate average grade
        if (signUpDTO.getAverageGrade() < 0 || signUpDTO.getAverageGrade() > 10) {
            return false;
        }

        // Check if email already exists
        if (signUpRepository.emailExists(signUpDTO.getEmail())) {
            return false;
        }

        // Insert data into database
        return signUpRepository.insertSignupData(signUpDTO);
    }
}