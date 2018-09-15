package me.pedrocamara.vendingmachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class VendingMachineDisplayTest {

    private VendingMachine vendingMachine;
    private Logger logger;
    private VendingMachineDisplay display;

    @BeforeEach
    void setUp() {
        this.vendingMachine = new VendingMachine();
        this.logger = mock(Logger.class);
        this.display = new VendingMachineDisplay(this.vendingMachine, this.logger);
    }

    @Test
    void testInitialDisplay() {
        assertEquals("INSERT COIN", this.display.getCurrentDisplay());
    }

    @Test
    void testCoinInsertion() {
        final boolean coinAccepted = this.vendingMachine.acceptCoin(CoinIdentifier.Quarter);
        final String quarterStringValue = "25";

        assertTrue(coinAccepted, "coin should be accepted");
        assertEquals(quarterStringValue, this.display.getCurrentDisplay());
    }

    @Test
    void testUpdate() {
        final String dimeStringValue = "10";
        this.vendingMachine.acceptCoin(CoinIdentifier.Dime);
        verify(this.logger, times(1)).info(eq(dimeStringValue));
        verify(this.logger, times(1)).info(any());
    }

    @Test
    void testAutoClose() throws Exception {
        final AutoCloseable autoCloseable = this.display;
        autoCloseable.close();
        this.vendingMachine.acceptCoin(CoinIdentifier.Dime);
        verify(this.logger, never()).info(any());
    }
}