import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SecretSharing {
    public static List<Integer> secretSharing(int n, List<int[]> intervals, int firstPerson) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] knownSecrets = new boolean[n];
        knownSecrets[firstPerson] = true;
        queue.offer(firstPerson);
        
        while (!queue.isEmpty()) {
            int currentPerson = queue.poll();
            for (int[] interval : intervals) {
                if (interval[0] <= currentPerson && currentPerson <= interval[1]) {
                    for (int i = interval[0]; i <= interval[1]; i++) {
                        if (!knownSecrets[i]) {
                            knownSecrets[i] = true;
                            queue.offer(i);
                        }
                    }
                }
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i =  0; i < n; i++) {
            if (knownSecrets[i]) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n =  5;
        List<int[]> intervals = Arrays.asList(new int[]{0,  2}, new int[]{1,  3}, new int[]{2,  4});
        int firstPerson =  0;
        List<Integer> output = secretSharing(n, intervals, firstPerson);
        System.out.println(output); 
    }
}
