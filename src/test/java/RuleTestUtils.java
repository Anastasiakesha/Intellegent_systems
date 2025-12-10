import com.diabetes.facts.Conclusion;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.stream.Collectors;

public final class RuleTestUtils {

    private RuleTestUtils() {}

    public static KieSession newSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        // Используем default-конструктор сессии; если в проекте задано имя сессии - поменяй на kc.newKieSession("ksession-rules")
        return kc.newKieSession("diabetesKS");
    }

    public static List<Conclusion> collectConclusions(KieSession session) {
        return session.getObjects(o -> o instanceof Conclusion)
                .stream()
                .map(o -> (Conclusion) o)
                .collect(Collectors.toList());
    }

    public static boolean containsMessage(List<Conclusion> conclusions, String fragment) {
        return conclusions.stream().anyMatch(c -> c.getMessage() != null && c.getMessage().contains(fragment));
    }

}