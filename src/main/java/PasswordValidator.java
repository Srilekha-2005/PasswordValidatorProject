public class PasswordValidator {

    private static final int MIN_LENGTH = 8;

    public boolean isValid(String password) {
        if (password == null) {
            throw new InvalidPasswordException(
                    "Password cannot be null");
        }

        if (!hasMinimumLength(password)) {
            throw new InvalidPasswordException(
                    "Password must be at least 8 characters long");
        }

        if (!containsDigit(password)) {
            throw new InvalidPasswordException(
                    "Password must contain a digit");
        }

        if (!containsUppercase(password)) {
            throw new InvalidPasswordException(
                    "Password must contain an uppercase letter");
        }

        if (containsSpace(password)) {
            throw new InvalidPasswordException(
                    "Password must not contain spaces");
        }

        return true;
    }

    private boolean hasMinimumLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsSpace(String password) {
        return password.contains(" ");
    }
}
