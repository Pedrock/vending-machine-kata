package me.pedrocamara.vendingmachine;

import com.google.inject.Inject;
import me.pedrocamara.observer.Observer;
import org.slf4j.Logger;

public class VendingMachineDisplay implements Observer<VendingMachine>, AutoCloseable {

    private final VendingMachine vendingMachine;
    private final Logger logger;

    @Inject
    public VendingMachineDisplay(final VendingMachine vendingMachine, final Logger logger) {
        this.vendingMachine = vendingMachine;
        this.logger = logger;
        vendingMachine.addObserver(this);
    }

    public String getCurrentDisplay() {
        final int money = this.vendingMachine.getCurrentMoney();
        if (money == 0) {
            return "INSERT COIN";
        } else {
            return Integer.toString(money);
        }
    }

    @Override
    public void close() {
        this.vendingMachine.deleteObserver(this);
    }

    @Override
    public void update(final VendingMachine vendingMachine) {
        logger.info(getCurrentDisplay());
    }
}
