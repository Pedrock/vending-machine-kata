package me.pedrocamara.vendingmachine;

import me.pedrocamara.observer.Observable;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class VendingMachine extends Observable<VendingMachine> {

    private Map<CoinIdentifier, Integer> coinAmounts;
    private int currentMoney;

    VendingMachine() {
        this.coinAmounts = new EnumMap<>(CoinIdentifier.class);
        this.currentMoney = 0;
    }

    public int getCurrentMoney() {
        return this.currentMoney;
    }

    public boolean acceptCoin(final CoinIdentifier coinIdentifier) {
        final Optional<Integer> coinValueOpt = getCoinValue(coinIdentifier);
        coinValueOpt.ifPresent(coinValue -> {
            this.coinAmounts.merge(coinIdentifier, 1, Integer::sum);
            this.currentMoney += coinValue;
            this.notifyObservers();
        });
        return coinValueOpt.isPresent();
    }

    private Optional<Integer> getCoinValue(final CoinIdentifier coinIdentifier) {
        switch (coinIdentifier) {
            case Nickel:
                return Optional.of(5);
            case Dime:
                return Optional.of(10);
            case Quarter:
                return Optional.of(25);
            default:
                return Optional.empty();
        }
    }
}
