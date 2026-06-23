public class HealthNetDP {

    // Function to solve 0/1 Knapsack using Dynamic Programming
    static int knapsack(int[] weight, int[] utility, int capacity, int n) {

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            utility[i - 1] + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]
                    );
                }
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {

        // Supply details
        int[] weight = {3, 7, 5, 4};
        int[] utility = {50, 90, 70, 60};

        int capacity = 15;
        int n = weight.length;

        System.out.println("HealthNet - Dynamic Programming Medical Resource Optimization Module (CO6)");

        System.out.println("\nMaximum Emergency Field Kit Weight Capacity: "
                + capacity + " Kilograms");

        System.out.println("\nAvailable Disaster Supply Profiles:");
        System.out.println("Item 1 (Advanced Trauma Dressing) -> Weight: 3 kg | Utility: 50");
        System.out.println("Item 2 (Portable Oxygen Cylinder) -> Weight: 7 kg | Utility: 90");
        System.out.println("Item 3 (Emergency Surgical Kit) -> Weight: 5 kg | Utility: 70");
        System.out.println("Item 4 (Broad Spectrum Antibiotics) -> Weight: 4 kg | Utility: 60");

        System.out.println("\nConstructing Dynamic Programming Tabulation Decision Grid...");
        System.out.println("Evaluating overlapping subproblems and identifying optimal sub-structures...");

        int maxUtility = knapsack(weight, utility, capacity, n);

        System.out.println("\nMaximum Achieved Life-Saving Utility Score = "
                + maxUtility);

        System.out.println("\nSystem Status: Optimization complete. Resource payload fully maximized.");
    }
}