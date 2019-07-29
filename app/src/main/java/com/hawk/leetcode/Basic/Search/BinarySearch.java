package com.hawk.leetcode.Basic.Search;


public class BinarySearch {
    public static void main(String[] args) {
                          // 0  1  2  3   4   5   6   7   8   9  10  11  12
        int[] sortedArray = {2, 3, 5, 7, 11, 15, 16, 17, 21, 24, 27, 33, 38};

        int index;
        // index = binarySearch_Iterator(sortedArray, 5, 3, 9);
        index = binarySearch_Recursive(sortedArray, 15, 3, 9);
        System.out.println();
    }

    static public int binarySearch_Iterator(
            int[] sortedArray, int target, int lowBound, int highBound) {
        int index = -1;

        while (lowBound <= highBound) {
            int mid = (lowBound + highBound) / 2;
            if (sortedArray[mid] < target) {
                lowBound = mid + 1;
            } else if (sortedArray[mid] > target) {
                highBound = mid - 1;
            } else if (sortedArray[mid] == target) {
                index = mid;
                break;
            }
        }
        return index;
    }

    static public int binarySearch_Recursive ( int[] sortedArray, int target, int lowBound, int highBound) {
        int middle = (lowBound + highBound) / 2;

        if (highBound < lowBound) {
            return -1;
        }

        if (target == sortedArray[middle])
            return middle;
        else if (target < sortedArray[middle])
            return binarySearch_Recursive( sortedArray, target, lowBound, middle - 1);
        else
            return binarySearch_Recursive(  sortedArray, target, middle + 1, highBound);
    }

}
