package com.diabetes.facts;

public class Patient {

    private int age;
    private double bmi;
    private String diabetesType;

    // Основные симптомы
    private boolean thirst;
    private boolean frequentUrination;
    private boolean fatigue;
    private boolean blurredVision;
    private boolean highSugar;
    private boolean lowSugar;
    private boolean numbness;
    private boolean injectionSiteProblems;
    private boolean nightSweats;
    private boolean rapidHeartbeat;

    // Гипогликемия
    private boolean dizziness;
    private boolean sweating;
    private boolean hunger;
    private boolean confusion;

    // Питание
    private boolean skippedMeals;
    private boolean poorDiet;
    private boolean lateEating;
    private boolean irregularEating;

    // Диабетические осложнения
    private boolean neuropathyPain;
    private boolean footUlcers;
    private boolean kidneyIssues;
    private boolean highBloodPressure;
    private boolean infections;
    private boolean slowHealing;
    private boolean weightLoss;

    // Гестационный диабет
    private Integer pregnancyWeek;
    private boolean fetalGrowthIssues;
    private boolean proteinInUrine;

    public Patient() {}

    public String getDiabetesType() { return diabetesType; }
    public void setDiabetesType(String diabetesType) { this.diabetesType = diabetesType; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getBmi() { return bmi; }
    public void setBmi(Double bmi) { this.bmi = bmi; }

    public Boolean getThirst() { return thirst; }
    public void setThirst(Boolean thirst) { this.thirst = thirst; }

    public Boolean getFrequentUrination() { return frequentUrination; }
    public void setFrequentUrination(Boolean frequentUrination) { this.frequentUrination = frequentUrination; }

    public Boolean getFatigue() { return fatigue; }
    public void setFatigue(Boolean fatigue) { this.fatigue = fatigue; }

    public Boolean getBlurredVision() { return blurredVision; }
    public void setBlurredVision(Boolean blurredVision) { this.blurredVision = blurredVision; }

    public Boolean getHighSugar() { return highSugar; }
    public void setHighSugar(Boolean highSugar) { this.highSugar = highSugar; }

    public Boolean getLowSugar() { return lowSugar;}
    public void setLowSugar(boolean selected) { this.lowSugar = lowSugar; }

    public Boolean getNumbness() { return numbness; }
    public void setNumbness(Boolean numbness) { this.numbness = numbness; }

    public Boolean getDizziness() { return dizziness; }
    public void setDizziness(Boolean dizziness) { this.dizziness = dizziness; }

    public Boolean getSweating() { return sweating; }
    public void setSweating(Boolean sweating) { this.sweating = sweating; }

    public Boolean getHunger() { return hunger; }
    public void setHunger(Boolean hunger) { this.hunger = hunger; }

    public Boolean getConfusion() { return confusion; }
    public void setConfusion(Boolean confusion) { this.confusion = confusion; }

    public Boolean getSkippedMeals() { return skippedMeals; }
    public void setSkippedMeals(Boolean skippedMeals) { this.skippedMeals = skippedMeals; }

    public Boolean getPoorDiet() { return poorDiet; }
    public void setPoorDiet(Boolean poorDiet) { this.poorDiet = poorDiet; }

    public Boolean getLateEating() { return lateEating; }
    public void setLateEating(Boolean lateEating) { this.lateEating = lateEating; }

    public Boolean getIrregularEating() { return irregularEating; }
    public void setIrregularEating(Boolean irregularEating) { this.irregularEating = irregularEating; }

    public Boolean getInjectionSiteProblems() { return injectionSiteProblems; }
    public void setInjectionSiteProblems(Boolean injectionSiteProblems) { this.injectionSiteProblems = injectionSiteProblems; }

    public Boolean getNeuropathyPain() { return neuropathyPain; }
    public void setNeuropathyPain(Boolean neuropathyPain) { this.neuropathyPain = neuropathyPain; }

    public Boolean getFootUlcers() { return footUlcers; }
    public void setFootUlcers(Boolean footUlcers) { this.footUlcers = footUlcers; }

    public Boolean getKidneyIssues() { return kidneyIssues; }
    public void setKidneyIssues(Boolean kidneyIssues) { this.kidneyIssues = kidneyIssues; }

    public Boolean getHighBloodPressure() { return highBloodPressure; }
    public void setHighBloodPressure(Boolean highBloodPressure) { this.highBloodPressure = highBloodPressure; }

    public Boolean getInfections() { return infections; }
    public void setInfections(Boolean infections) { this.infections = infections; }

    public Boolean getSlowHealing() { return slowHealing; }
    public void setSlowHealing(Boolean slowHealing) { this.slowHealing = slowHealing; }

    public Boolean getWeightLoss() { return weightLoss; }
    public void setWeightLoss(Boolean weightLoss) { this.weightLoss = weightLoss; }

    public Integer getPregnancyWeek() { return pregnancyWeek; }
    public void setPregnancyWeek(Integer pregnancyWeek) { this.pregnancyWeek = pregnancyWeek; }

    public Boolean getFetalGrowthIssues() { return fetalGrowthIssues; }
    public void setFetalGrowthIssues(Boolean fetalGrowthIssues) { this.fetalGrowthIssues = fetalGrowthIssues; }

    public Boolean getProteinInUrine() { return proteinInUrine; }
    public void setProteinInUrine(Boolean proteinInUrine) { this.proteinInUrine = proteinInUrine; }

    public Boolean getNightSweats() { return nightSweats; }
    public void setNightSweats(Boolean nightSweats) { this.nightSweats = nightSweats; }

    public Boolean getRapidHeartbeat() { return rapidHeartbeat; }
    public void setRapidHeartbeat(Boolean rapidHeartbeat) { this.rapidHeartbeat = rapidHeartbeat; }
}
