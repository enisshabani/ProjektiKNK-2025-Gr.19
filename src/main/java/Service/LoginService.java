package Service;

import Model.DTO.LoginDTO;
import Repository.LoginRepository;
import Repository.LoginRepositoryImpl;

public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    public boolean authenticateUser(LoginDTO loginDTO) {
        if (loginDTO.getEmail().isEmpty() || loginDTO.getPassword().isEmpty()) {
            return false;
        }
        return loginRepository.validateLogin(loginDTO);
    }

    public boolean registerLogin(LoginDTO loginDTO) {
        if (loginDTO.getEmail().isEmpty() || loginDTO.getPassword().isEmpty()) {
            return false;
        }
        return loginRepository.insertLoginData(loginDTO);
    }
}