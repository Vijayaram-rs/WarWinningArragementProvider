package main.test.com.casademo;

import main.java.com.casademo.WarWinningArrangementProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WarWinningArrangementProviderTest {

    @Test
    public void mainWithException() {
        String[] input = {""};
        Assertions.assertThrows(RuntimeException.class, () -> WarWinningArrangementProvider.main(input));
    }

    @Test
    public void mainWithoutException() {
        String[] input = {"Spearmen#1","Spearmen#1"};
        Assertions.assertDoesNotThrow(()->WarWinningArrangementProvider.main(input));

    }
}
