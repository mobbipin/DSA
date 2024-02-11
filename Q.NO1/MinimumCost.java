import java.util.Arrays;

public class MinimumCost{

    public static int minCostToDecorate(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;

        int[][] dp = new int[n][k];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

       
        for (int i = 1; i < n; i++) {
            
            for (int j = 0; j < k; j++) {
                
                int minPrev = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        minPrev = Math.min(minPrev, dp[i - 1][l]);
                    }
                }
                dp[i][j] = minPrev + costs[i][j];
            }
        }

        
        int minTotalCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minTotalCost = Math.min(minTotalCost, dp[n - 1][i]);
        }
        return minTotalCost;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[][] costs = {{1,  5,  3}, {2,  9,  4}};
        System.out.println(minCostToDecorate(costs)); 
    }
    }