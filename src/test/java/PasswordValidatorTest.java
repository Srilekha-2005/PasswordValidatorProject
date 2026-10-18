import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyTestListener.class)
public class PasswordValidatorTest {

    private final PasswordValidator validator =
            new PasswordValidator();

    @DataProvider(name = "passwordCases")
    public Object[][] passwordCases() {
        return new Object[][] {
                // Valid equivalence partitions and boundaries
                {"Abcdefg1", true, "Minimum length of 8"},
                {"Abcdefgh1", true, "Length above minimum"},
                {"Password123", true, "Normal valid password"},
                {"A1234567", true, "Uppercase and digits"},
                {"ABCDEFG1", true, "Uppercase without lowercase"},
                {"abcdefg1", false, "Missing uppercase"},
                {"Abcdefgh", false, "Missing digit"},
                {"Abc def1", false, "Contains a space"},
                {"Abcdef1", false, "Length below minimum"}
        };
    }

    @Test(
            dataProvider = "passwordCases",
            groups = {"regression"}
    )
    public void validatesPasswordEquivalencePartitions(
            String password,
            boolean expectedValid,
            String description) {

        boolean actualValid;

        try {
            actualValid = validator.isValid(password);
        } catch (InvalidPasswordException exception) {
            actualValid = false;
        }

        Assert.assertEquals(
                actualValid,
                expectedValid,
                "Failed case: " + description
        );
    }

    @Test(groups = {"smoke"})
    public void acceptsValidPassword() {
        Assert.assertTrue(validator.isValid("Abcdefg1"));
    }

    @Test(
            groups = {"regression"},
            expectedExceptions = InvalidPasswordException.class
    )
    public void rejectsPasswordShorterThanEight() {
        validator.isValid("Abcdef1");
    }

    @Test(
            groups = {"regression"},
            expectedExceptions = InvalidPasswordException.class
    )
    public void rejectsPasswordWithoutDigit() {
        validator.isValid("Abcdefgh");
    }

    @Test(
            groups = {"regression"},
            expectedExceptions = InvalidPasswordException.class
    )
    public void rejectsPasswordWithoutUppercase() {
        validator.isValid("abcdefg1");
    }

    @Test(
            groups = {"regression"},
            expectedExceptions = InvalidPasswordException.class
    )
    public void rejectsPasswordContainingSpace() {
        validator.isValid("Abc def1");
    }

    @Test(
            groups = {"regression"},
            expectedExceptions = InvalidPasswordException.class
    )
    public void rejectsNullPassword() {
        validator.isValid(null);
    }
}
