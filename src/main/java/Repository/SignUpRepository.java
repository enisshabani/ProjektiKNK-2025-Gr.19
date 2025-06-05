package Repository;

import DTO.SignUpDTO;

public interface SignUpRepository {
    boolean insertSignupData(SignUpDTO signUpDTO);
    boolean emailExists(String email);
}