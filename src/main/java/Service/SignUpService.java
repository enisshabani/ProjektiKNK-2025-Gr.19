package Service;

import Model.DTO.SignUpDTO;
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

        if (signUpDTO.getAverageGrade() < 0 || signUpDTO.getAverageGrade() > 10) {
            return false;
        }

        if (signUpRepository.emailExists(signUpDTO.getEmail())) {
            return false;
        }

        return signUpRepository.insertSignupData(signUpDTO);
    }
}