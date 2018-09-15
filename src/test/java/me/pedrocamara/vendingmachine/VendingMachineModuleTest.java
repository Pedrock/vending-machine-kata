package me.pedrocamara.vendingmachine;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineModuleTest {
    @Test
    void testVendingMachineCreation() {
        final Injector injector = Guice.createInjector(new VendingMachineModule());
        final VendingMachine vendingMachine = injector.getInstance(VendingMachine.class);
        assertNotNull(vendingMachine);
    }
}