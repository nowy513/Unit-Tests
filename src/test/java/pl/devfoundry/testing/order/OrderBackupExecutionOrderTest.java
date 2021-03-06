package pl.devfoundry.testing.order;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderBackup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingFileFirstShouldThrowException() throws IOException {

//        Given
        OrderBackup orderBackup = new OrderBackup();

//        Then
        assertThrows(IOException.class, () -> orderBackup.backupOrder(new Order()));
    }
}
