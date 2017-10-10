package com.blitz.genetic.simple;

/**
 * Created by blitz on 2017/10/10.
 */
public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int estismCount;

    public GeneticAlgorithm(int populationSize,double mutationRate,double crossoverRate,int estismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.estismCount = estismCount;
    }

    public Population initPopulation(int chromosomeLength){
        Population population = new Population(this.populationSize,chromosomeLength);
        return  population;
    }

    public double calcFitness(Individual individual){
         int correctGenes = 0;

         for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++){
             if (individual.getGene(geneIndex) == 1){
                 correctGenes +=1;
             }
         }

         double fitness = (double) correctGenes / individual.getChromosomeLength();
         individual.setFitnes(fitness);

         return fitness;
    }

    public void evalPopulation(Population population){
        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()){
            populationFitness += calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population){
        for (Individual individual: population.getIndividuals()){
            if (individual.getFitnes() == 1){
                return true;
            }
        }

        return false;
    }


    public Individual selectParent(Population population){
        Individual individuals[] = population.getIndividuals();
        double populationFitness = population.getPopulationFitness();
        double rouleWheelPosition = Math.random() * populationFitness;

        double spinWheel = 0;
        for (Individual individual : individuals){
            spinWheel += individual.getFitnes();
            if (spinWheel >= rouleWheelPosition){
                return  individual;
            }
        }

        return individuals[population.size() - 1];
    }

    public  Population crossoverPopulation(Population population){
        Population newPopulation = new Population(population.size());

        for (int populationIndex = 0;populationIndex < population.size(); populationIndex++){
            Individual parent1 = population.getFittest(populationIndex);

            if (this.crossoverRate > Math.random() && populationIndex > this.estismCount){
                Individual offsprint = new Individual(parent1.getChromosome());
                Individual parent2 = selectParent(population);

                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength();geneIndex++){
                    if (0.5 > Math.random()){
                        offsprint.setGene(geneIndex,parent1.getGene(geneIndex));
                    } else {
                        offsprint.setGene(geneIndex,parent2.getGene(geneIndex));
                    }
                }

                newPopulation.setIndividual(populationIndex,offsprint);
            } else {
                newPopulation.setIndividual(populationIndex,parent1);
            }
        }

        return newPopulation;
    }

    public Population mutatePopulation(Population population){
        Population newPopulation = new Population(this.populationSize);

        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++){
            Individual individual = population.getFittest(populationIndex);
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++){
                if ((this.mutationRate > Math.random())){
                    int newGene = 1;
                    if (individual.getGene(geneIndex) == 1){
                        newGene = 0;
                    }

                    individual.setGene(geneIndex,newGene);
                }


            }

            newPopulation.setIndividual(populationIndex,individual);
        }

        return newPopulation;
    }



}
