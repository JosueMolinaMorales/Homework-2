/**
 * File: HW02_PartC_MolinaMorales_Josue.java
 * This file will run all MergeSort Methods from A-D
 * Starting with 1M elements continuing to 3M.
 * The methods will sort the same numbers to test for runtime.
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HW02_PartC_MolinaMorales_Josue {
    public static void main(String[] args){
        //arrays to use
        double[] numbersA = null;
        double[] numbersB = null;
        double[] numbersC = null;
        double[] numbersD = null;
        long[][] durations = new long[5][4];
        int rowCounter = 0;
        int numIterations = 1;

        for(int i = 0; i < numIterations; i++){
            System.out.println("================ITERATION " + (i+1) + "================");
            //1 million numbers
            System.out.println("==============1M NUMBERS==========");
            numbersA = readNumbers(1000000);
            numbersB = numbersA.clone();
            numbersC = numbersA.clone();
            numbersD = numbersA.clone();
            runSortingAlgorithms(numbersA, numbersB, numbersC, numbersD, durations, rowCounter);
            rowCounter++;

            //1.5 million numbers
            System.out.println("==============1.5M NUMBERS==========");
            numbersA = readNumbers(1500000);
            numbersB = numbersA.clone();
            numbersC = numbersA.clone();
            numbersD = numbersA.clone();
            runSortingAlgorithms(numbersA, numbersB, numbersC, numbersD, durations, rowCounter);
            rowCounter++;

            //2 million numbers
            System.out.println("==============2M NUMBERS==========");
            numbersA = readNumbers(2000000);
            numbersB = numbersA.clone();
            numbersC = numbersA.clone();
            numbersD = numbersA.clone();
            runSortingAlgorithms(numbersA, numbersB, numbersC, numbersD, durations, rowCounter);
            rowCounter++;

            //2.5 million numbers
            System.out.println("==============2.5M NUMBERS==========");
            numbersA = readNumbers(2500000);
            numbersB = numbersA.clone();
            numbersC = numbersA.clone();
            numbersD = numbersA.clone();
            runSortingAlgorithms(numbersA, numbersB, numbersC, numbersD, durations, rowCounter);
            rowCounter++;

            //3 million numbers
            System.out.println("==============3M NUMBERS==========");
            numbersA = readNumbers(3000000);
            numbersB = numbersA.clone();
            numbersC = numbersA.clone();
            numbersD = numbersA.clone();
            runSortingAlgorithms(numbersA, numbersB, numbersC, numbersD, durations, rowCounter);
            rowCounter++;

            rowCounter = 0;
        }

        /*
        Rows are the elements of the array
        Column are the different merge sort methods
        What will be printed out will be the average.
        */
        for(int i = 0; i < durations.length; i++){
            for(int j = 0; j < durations[i].length; j++){
                System.out.print((durations[i][j]/(double)numIterations) + " ");
            }
            System.out.println();
        }

    }

    /**
     * runSortingAlgorithms takes in all the double arrays that will be sorted and sort them
     * @param numbersA MergeSortA() array
     * @param numbersB MergeSortB() array
     * @param numbersC MergeSortC() array
     * @param numbersD MergeSortD() array
     * @param durations Durations is a 2d array that helps find the average runtime for each 
     * @param rowCounter Keeps track what method is being calculated, 0 - A, 1 - B, and so on 
     */
    public static void runSortingAlgorithms(double[] numbersA, double[] numbersB, double[] numbersC, double[] numbersD, long[][] durations, int rowCounter){
        int colCounter = 0;
        //MergeSortA
        long startTime = System.nanoTime();
        mergeSortA(numbersA, 0, numbersA.length-1);
        long endTime = System.nanoTime(); 
        long duration = (endTime - startTime);
        System.out.println("MergeSortA took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
        durations[rowCounter][colCounter] += convertNanoToMillis(duration);
        colCounter++;

        //MergeSortB
		startTime = System.nanoTime();
		mergeSortB(numbersB);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortB took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
		durations[rowCounter][colCounter] += convertNanoToMillis(duration);
        colCounter++;

		//MergeSortC
		startTime = System.nanoTime();
		mergeSortC(numbersC, 0, numbersC.length-1, 15);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortC took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
        durations[rowCounter][colCounter] += convertNanoToMillis(duration);
        colCounter++;

        //mergeSortD
        startTime = System.nanoTime();
		mergeSortD(numbersD, 15);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortD took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
        durations[rowCounter][colCounter] += convertNanoToMillis(duration);
        colCounter++;
    }

    public static long convertNanoToMillis(long nano){
        return TimeUnit.NANOSECONDS.toMillis(nano);
    }

    public static void printArr(double[] numbers){
        for(int i = 0; i < numbers.length; i++){
            System.out.print(String.format("%.5f",numbers[i]) + " ");
        }
        System.out.println();
    }

    /**
     * MergeSortA implements merge sort with recursion. This version does not use insertion sort
     * as sub procedure
     * @param arr The array to be sorted
     * @param left left index of the sub array
     * @param right right index of the sub array
     */
    public static void mergeSortA(double[] arr, int left, int right){ 
        if(left < right){
            int mid = (left + right) / 2;
            mergeSortA(arr, left, mid);
            mergeSortA(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * MergeSortB implements merge sort using for loops and merge as a subprocedure
     * B uses a bottom up approach, starting with a subarr len 1 and working its way up to the len of
     * arr
     * @param arr the array to be sorted
     */
    public static void mergeSortB(double[] arr){ 
        int subArrLen = 0;
        int left = 0; //left subarray pointer
        int right = 0; //right pointer
        int mid = 0;

        for(subArrLen = 1; subArrLen <= arr.length-1; subArrLen = 2*subArrLen){ //outer loop keeps track of the len of the subarr
            for(left = 0; left < arr.length-1; left += 2*subArrLen){ //inner loops keeps track of where left starts
                mid = Math.min(left + subArrLen - 1, arr.length - 1);
                right = Math.min(left + 2*subArrLen - 1, arr.length-1); //calc right, if left+2*subarrlen is greater than the length of the arr, len will be the right index

                merge(arr, left, mid, right);
            }
        }
    }
    
    /**
     * MergeSortC implements merge sort with recursion and uses inserstion sort when the sub array becomes length = size
     * @param arr arr to be sorted
     * @param left index of the start of the subarr
     * @param right index of the end of the subarr
     * @param size the size of the max subarray
     */
    public static void mergeSortC(double[] arr, int left, int right, int size){ //Recursion and Insertion Sort
        if(right-left+1 <= size){
            insertionSort(arr, left, right+1);
        }else{
			int mid = (left + right) / 2;
			mergeSortC(arr, left, mid, size);
            mergeSortC(arr, mid+1, right, size);	
            merge(arr, left, mid, right);
		}
    }
    
    /**
     * MergeSortD implements merge sort with for loops and uses insertion sort as a subprocedure
     * @param arr array to be sorted
     * @param size the size of the max subarray
     */
    public static void mergeSortD(double[] arr, int size){
        int subArrLen = 0;
        int left = 0; 
        int right = 0; 
        int mid = 0;

        for(subArrLen = size; subArrLen <= arr.length-1; subArrLen = 2*subArrLen){ 
            for(left = 0; left < arr.length-1; left += 2*subArrLen){ 
                right = Math.min(left + 2*subArrLen, arr.length); 
                if(subArrLen <= size){
                    insertionSort(arr, left, right);
                }else{
                    mid = Math.min(left + subArrLen, arr.length);
                    merge(arr, left, mid-1, right-1);
                }
            }
        }

    }


    public static void merge(double[] arr, int left, int mid, int right){
        //System.out.println("left: " + left + " right: " + right + " mid: " + mid);
        int leftArrSize = mid - left + 1; //size of the left subarray 
        int rightArrSize = right - mid; //size of the right subarray

        //create two arrays
        double[] leftArr = new double[leftArrSize+1]; //one extra spot to hold infinity 
        double[] rightArr = new double[rightArrSize+1];


        //copy the respective sides into their arrs
        for(int i = 0; i < leftArrSize; i++){
            leftArr[i] = arr[left + i];
        }
        for(int j = 0; j < rightArrSize; j++){
            rightArr[j] = arr[mid + 1 + j];
        }

        leftArr[leftArrSize] = Double.POSITIVE_INFINITY;
        rightArr[rightArrSize] = Double.POSITIVE_INFINITY;

        int leftIndex = 0;
        int rightIndex = 0;
        for(int i = left; i <= right; i++){
            if(leftArr[leftIndex] <= rightArr[rightIndex]){
                arr[i] = leftArr[leftIndex];
                leftIndex++;
            }else{
                arr[i] = rightArr[rightIndex];
                rightIndex++;
            }
        }
    }
    
    public static void insertionSort(double[] arr, int left, int right){
        //number that is being compared. This is the number that will be moved back in the while loop
        double key = 0;
        //index for swapping numbers
        int i = 0;
        
        //assumes arr[0] is already sorted and starts at index left+1
        for(int j = left+1; j < right; j++){
            //the next number to sort
            key = arr[j];
            
            //insert arr[j] into the sorted sequence arr[1..j-1]
            i = j - 1;
            
            //while the number in index i is greater than the number being compared
            //move that number to the right and decrement i
            while(i > left-1 && arr[i] > key){
                arr[i+1] = arr[i];
                i -= 1;
            }    
            //after the loop, place the key into the correct place
            arr[i+1] = key;
        }
    }
    
    /**
    * Generates {size} random numbers by calling a helper function genNumbers()
    * reads the file that was created, and creates an array that stores all the numbers
    * returns this array
    * @param size how many numbers to generate
    * @return a double array containing psuedo random numbers 
    */
    public static double[] readNumbers(int size){
        final String FILENAME = "inputHW02.txt";
        
        GenerateNumbers.genNumbers(size, FILENAME);
        
        File numFile = null;
        Scanner scan = null;
        
        double[] randNumbers = new double[size];
        double num = 0;
        
        try{
            numFile = new File(FILENAME);
            scan = new Scanner(numFile);
            
            for(int i = 0; i < randNumbers.length; i++){
                num = Double.parseDouble(scan.nextLine());
                randNumbers[i] = num;
            }
            
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        scan.close();
        return randNumbers;
    }
}
/* testing
double[] numbers = readNumbers(3000000);
        double[] numbers2 = numbers.clone();
        double[] numbers3 = numbers.clone();
		double[] numbers4 = numbers.clone();
		double[] numbers5 = numbers.clone();
		System.out.println("DONE PRINT AND CLONING. BEGINNING SORTING\n");
        //printArr(numbers);
		//Insertion Sort
        // long startTime = System.nanoTime();
		// //insertionSort(numbers, 0, numbers.length);
        // long endTime = System.nanoTime(); 
        // long duration = (endTime - startTime);
        // System.out.println("Insertion Sort took: " + duration + "\n");
        //printArr(numbers);
		
		//MergeSortA
		long startTime = System.nanoTime();
		mergeSortA(numbers2, 0, numbers2.length-1);
        long endTime = System.nanoTime(); 
        long duration = (endTime - startTime);
        System.out.println("MergeSortA took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
		
		//MergeSortB
		startTime = System.nanoTime();
		mergeSortB(numbers3);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortB took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
		
		//MergeSortC
		startTime = System.nanoTime();
		mergeSortC(numbers4, 0, numbers4.length-1, 10);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortC took: " + convertNanoToMillis(duration) + " MilliSeconds\n");

        //printArr(numbers2);

        //mergeSortD
        startTime = System.nanoTime();
		mergeSortD(numbers5, 2);
        endTime = System.nanoTime(); 
        duration = (endTime - startTime);
        System.out.println("MergeSortD took: " + convertNanoToMillis(duration) + " MilliSeconds\n");
        //printArr(numbers5);
		
		//insertionSort(numbers, 0, numbers.length);
        //mergeSortA(numbers, 0, numbers.length-1);
        //mergeSortB(numbers);
*/