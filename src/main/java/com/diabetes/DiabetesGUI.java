package com.diabetes;

import com.diabetes.facts.Conclusion;
import com.diabetes.facts.Patient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class DiabetesGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Экспертная система по диабету");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 20));

        int row = 0;

        // -------------------- ОСНОВНЫЕ ДАННЫЕ --------------------
        grid.add(new Label("Возраст:"), 0, row);
        TextField ageField = new TextField();
        allowOnlyInteger(ageField);
        grid.add(ageField, 1, row++);

        grid.add(new Label("BMI:"), 0, row);
        TextField bmiField = new TextField();
        allowOnlyDouble(bmiField);
        grid.add(bmiField, 1, row++);

        grid.add(new Separator(), 0, row++, 2, 1);

        // -------------------- ТИП ДИАБЕТА --------------------
        grid.add(new Label("Тип диабета:"), 0, row);
        ComboBox<String> diabetesTypeCombo = new ComboBox<>();
        diabetesTypeCombo.getItems().addAll("Тип 1", "Тип 2", "Гестационный");
        diabetesTypeCombo.setPromptText("Выберите тип");
        grid.add(diabetesTypeCombo, 1, row++);

        grid.add(new Separator(), 0, row++, 2, 1);

        // -------------------- ОСНОВНЫЕ СИМПТОМЫ --------------------
        grid.add(new Label("Основные симптомы:"), 0, row++);

        CheckBox thirstCheck = new CheckBox("Жажда");
        CheckBox urinationCheck = new CheckBox("Частое мочеиспускание");
        CheckBox fatigueCheck = new CheckBox("Усталость");
        CheckBox blurredVisionCheck = new CheckBox("Затуманенное зрение");
        CheckBox highSugarCheck = new CheckBox("Высокий уровень сахара");
        CheckBox lowSugarCheck = new CheckBox("Низкий уровень сахара");
        CheckBox numbnessCheck = new CheckBox("Онемение");
        CheckBox nightSweatsCheck = new CheckBox("Ночная потливость");
        CheckBox rapidHeartbeatCheck = new CheckBox("Учащённое сердцебиение");
        CheckBox injectionSiteProblemsCheck = new CheckBox("Проблемы места инъекции");

        grid.add(thirstCheck, 0, row);
        grid.add(urinationCheck, 1, row++);
        grid.add(fatigueCheck, 0, row);
        grid.add(blurredVisionCheck, 1, row++);
        grid.add(highSugarCheck, 0, row);
        grid.add(lowSugarCheck, 1, row++);
        grid.add(numbnessCheck, 0, row);
        grid.add(nightSweatsCheck, 1, row++);
        grid.add(rapidHeartbeatCheck, 0, row);
        grid.add(injectionSiteProblemsCheck, 1, row++);
        row++;
        grid.add(new Separator(), 0, row++, 2, 1);

        // -------------------- ГИПОГЛИКЕМИЯ --------------------
        grid.add(new Label("Признаки гипогликемии:"), 0, row++);

        CheckBox dizzinessCheck = new CheckBox("Головокружение");
        CheckBox sweatingCheck = new CheckBox("Потливость");
        CheckBox hungerCheck = new CheckBox("Сильный голод");
        CheckBox confusionCheck = new CheckBox("Спутанность сознания");

        grid.add(dizzinessCheck, 0, row);
        grid.add(sweatingCheck, 1, row++);
        grid.add(hungerCheck, 0, row);
        grid.add(confusionCheck, 1, row++);

        grid.add(new Separator(), 0, row++, 2, 1);

        // -------------------- ПИТАНИЕ --------------------
        grid.add(new Label("Проблемы питания:"), 0, row++);

        CheckBox skippedMealsCheck = new CheckBox("Пропуски еды");
        CheckBox poorDietCheck = new CheckBox("Неправильное питание");
        CheckBox lateEatingCheck = new CheckBox("Поздние приёмы пищи");
        CheckBox irregularEatingCheck = new CheckBox("Нерегулярное питание");

        grid.add(skippedMealsCheck, 0, row);
        grid.add(poorDietCheck, 1, row++);
        grid.add(lateEatingCheck, 0, row);
        grid.add(irregularEatingCheck, 1, row++);

        grid.add(new Separator(), 0, row++, 2, 1);


        // -------------------- ОСЛОЖНЕНИЯ --------------------
        grid.add(new Label("Осложнения:"), 0, row++);

        CheckBox neuropathyPainCheck = new CheckBox("Нейропатическая боль");
        CheckBox footUlcersCheck = new CheckBox("Язвы стопы");
        CheckBox kidneyIssuesCheck = new CheckBox("Проблемы почек");
        CheckBox highBloodPressureCheck = new CheckBox("Высокое давление");
        CheckBox infectionsCheck = new CheckBox("Инфекции");
        CheckBox slowHealingCheck = new CheckBox("Медленное заживление");
        CheckBox weightLossCheck = new CheckBox("Похудение");

        grid.add(neuropathyPainCheck, 0, row);
        grid.add(footUlcersCheck, 1, row++);
        grid.add(kidneyIssuesCheck, 0, row);
        grid.add(highBloodPressureCheck, 1, row++);
        grid.add(infectionsCheck, 0, row);
        grid.add(slowHealingCheck, 1, row++);
        grid.add(weightLossCheck, 0, row++);

        grid.add(new Separator(), 0, row++, 2, 1);

        // -------------------- ГЕСТАЦИОННЫЙ ДИАБЕТ (динамические поля) --------------------
        Label gestationalLabel = new Label("Гестационный диабет:");
        TextField pregnancyWeekField = new TextField();
        allowOnlyInteger(pregnancyWeekField);
        CheckBox fetalGrowthIssuesCheck = new CheckBox("Проблемы роста плода");
        CheckBox proteinUrineCheck = new CheckBox("Белок в моче");

        // Добавляем на сетку, но скрываем по умолчанию
        grid.add(gestationalLabel, 0, row++);
        Label pregnancyWeekLabel = new Label("Неделя беременности:");
        grid.add(pregnancyWeekLabel, 0, row);
        grid.add(pregnancyWeekField, 1, row++);
        grid.add(fetalGrowthIssuesCheck, 0, row);
        grid.add(proteinUrineCheck, 1, row++);
        grid.add(new Separator(), 0, row++, 2, 1);

        // Скрытие по умолчанию
        gestationalLabel.setVisible(false);
        gestationalLabel.setManaged(false);
        pregnancyWeekLabel.setVisible(false);
        pregnancyWeekLabel.setManaged(false);
        pregnancyWeekField.setVisible(false);
        pregnancyWeekField.setManaged(false);
        fetalGrowthIssuesCheck.setVisible(false);
        fetalGrowthIssuesCheck.setManaged(false);
        proteinUrineCheck.setVisible(false);
        proteinUrineCheck.setManaged(false);

        // -------------------- ВЗАИМОИСКЛЮЧЕНИЕ САХАРА --------------------
        highSugarCheck.setOnAction(e -> {
            if (highSugarCheck.isSelected() && lowSugarCheck.isSelected()) {
                showSugarConflictAlert();
            }
        });

        lowSugarCheck.setOnAction(e -> {
            if (lowSugarCheck.isSelected() && highSugarCheck.isSelected()) {
                showSugarConflictAlert();
            }
        });

        // -------------------- КНОПКА --------------------
        Button checkBtn = new Button("Проверить");
        grid.add(checkBtn, 0, row++);


        TextArea rulesArea = new TextArea();
        rulesArea.setEditable(false);
        rulesArea.setPromptText("Сработавшие правила:");
        rulesArea.setPrefHeight(100);
        rulesArea.setMinHeight(100);
        GridPane.setVgrow(rulesArea, Priority.ALWAYS);
        grid.add(rulesArea, 0, row, 2, 1);
        row++;

        TextArea conclusionsArea = new TextArea();
        conclusionsArea.setEditable(false);
        conclusionsArea.setPrefHeight(125);
        conclusionsArea.setMinHeight(125);
        conclusionsArea.setPromptText("Заключения:");
        grid.add(conclusionsArea, 0, row, 2, 1);


        diabetesTypeCombo.setOnAction(e -> {
            String type = diabetesTypeCombo.getValue();
            boolean showGestational = "Гестационный".equals(type);

            gestationalLabel.setVisible(showGestational);
            gestationalLabel.setManaged(showGestational);
            pregnancyWeekLabel.setVisible(showGestational);
            pregnancyWeekLabel.setManaged(showGestational);
            pregnancyWeekField.setVisible(showGestational);
            pregnancyWeekField.setManaged(showGestational);
            fetalGrowthIssuesCheck.setVisible(showGestational);
            fetalGrowthIssuesCheck.setManaged(showGestational);
            proteinUrineCheck.setVisible(showGestational);
            proteinUrineCheck.setManaged(showGestational);
        });


        checkBtn.setOnAction(e -> {
            rulesArea.clear();
            conclusionsArea.clear();

            if (diabetesTypeCombo.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "Выберите тип диабета").showAndWait();
                return;
            }

            Patient patient = new Patient();
            try {
                patient.setAge(Integer.parseInt(ageField.getText()));
                patient.setBmi(Double.parseDouble(bmiField.getText()));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Ошибка: неверные данные возраста или BMI").showAndWait();
                return;
            }

            patient.setThirst(thirstCheck.isSelected());
            patient.setFrequentUrination(urinationCheck.isSelected());
            patient.setFatigue(fatigueCheck.isSelected());
            patient.setBlurredVision(blurredVisionCheck.isSelected());
            patient.setHighSugar(highSugarCheck.isSelected());
            patient.setLowSugar(lowSugarCheck.isSelected());
            patient.setNumbness(numbnessCheck.isSelected());
            patient.setNightSweats(nightSweatsCheck.isSelected());
            patient.setRapidHeartbeat(rapidHeartbeatCheck.isSelected());
            patient.setInjectionSiteProblems(injectionSiteProblemsCheck.isSelected());

            patient.setDizziness(dizzinessCheck.isSelected());
            patient.setSweating(sweatingCheck.isSelected());
            patient.setHunger(hungerCheck.isSelected());
            patient.setConfusion(confusionCheck.isSelected());

            patient.setSkippedMeals(skippedMealsCheck.isSelected());
            patient.setPoorDiet(poorDietCheck.isSelected());
            patient.setLateEating(lateEatingCheck.isSelected());
            patient.setIrregularEating(irregularEatingCheck.isSelected());


            patient.setNeuropathyPain(neuropathyPainCheck.isSelected());
            patient.setFootUlcers(footUlcersCheck.isSelected());
            patient.setKidneyIssues(kidneyIssuesCheck.isSelected());
            patient.setHighBloodPressure(highBloodPressureCheck.isSelected());
            patient.setInfections(infectionsCheck.isSelected());
            patient.setSlowHealing(slowHealingCheck.isSelected());
            patient.setWeightLoss(weightLossCheck.isSelected());

            if (!pregnancyWeekField.getText().isEmpty()) {
                try {
                    patient.setPregnancyWeek(Integer.parseInt(pregnancyWeekField.getText()));
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Введите корректную неделю беременности").showAndWait();
                    return;
                }
            }
            patient.setFetalGrowthIssues(fetalGrowthIssuesCheck.isSelected());
            patient.setProteinInUrine(proteinUrineCheck.isSelected());

            patient.setDiabetesType(diabetesTypeCombo.getValue());

            // -------------------- DROOLS --------------------
            KieSession session = DroolsConfig.getSession();
            List<String> firedRules = new ArrayList<>();

            session.addEventListener(new org.kie.api.event.rule.DefaultAgendaEventListener() {
                @Override
                public void afterMatchFired(org.kie.api.event.rule.AfterMatchFiredEvent event) {
                    firedRules.add(event.getMatch().getRule().getName());
                }
            });

            session.insert(patient);
            session.fireAllRules();

            List<Conclusion> conclusions =
                    session.getObjects(o -> o instanceof Conclusion)
                            .stream().map(o -> (Conclusion) o).toList();



            session.dispose();

            rulesArea.setText(String.join("\n", firedRules));

            StringBuilder sb = new StringBuilder();
            for (Conclusion c : conclusions) sb.append(c.getMessage()).append("\n");
            conclusionsArea.setText(sb.toString());


        });

        // ScrollPane для формы
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(scrollPane, rulesArea, conclusionsArea);


        VBox.setVgrow(rulesArea, Priority.ALWAYS);
        VBox.setVgrow(conclusionsArea, Priority.ALWAYS);

        rulesArea.setPrefHeight(200);
        conclusionsArea.setPrefHeight(300);

// Создаём сцену
        Scene scene = new Scene(root, 700, 950);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void allowOnlyInteger(TextField textField) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(formatter);
    }

    private void allowOnlyDouble(TextField textField) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(formatter);
    }

    private void showSugarConflictAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода данных");
        alert.setHeaderText("Противоречивые данные");
        alert.setContentText(
                "Невозможно одновременно выбрать высокий и низкий уровень сахара.\n\n" +
                        "Пожалуйста, уточните состояние пациента."
        );
        alert.showAndWait();
    }
}