import com.diabetes.facts.Conclusion;
import com.diabetes.facts.Patient;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RulesEngineTest {

    @Test
    public void testPossibleHyperglycemia() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setAge(50); p.setBmi(28.0);
            p.setFatigue(true);
            p.setFrequentUrination(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "гипергликемию"));
        } finally {
            ks.dispose();
        }
    }

    @Test
    public void testVisionComplicationsRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBlurredVision(true);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "сетчатке") || RuleTestUtils.containsMessage(concl, "офтальмолог"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testNeuropathyRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setNumbness(true);
            p.setFatigue(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "нейропатии") || RuleTestUtils.containsMessage(concl, "невролог"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testDehydrationRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFrequentUrination(true);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "дегидратация") || RuleTestUtils.containsMessage(concl, "жидкости"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testCardiovascularRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(32.0);
            p.setFatigue(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "сердечно-сосудистой") || RuleTestUtils.containsMessage(concl, "ЭКГ"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testCombinedComplicationsRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBlurredVision(true);
            p.setNumbness(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "мультиорганные") || RuleTestUtils.containsMessage(concl, "комплексная диагностика"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testDifficultyManagingDiabetes() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFamilyHistory(true);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "трудности") || RuleTestUtils.containsMessage(concl, "индивидуальную терапию"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testUnexpectedTreatmentResponse() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setHighSugar(true);
            p.setBmi(22.0);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "LADA") || RuleTestUtils.containsMessage(concl, "нестандартная"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testMultiOrganRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setNumbness(true);
            p.setBlurredVision(true);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "комплексная оценка") || RuleTestUtils.containsMessage(concl, "срочная"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testMetabolicComplications() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(31.0);
            p.setFatigue(true);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "пересмотр диеты") || RuleTestUtils.containsMessage(concl, "метаболические осложнения"));
        } finally { ks.dispose(); }
    }

    // дополнительные сложные правила
    @Test
    public void testHyperglycemiaHiddenRisk1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setHighSugar(true);
            p.setThirst(true);
            p.setFrequentUrination(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "скрытая гипергликемия") || RuleTestUtils.containsMessage(concl, "профиль глюкозы"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHyperglycemiaHiddenRisk2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setHighSugar(true);
            p.setBlurredVision(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "колебания сахара") || RuleTestUtils.containsMessage(concl, "в течение дня"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHyperglycemiaCumulativeRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setHighSugar(true);
            p.setFrequentUrination(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "Тройная комбинация") || RuleTestUtils.containsMessage(concl, "выраженной гипергликемии"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testDoubleSymptomTrigger1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setNumbness(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "ухудшения нейропатии") || RuleTestUtils.containsMessage(concl, "периферических нервов"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testDoubleSymptomTrigger2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBlurredVision(true);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "острые нарушения обмена веществ") || RuleTestUtils.containsMessage(concl, "проверка гликемии"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testDoubleSymptomTrigger3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setHighSugar(true);
            p.setNumbness(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "язвы") || RuleTestUtils.containsMessage(concl, "трофических осложнений"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testBmiRisk1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(33.0);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "Высокий BMI") || RuleTestUtils.containsMessage(concl, "метаболический контроль"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testBmiRisk2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(19.0);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "аутоиммунный") || RuleTestUtils.containsMessage(concl, "худеющую"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testBmiRisk3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(29.0);
            p.setFatigue(true);
            p.setNumbness(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "сердечно-сосудистых") || RuleTestUtils.containsMessage(concl, "нейропатии"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testFamilyHistoryRisk1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFamilyHistory(true);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "анамнез") || RuleTestUtils.containsMessage(concl, "самоконтроль"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testFamilyHistoryRisk2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFamilyHistory(true);
            p.setHighSugar(true);
            p.setBmi(28.0);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "коррекция терапии") || RuleTestUtils.containsMessage(concl, "управлении сахаром"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testFamilyHistoryRisk3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFamilyHistory(true);
            p.setBlurredVision(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "офтальмолог") || RuleTestUtils.containsMessage(concl, "усиленный"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHiddenPattern1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setThirst(true);
            p.setFatigue(true);
            p.setFrequentUrination(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "дневник питания") || RuleTestUtils.containsMessage(concl, "нерегулярный прием пищи"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHiddenPattern2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setNumbness(true);
            p.setHighSugar(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "искать другие причины") || RuleTestUtils.containsMessage(concl, "скрытые периферические"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHiddenPattern3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBlurredVision(true);
            p.setHighSugar(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "сосудистые") || RuleTestUtils.containsMessage(concl, "микрососудистые"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testSystemicRisk1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setThirst(true);
            p.setBlurredVision(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "системное нарушение") || RuleTestUtils.containsMessage(concl, "комплексная оценка"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testSystemicRisk2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setHighSugar(true);
            p.setNumbness(true);
            p.setBlurredVision(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "высокий риск осложнений") || RuleTestUtils.containsMessage(concl, "немедленная оценка"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testSystemicRisk3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFrequentUrination(true);
            p.setBlurredVision(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "декомпенсацию") || RuleTestUtils.containsMessage(concl, "контроль"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testComplexCombination1() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(21.0);
            p.setThirst(true);
            p.setNumbness(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "эндокринные") || RuleTestUtils.containsMessage(concl, "аутоиммунные"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testComplexCombination2() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setBmi(36.0);
            p.setFrequentUrination(false);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "скрытая гипергликемия") || RuleTestUtils.containsMessage(concl, "гидратация"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testComplexCombination3() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setFatigue(true);
            p.setBlurredVision(true);
            p.setFamilyHistory(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "междисциплинарная оценка") || RuleTestUtils.containsMessage(concl, "усиленный мониторинг"));
        } finally { ks.dispose(); }
    }

    // Гестационные правила
    @Test
    public void testGestationalInsulinResistanceWarning() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setDiabetesType("Гестационный");
            p.setBmi(29.0);
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "инсулинорезистентности") || RuleTestUtils.containsMessage(concl, "акушером"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testGestationalFetalRiskSignal() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setDiabetesType("Гестационный");
            p.setHighSugar(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "макросомии") || RuleTestUtils.containsMessage(concl, "родах"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testGestationalHydrationConcern() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setDiabetesType("Гестационный");
            p.setFrequentUrination(true);
            p.setThirst(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "дегидратирована") || RuleTestUtils.containsMessage(concl, "электролитов"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testGestationalHypoglycemiaRisk() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setDiabetesType("Гестационный");
            p.setFatigue(true);
            p.setHighSugar(false);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "гипогликемия") || RuleTestUtils.containsMessage(concl, "мониторинг"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testGestationalPostpartumMonitoring() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            Patient p = new Patient();
            p.setDiabetesType("Гестационный");
            p.setHighSugar(false);
            p.setFamilyHistory(true);

            ks.insert(p);
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "постнатальное наблюдение") || RuleTestUtils.containsMessage(concl, "развития диабета 2 типа"));
        } finally { ks.dispose(); }
    }

    // Агрегаторные правила: вставляем строки напрямую
    @Test
    public void testHyperglycemiaEscalation() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            ks.insert("HYPER");
            ks.insert("HYPER"); // два тега — должно сработать
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "несколько признаков гипергликемии") || RuleTestUtils.containsMessage(concl, "повышенная вероятность"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testHypoglycemiaEscalation() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            ks.insert("HYPO");
            ks.insert("HYPO");
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "повторяющиеся эпизоды гипогликемии") || RuleTestUtils.containsMessage(concl, "пересмотреть режим"));
        } finally { ks.dispose(); }
    }

    @Test
    public void testMultiComplicationEscalation() {
        KieSession ks = RuleTestUtils.newSession();
        try {
            ks.insert("COMPL");
            ks.insert("COMPL");
            ks.insert("COMPL");
            ks.fireAllRules();

            List<Conclusion> concl = RuleTestUtils.collectConclusions(ks);
            assertTrue(RuleTestUtils.containsMessage(concl, "в высокой группе риска") || RuleTestUtils.containsMessage(concl, "срочная комплексная оценка"));
        } finally { ks.dispose(); }
    }
}