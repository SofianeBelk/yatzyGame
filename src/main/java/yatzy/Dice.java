package yatzy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dice {
    private final List<Integer> faces;

    public Dice(int d1, int d2, int d3, int d4, int d5){
        this.faces = Arrays.asList(d1, d2, d3, d4, d5);
    }

    private Map<Integer, Long> counts(){
        return this.faces.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int sum(){
        return this.faces.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean hasElementWithNumberFive(){
        return counts().values().stream()
                .anyMatch(value -> value == 5);
    }

    public int diceCount(int face){
        return counts().getOrDefault(face, 0L).intValue();
    }

    public List<Integer> findPairs(){
        return counts().entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public int getDiceWithNumberOfAKind(int n){
        return counts().entrySet().stream()
                .filter(e -> e.getValue() >= n)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
    }

    public boolean isSmallStraight(){
        return faces.stream().sorted().collect(Collectors.toList()).equals(Arrays.asList(1, 2, 3, 4, 5));
    }

    public boolean isLargeStraight(){
        return faces.stream().sorted().collect(Collectors.toList()).equals(Arrays.asList(2, 3, 4, 5, 6));
    }

}