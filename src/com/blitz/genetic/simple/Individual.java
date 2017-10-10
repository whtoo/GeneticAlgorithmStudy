package com.blitz.genetic.simple;

/**
 * Created by blitz on 2017/10/10.
 */
public class Individual {
    private int[] chromosome;
    private  double fitnes = -1;
    public Individual(int[] chromosome){
        this.chromosome = chromosome;
    }

    public Individual(int chromesomeLength){
        this.chromosome = new int[chromesomeLength];
        for (int gene = 0; gene < chromesomeLength; gene++){
            if (0.5 < Math.random()){
                this.setGene(gene,1);
            } else {
                this.setGene(gene,0);
            }
        }
    }

    public  int[] getChromosome(){
        return this.chromosome;
    }

    public  int getChromosomeLength() {
        return this.chromosome.length;
    }

    public  void setGene(int offset,int gene){
        this.chromosome[offset] = gene;
    }

    public int getGene(int offset){
        return this.chromosome[offset];
    }

    public void setFitnes(double fitnes){
        this.fitnes = fitnes;
    }

    public double getFitnes(){
        return this.fitnes;
    }

    public String toString(){
        String output = "";
        for (int gene = 0; gene < this.chromosome.length;gene++){
            output += this.chromosome[gene];
        }
        return output;
    }
}
