package Service;

import Model.DTO.ForgotPasswordDTO;
import Repository.ForgotPasswordRepository;
import java.util.ResourceBundle;

public class ForgotPasswordService {
    private ForgotPasswordRepository repository;
    private ResourceBundle bundle;

    public ForgotPasswordService(ResourceBundle bundle) {
        this.repository = new ForgotPasswordRepository();
        this.bundle = bundle;
    }

    public String processPasswordReset(ForgotPasswordDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty() ||
                dto.getNewPassword() == null || dto.getNewPassword().isEmpty()) {
            return bundle.getString("fillAllFields");
        }

        if (!repository.emailExists(dto.getEmail())) {
            return bundle.getString("emailNotFound");
        }

        String hashedPassword = PasswordHasher.hashPassword(dto.getNewPassword());
        dto.setHashedPassword(hashedPassword);

        if (!repository.savePasswordResetRequest(dto)) {
            return bundle.getString("resetRequestFailed");
        }

        if (!repository.updateUserPassword(dto)) {
            return bundle.getString("passwordUpdateFailed");
        }

        return "success";
    }
}
