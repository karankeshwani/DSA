package com.sunbeam;

import java.util.Arrays;

public class SortingMain {
	// time: O(n log n)
	public static void mergeSort(int []arr, int left, int right) {
		// if array contains single elem, do nothing
		if(left==right)
			return;
		// divide array into two equal parts
		int mid = (left + right) / 2;
		// sort left and right partitions individually
		mergeSort(arr, left, mid);
		mergeSort(arr, mid+1, right);
		// allocate temp array that can accomodate left+right partition
		int[] temp = new int[right - left + 1];
		// merge both sorted partitions into temp array
		int i=left, j=mid+1, k=0;
		// compare elem from both parts and copy smaller into temp array -- do until any one partition is completed
		while(i <= mid && j <= right)	
			temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
		// copy remaining elems from the other/remaining partition
		while(i <= mid)
			temp[k++] = arr[i++];
		while(j <= right)
			temp[k++] = arr[j++];
		// copy sorted temp array back to original array
		for(i=0; i<temp.length; i++)
			arr[left+i] = temp[i];
	}
	
	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static void quickSort(int[] arr, int left, int right) {
		// if partition has single elem or invalid part then do nothing
		if(left >= right)
			return;
		// consider left-most element as pivot i.e. arr[left]
		int i = left, j = right;
		while(i < j) {
			// from left find elem greater than pivot
			while(i <= right && arr[i] <= arr[left])
				i++;
			// from right find elem less than or equal to pivot
			while(arr[j] > arr[left])
				j--;
			// if i < j, then swap arr[i] with arr[j]
			if(i < j)
				swap(arr, i, j);
		} // repeat until i & j are not crossed
		// swap pivot elem with arr[j]
		swap(arr, left, j);
		// apply quick sort to the left and right partition
		quickSort(arr, left, j-1);
		quickSort(arr, j+1, right);
	}
	
	public static void main(String[] args) {
		int[] arr = {4, 7, 9, 2, 8, 1, 6, 3, 5};
		System.out.println("Before Sort: " + Arrays.toString(arr));
		//mergeSort(arr, 0, arr.length-1);
		quickSort(arr, 0, arr.length-1);
		System.out.println(" After Sort: " + Arrays.toString(arr));
	}

}
