package com.blitz.genetic.simple;

public class Main {
	//To make all bits to 1
    public static void main(String[] args) {
    	//For this question,we can increase estismCount , descrease mutaiotnRate and crossoverRate to accerate our step to best fitness.
	    GeneticAlgorithm ga = new GeneticAlgorithm(100,0.001,0.95,12);
	    Population population = ga.initPopulation(50);

	    ga.evalPopulation(population);

	    int generation = 1;
	    while (ga.isTerminationConditionMet(population) == false){
	        System.out.println("Best solution:" + population.getFittest(0).toString());

	        // Apply crossover
			population = ga.crossoverPopulation(population);
            // Apply mutation
			population = ga.mutatePopulation(population);
	        ga.evalPopulation(population);

	        generation++;
        }

        System.out.println("Found solution in " + generation + " generations");
	    System.out.println("Best solution: " + population.getFittest(0).toString());
    }
}
