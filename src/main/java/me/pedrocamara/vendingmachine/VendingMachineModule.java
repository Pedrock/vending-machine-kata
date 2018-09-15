package me.pedrocamara.vendingmachine;

import com.google.inject.AbstractModule;
import com.tavianator.sangria.slf4j.SangriaSlf4jModule;

public class VendingMachineModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new SangriaSlf4jModule());
    }
}
