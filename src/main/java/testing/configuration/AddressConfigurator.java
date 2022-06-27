package testing.configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

//Класс запускает и создаёт appium server с параметрами, которые мы зададим
public class AddressConfigurator {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final String ERROR_LOG_LEVEL = "error";
    public static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";
    private static AppiumDriverLocalService appiumDriverLocalService;//класс позволяющий манипулировать appium server

    private AddressConfigurator() {
    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null)
            startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.get().appiumAddress())
                //.usingAnyFreePort() - займёт любой свбодный порт
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE) //закрываем предыдущую сессию на таком же порте и адресе, что и у нас, если она не была завершена ранее
                .withArgument(LOG_LEVEL, ERROR_LOG_LEVEL)//выведет ошибки в логах
                .build();
        appiumDriverLocalService.start();
        LOG.info("Appium server was started on the port {}", port);
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            LOG.info("Appium server stopped");
        });
    }

    //проверяем свободен ли порт
    private static boolean isPortFree(int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) { //проверяет свбоден ли порт
            LOG.info("Specified port - {} is available and ready to use", port);
        } catch (Exception e) {
            isFree = false;
            LOG.warn("Specified port - {} is occupied by some process and process will be terminated", port);
        }
        return isFree;
    }

    //освободит наш порт, если он занят
    private static void makePortAvailableIfOccupied(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(KILL_NODE_COMMAND);
            } catch (IOException e) {
                LOG.error("Couldn't execute runtime command, message: {}", e.getMessage());
            }
        }
    }
}
