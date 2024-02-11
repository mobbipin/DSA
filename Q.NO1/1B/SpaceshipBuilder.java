import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Engine {
    int buildTime;

    public Engine(int buildTime) {
        this.buildTime = buildTime;
    }
}

public class SpaceshipBuilder {
    private static final Comparator<Integer> COMPARATOR = Integer::compareTo;

    public static int minTime(List<Engine> engines, int splitCost) {
        engines.sort(Comparator.comparingInt(e -> e.buildTime));
        int[] dp = new int[engines.size() +  1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] =  0;
        PriorityQueue<Integer> workloads = new PriorityQueue<>(COMPARATOR);

        for (int i =  1; i <= engines.size(); i++) {
            int buildTime = engines.get(i -  1).buildTime;
            while (!workloads.isEmpty()) {
                int workload = workloads.peek();
                int buildWithCurrentWorkload = Math.max(workload, buildTime);
                int splitAndBuild = splitCost + buildTime;

                if (buildWithCurrentWorkload <= splitAndBuild) {
                    dp[i] = Math.min(dp[i], dp[i -  1] + buildWithCurrentWorkload);
                } else {
                    dp[i] = Math.min(dp[i], dp[i -  1] + splitAndBuild);
                }

                workloads.poll();
                workloads.offer(buildWithCurrentWorkload);
            }

           
            workloads.add(buildTime);
        }

        return dp[engines.size()];
    }

    public static void main(String[] args) {
        List<Engine> engines = Arrays.asList(new Engine(1), new Engine(2), new Engine(3));
        int splitCost =  1;

        int minTime = minTime(engines, splitCost);
        System.out.println("Minimum time to build all engines: " + minTime);
    }
}

