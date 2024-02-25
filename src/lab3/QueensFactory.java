package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QueensFactory extends AbstractCandidateFactory<QueenSolution> {
    private int dimension;
    public QueensFactory(int dimension) {
        this.dimension = dimension;
    }

    public QueenSolution generateRandomCandidate(Random random) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution, random);

        return new QueenSolution(dimension, solution);
    }
}

