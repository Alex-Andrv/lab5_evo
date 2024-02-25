package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class QueenFitnessFunction implements FitnessEvaluator<QueenSolution> {

    public double getFitness(QueenSolution solution, List<? extends QueenSolution> list) {
        int dimension = solution.getDimension();
        List<Integer> solutionList =  solution.getSolution();
        List<Integer> countConflictLeftDown = new ArrayList<>();
        for (int i = 0; i < 2 * dimension - 1; i++) {
            countConflictLeftDown.add(0);
        }
        List<Integer> countConflictRightDown = new ArrayList<>();
        for (int i = 0; i < 2 * dimension - 1; i++) {
            countConflictRightDown.add(0);
        }
        for (int i = 0; i < dimension; i++) {
            int j = solutionList.get(i);
            if (j - i + (dimension - 1) < 0 || j - i + (dimension - 1) >= 2 * dimension - 1) {
                throw new Error("New Error");
            }
            countConflictLeftDown.set(j - i + (dimension - 1), countConflictLeftDown.get(j - i + (dimension - 1)) + 1);
            countConflictRightDown.set(j + i, countConflictLeftDown.get(j + i) + 1);
        }
        double fitness = 0.;
        for (int i = 0; i < 2 * dimension - 1; i++) {
            fitness += Math.pow(countConflictLeftDown.get(i), 2) - countConflictLeftDown.get(i);
            fitness += Math.pow(countConflictRightDown.get(i), 2) - countConflictRightDown.get(i);
        }
        return fitness;
    }

    public boolean isNatural() {
        return false;
    }
}
