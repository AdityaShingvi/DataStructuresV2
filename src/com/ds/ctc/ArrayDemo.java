package com.ds.ctc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayDemo {
	
	public static void main(String[] args) {
		ArrayDemo demo = new ArrayDemo();
		String input = "adityaaa";
		char[] inputArr = input.toCharArray();
//		System.out.println(demo.hasUnique(inputArr));
//		System.out.println(demo.hasUnique2(input));
		//System.out.println(demo.reverse2(input));
		//demo.recursiveReverse(inputArr, 0, new StringBuilder());
		StringBuilder b = new StringBuilder(input);
	//	b.reverse();
		System.out.println(demo.isPermutation(input, "aditya"));
		int[][] matrix = new int[][]{{0,1,4},{2,3,5}};
		demo.rotateBy90(matrix, 3);
	}

	//solution 1: to check if array has all unique values (or duplicates)
	public boolean hasUnique(char[] inputArray) {
		Arrays.sort(inputArray);
		for(int i=0,j=1; i < inputArray.length-1 && j < inputArray.length ; i++, j++) {
			if (inputArray[i] == inputArray[j]) {
				return false;
			}
		}
		return true;
	}
	//solution 2 - CTC
	public boolean hasUnique2(String input) {
		//for ASCII string if length if > 256 then return false
		if (input.length() > 256) {
			return false;
		}
		boolean[] charFlag = new boolean[256];
		for(int i=0; i < input.length(); i++) {
			int ascii = input.charAt(i);
			if(charFlag[ascii]) {
				return false;
			}
			charFlag[ascii] = true;
		}
		return true;
	}
	
	//Reverse: solution 1
	public String reverse(String input) {
		if(input.isEmpty()) {
			return null;
		}
		if (input.length() == 1) {
			return input;
		}
		StringBuilder result = new StringBuilder();
		for(int j=input.length()-1; j >= 0; j--) {
			result.append(input.charAt(j));
		}
		return result.toString();
	}
	//solution2: recursion
	public void recursiveReverse(char[] arr, int i, StringBuilder sb) {
		if (i > arr.length-1){
			return;
		}
		char ch = arr[i++];
		recursiveReverse(arr, i, sb);
		sb.append(ch);
		if(sb.length() == arr.length) {
			System.out.print(sb.toString());
		}
	}
	//solution3: correct recursion
	public String reverse2(String str) {
	    if ((null == str) || (str.length() <= 1)) {
	        return str;
	    }
	    return reverse2(str.substring(1)) + str.charAt(0);
	}
	
	//todo- solution1: Sort both arrays and compare
	//solution 2 
	public boolean isPermutation(String src, String test) {
		int[] arr = new int[256];
		int index;
		if (src.isEmpty()) {
			return false;
		}
		for(int i=0; i < src.length(); i++) {
			index = src.charAt(i);
			arr[index] = arr[index]+1;
		}
		for(int i=0; i < test.length(); i++) {
			index = test.charAt(i);
			if (arr[index]-- < 0) {
				return false;
			}
		}
		return true;
	}
	
	//todo
	//To replace each blank spapce in a string with the string "%20"
	public String expandString(String input) {
		int noOfSpace;
		return null;
	}

	//rotate n*n matrix by 90 degrees
	public void rotateBy90(int[][] matrix, int n) {
		for (int layer = 0; layer < n/2; ++layer) {
			int first = layer;
			int last = n-1-layer;
			for (int i = first; i<last; ++i) {
				int offset = i - first;

				//save top
				int top = matrix[first][i];
				
				//left -> top
				matrix[first][i] = matrix[last-offset][first];
				
				//bottom -> left
				matrix[last-offset][first] = matrix[last][last-offset];
				
				//right -> bottom
				matrix[last][last-offset] = matrix[i][last];
				
				// top -> right
				matrix[i][last] = top;
			}
		}
		for (int i = 0; i< n; i++) {
			for (int j = 0 ; j < n; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}
}