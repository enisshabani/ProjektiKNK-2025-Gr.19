package Repository;

import DTO.SignUpDTO;
import Service.DatabaseConnector;
import Service.PasswordHasher;

public class SignUpRepositoryImpl implements SignUpRepository {
    @Override
    public boolean insertSignupData(SignUpDTO signUpDTO) {
        try {
            String hashedPassword = PasswordHasher.hashPassword(signUpDTO.getPassword());
            return DatabaseConnector.insertSignupData(
                    signUpDTO.getName(),
                    signUpDTO.getLastName(),
                    signUpDTO.getEmail(),
                    hashedPassword,
                    signUpDTO.getUniversity(),
                    signUpDTO.getUniversityYear(),
                    signUpDTO.getAverageGrade(),
                    signUpDTO.getGender()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean emailExists(String email) {
        return DatabaseConnector.checkEmailExists(email);
    }
}