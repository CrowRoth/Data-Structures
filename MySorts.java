public class MySorts {
    public static void insertSort(int [] arr){
        for (int i = 1; i < CSCI251ProjOne.RANGE; i++) { //CSCI251ProjOne has final int RANGE for size of array
            int temp = 0;
            int j = i;
            // Insert arr[i] into sorted part
            // stopping once arr[i] in correct position
            while (j > 0 && arr[j] < arr[j - 1]) {
                
               // Swap arr[j] and arr[j - 1]
               temp = arr[j];
               arr[j] = arr[j - 1];
               arr[j - 1] = temp;
               j--;
            }
         }
    };

    public static void selectSort(int [] arr){
        for (int i = 0; i < CSCI251ProjOne.RANGE - 1; i++) {
            int temp = 0;
            // Find index of smallest remaining element
            int indexSmallest = i;
            for (int j = i + 1; j < CSCI251ProjOne.RANGE; j++) {
         
               if (arr[j] < arr[indexSmallest]) {
                  indexSmallest = j;
               }
            }
         
            // Swap arr[i] and arr[indexSmallest]
            temp = arr[i];
            arr[i] = arr[indexSmallest];
            arr[indexSmallest] = temp;
         }
    };

    public static void quickSort(int [] arr){
        quickSortRecursive(arr, 0, CSCI251ProjOne.RANGE - 1);
    };

    private static int pivot(int[] arr, int begin, int end) {
        // find midpoint and set its value as pivot
        int midpoint = begin + (end - begin) / 2;
        int pivot = arr[midpoint];
        
        boolean done = false;
        while (!done) {
            // move begin to next index while arr[begin] < pivot
            while (arr[begin] < pivot) {
                begin++;
            }
            
            // move end down an index while pivot < arr[end]
            while (pivot < arr[end]) {
                end--;
            }
            
            // base case again
            if (begin >= end) {
                done = true;
            }
            else {
                // Swap arr[begin] and arr[end]
                int temp = arr[begin];
                arr[begin] = arr[end];
                arr[end] = temp;
                
                // Update begin and end
                begin++;
                end--;
            }
        }
        
        return end;
    };

    private static void quickSortRecursive(int[] arr, int begin, int end) {
        //base case = sorted
        if (begin >= end) {
            return;
        }
        
        //partition into low and high partitions
        int lowEndIndex = pivot(arr, begin, end);
        
        // Recursively sort low partition (lowIndex to lowEndIndex) 
        // and high partition (lowEndIndex + 1 to highIndex)
        quickSortRecursive(arr, begin, lowEndIndex);
        quickSortRecursive(arr, lowEndIndex + 1, end);
    };

    public static void mergeSort(int [] arr){
        mergeSortRecursive(arr, 0, CSCI251ProjOne.RANGE - 1); // call mergesortrecursive and send arr, 0(first index), lastindex
    }

    private static void merge(int[] arr, int begin, int middle, int end) {
        int mergedSize = end - begin + 1;           // Size of merged partition
        int mergePos = 0;                           // Position to insert merged number
        int leftPos = 0;                            // Position of elements in left partition
        int rightPos = 0;                           // Position of elements in right partition
        int[] mergedNumbers = new int[mergedSize];  // temporary array for merged numbers
        
        leftPos = begin;                            // Initialize left partition position
        rightPos = middle + 1;                      // Initialize right partition position
        
        while (leftPos <= middle && rightPos <= end) {
            if (arr[leftPos] <= arr[rightPos]) { //compare next smallest element from left partition to mergednumbers array
                mergedNumbers[mergePos] = arr[leftPos]; // if left position element was smaller insert into mergednumbers
                leftPos++; // move up left position
            }
            else {
                mergedNumbers[mergePos] = arr[rightPos]; // if right position element was smaller insert into mergednumbers
                rightPos++; // move down right position
            }
            mergePos++; //move merge position up to fill next slot in mergednumbers array
        }
        
        
        while (leftPos <= middle) { //check if left partitions position is below or equal to middle meaning its not empty
            mergedNumbers[mergePos] = arr[leftPos]; //add remaining elements from left partition to mergednumbers array
            leftPos++; //move up left position
            mergePos++; //move up mergenumbers array position
        }
        
        // If right partition is not empty, add remaining elements to merged numbers
        while (rightPos <= end) { //check if right partition is not empty
            mergedNumbers[mergePos] = arr[rightPos]; //add remaining elements from right partition to mergednmumbers array
            rightPos++; //move up right postion
            mergePos++; // move up mergenumbers array postion
        }
        
        // Copy merge number back to numbers
        for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
            arr[begin + mergePos] = mergedNumbers[mergePos];//copy temporary array mergednumbers to arr
        }
    };

    private static void mergeSortRecursive(int[] arr, int begin, int end) {
        int middle = 0; //initialize middle
    
        if (begin < end) { //if not done sorting
            middle = (begin + end) / 2;  // Find the midpoint in the partition
            
            // Recursively call sort left and right partitions
            mergeSortRecursive(arr, begin, middle);
            mergeSortRecursive(arr, middle + 1, end);
            
            // Merge left and right partition in sorted order
            merge(arr, begin, middle, end);
        }
    }
}
