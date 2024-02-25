package lab3;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Random;

public class QueensAlg {

    public static void main(String[] args) throws Exception {
        int dimension = 10000; // name of problem or path to input file

        int populationSize = 10; // size of population
        int generations = 200000; // number of generations

        Random random = new Random(); // random

        ArrayList<EvolutionaryOperator<QueenSolution>> operators = new ArrayList<EvolutionaryOperator<QueenSolution>>();
        operators.add(new QueensCrossover()); // Crossover
        operators.add(new QueensMutation()); // Mutation
        EvolutionPipeline<QueenSolution> pipeline = new EvolutionPipeline<QueenSolution>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator
        selection = new TournamentSelection(new Probability(0.9));

        FitnessEvaluator<QueenSolution> evaluator = new QueenFitnessFunction(); // Fitness function

        CandidateFactory<QueenSolution> factory = new QueensFactory(dimension); // generation of solutions

        EvolutionEngine<QueenSolution> algorithm = new SteadyStateEvolutionEngine<QueenSolution>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                QueenSolution best = (QueenSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.toString());
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 5, terminate);
    }
}
