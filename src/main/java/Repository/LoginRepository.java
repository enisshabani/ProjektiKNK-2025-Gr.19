package Repository;

import Model.DTO.LoginDTO;
import Service.PasswordHasher;

public interface LoginRepository {
    boolean insertLoginData(LoginDTO loginDTO);
    boolean validateLogin(LoginDTO loginDTO);
}