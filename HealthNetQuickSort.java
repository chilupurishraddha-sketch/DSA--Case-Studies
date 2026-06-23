import java.util.Random;

public class HealthNetQuickSort {

    // Function to swap elements
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Partition function
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Descending order (highest severity first)
            if (arr[j] > pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Randomized pivot selection
    static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1);

        swap(arr, pivotIndex, high);
        return partition(arr, low, high);
    }

    // Quick Sort function
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Main Method
    public static void main(String[] args) {

        int[] severityScores = {67, 92, 45, 89, 71, 99, 54, 80};

        System.out.println("HealthNet - Advanced Triage Prioritization & Sorting System (CO5)");

        System.out.println("\nLoading Raw Unsorted Emergency Patient Severity Index Rows:");
        System.out.print("Unsorted Array: [");

        for (int i = 0; i < severityScores.length; i++) {
            System.out.print(severityScores[i]);
            if (i < severityScores.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("\nInitiating Quick Sort Strategy utilizing Randomized Pivot Allocation Rules...");
        System.out.println("Slicing sub-arrays and updating memory bounds sequentially...");

        quickSort(severityScores, 0, severityScores.length - 1);

        System.out.println("\nSorted Triage Ranking Register (Descending Priority Order):");

        for (int i = 0; i < severityScores.length; i++) {
            System.out.println("Priority " + (i + 1)
                    + ": Severity Score "
                    + severityScores[i]
                    + " (Patient Ref: P-10" + (i + 1) + ")");
        }

        System.out.println("\nEmpirical Benchmarking Report:");
        System.out.println("Quick Sort Average Time Complexity: O(n log n)");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Auxiliary Space Complexity: O(log n)");
        System.out.println("Sorting achieved successfully.");
    }
}