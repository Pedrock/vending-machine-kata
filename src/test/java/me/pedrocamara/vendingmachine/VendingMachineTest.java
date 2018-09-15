package me.pedrocamara.vendingmachine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    @Test
    void testAcceptCoin() {
        final VendingMachine vm = new VendingMachine();

        assertFalse(vm.acceptCoin(CoinIdentifier.InvalidCoin));
        Arrays.stream(CoinIdentifier.values())
                .filter(coin -> coin != CoinIdentifier.InvalidCoin)
                .forEach(coin -> assertTrue(vm.acceptCoin(coin)));
    }

    @Test
    void testGetCurrentMoney() {
        final VendingMachine vm = new VendingMachine();

        assertTrue(vm.acceptCoin(CoinIdentifier.Nickel));
        assertEquals(5, vm.getCurrentMoney(), "money should equal 5");
        assertTrue(vm.acceptCoin(CoinIdentifier.Quarter));
        assertEquals(30, vm.getCurrentMoney(), "money should equal 30");
        assertTrue(vm.acceptCoin(CoinIdentifier.Dime));
        assertEquals(40, vm.getCurrentMoney(), "money should equal 40");
    }

    @Test
    void testRepeatedCoins() {
        final VendingMachine vm = new VendingMachine();

        for (int i = 0; i < 10; i++) {
            assertTrue(vm.acceptCoin(CoinIdentifier.Nickel));
        }
        assertEquals(50, vm.getCurrentMoney(), "money should equal 50");
    }

    @Test
    void testInvalidCoinMoney() {
        final VendingMachine vm = new VendingMachine();

        assertFalse(vm.acceptCoin(CoinIdentifier.InvalidCoin));
        assertEquals(0, vm.getCurrentMoney(), "money should equal 0");
    }
}