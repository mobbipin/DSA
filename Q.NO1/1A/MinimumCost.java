import java.util.Arrays;

public class MinimumCost{

    public static int minCostToDecorate(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;

        int[][] d = new int[n][k];
        for (int[] row : d) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        
        for (int i = 0; i < k; i++) {
            d[0][i] = costs[0][i];
        }

       
        for (int i = 1; i < n; i++) {
            
            for (int j = 0; j < k; j++) {
                
                int minPrev = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        minPrev = Math.min(minPrev, d[i - 1][l]);
                    }
                }
                d[i][j] = minPrev + costs[i][j];
            }
        }

        
        int minTotalCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minTotalCost = Math.min(minTotalCost, d[n - 1][i]);
        }
        return minTotalCost;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[][] costs = {{1,  5,  3}, {2,  9,  4}};
        System.out.println(minCostToDecorate(costs));
    }
    }