package Repository;

import Model.DTO.LoginDTO;
import Service.DatabaseConnector;
import Service.PasswordHasher;

public class LoginRepositoryImpl implements LoginRepository {
    @Override
    public boolean insertLoginData(LoginDTO loginDTO) {
        try {
            String hashedPassword = PasswordHasher.hashPassword(loginDTO.getPassword());
            return DatabaseConnector.insertLoginData(loginDTO.getEmail(), hashedPassword, loginDTO.getUserType());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateLogin(LoginDTO loginDTO) {
        return DatabaseConnector.validateLogin(loginDTO.getEmail(), loginDTO.getPassword(), loginDTO.getUserType());
    }
}