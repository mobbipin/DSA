import java.util.Collections;
import java.util.PriorityQueue;

public class ScoreTracker {
    private final PriorityQueue<Double> lowerHalf;
    private final PriorityQueue<Double> upperHalf;

    public ScoreTracker() {
        lowerHalf = new PriorityQueue<>();
        upperHalf = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addScore(double score) {
        if (lowerHalf.isEmpty() || score < lowerHalf.peek()) {
            lowerHalf.add(score);
        } else {
            upperHalf.add(score);
        }

        // Balance the heaps
        if (lowerHalf.size() > upperHalf.size() +  1) {
            upperHalf.add(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.add(upperHalf.poll());
        }
    }

    public double getMedianScore() {
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) /  2.0;
        } else {
            return lowerHalf.peek();
        }
    }

  
    public static void main(String[] args) {
        ScoreTracker scoreTracker = new ScoreTracker();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        System.out.println(scoreTracker.getMedianScore()); 
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        System.out.println(scoreTracker.getMedianScore()); 
    }
}
