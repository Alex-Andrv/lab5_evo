package lab3;

import java.util.List;
import java.util.stream.Collectors;

public class QueenSolution {
    // any required fields and methods
    private int dimension;
    List<Integer> solution;

    public QueenSolution(int dimension, List<Integer> solution) {
        this.dimension = dimension;
        this.solution = solution;
    }

    public int getDimension() {
        return dimension;
    }

    public List<Integer> getSolution() {
        return solution;
    }

    public String toString() {
        return String.format("best solution: [%s]", solution.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
