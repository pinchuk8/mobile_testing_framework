package testing.BaseEntities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import testing.driver.DriverManager;

public class BaseTest {
    @BeforeClass
    public static void createSession() {
        DriverManager.getDriver();
    }

    //сбрасываем текущую сессию, возвращаем приложение в исходное состояние
    @AfterMethod
    public void resetApp() {
        DriverManager.getDriver().resetApp();
    }

    @AfterClass
    public void closeSession() {
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
