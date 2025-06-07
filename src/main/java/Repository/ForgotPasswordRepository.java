package Repository;

import Model.DTO.ForgotPasswordDTO;
import Service.DatabaseConnector;

public class ForgotPasswordRepository {
    private DatabaseConnector databaseConnector;

    public ForgotPasswordRepository() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    public boolean savePasswordResetRequest(ForgotPasswordDTO dto) {
        return databaseConnector.insertForgotPasswordData(
                dto.getEmail(),
                dto.getConfirmationCode(),
                dto.getHashedPassword()
        );
    }

    public boolean updateUserPassword(ForgotPasswordDTO dto) {
        return databaseConnector.updatePassword(
                dto.getEmail(),
                dto.getHashedPassword()
        );
    }

    public boolean emailExists(String email) {
        // This would check if the email exists in the database
        return databaseConnector.checkEmailExists(email);
    }
}