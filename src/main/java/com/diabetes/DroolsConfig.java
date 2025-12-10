package com.diabetes;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsConfig {

    public static KieSession getSession() {
        KieServices kie = KieServices.Factory.get();
        KieContainer container = kie.getKieClasspathContainer();
        return container.newKieSession("diabetesKS");
    }
}