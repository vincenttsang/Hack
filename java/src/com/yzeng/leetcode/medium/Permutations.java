package com.yzeng.leetcode.medium;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Permutations {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> input = new ArrayList<Integer>();
		int setSize = Integer.valueOf(scanner.nextLine());
		while (setSize != 0) {
			input.add(scanner.nextInt());
			setSize --;
		}
		int [] nums = new int [input.size()];
		for (int i = 0; i < input.size(); i++) {
			nums[i] = input.get(i);
		}
		List<List<Integer>> permuted = permuteUnique(nums);
		printPermutations(permuted);
	}
	
	// unique permutation with duplicates in original array
	// idea: sort the array and only use an element when its previous occurence is used to avoid duplicates in output
	public static List<List<Integer>> permuteUnique(int[] nums) {
    	Arrays.sort(nums);
		boolean used[] = new boolean[nums.length];
		Arrays.fill(used, false);
		List<List<Integer>> list = new ArrayList<>();
		List<Integer> innerList = new ArrayList<Integer>();
		recurPermute(list, innerList, nums, used);
		return list;
    }
	
	public static void recurPermute(List<List<Integer>> list, List<Integer> innerList, int[] nums, boolean used[]){
		if (innerList.size() == nums.length) {
			list.add(new ArrayList<>(innerList));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
    		if (!used[i]) {
    			if (i == 0);
    			else if (nums[i - 1] == nums[i]) {
					if (!used[i - 1])
						continue;
				}
    			innerList.add(nums[i]);
    			used[i] = true;
    			recurPermute(list, innerList, nums, used);
    			innerList.remove(innerList.size() - 1);
    			used[i] = false;
			}
		}
	}
	
	// yukun: high space complexity, runs much faster than high vote one, permutation I
    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer> innerList = new ArrayList<Integer>();
    	if (nums.length == 1) {
    		
    		innerList.add(nums[0]);
    		list.add(innerList);
    		return list;
		}
        for (int i = 0; i < nums.length; i++) {
        	List<List<Integer>> childList = permute(remove(nums, nums[i]));
        	for (int j = 0; j < childList.size(); j++) {
        		childList.get(j).add(nums[i]);
				list.add(childList.get(j));
			}
		}
        return list;
    }
    
    public static int [] remove(int[] nums, int num){
    	int[] newNum = new int[nums.length-1];
    	for (int i = 0, j = 0; i < nums.length; i++) {
			if (nums[i] != num) {
				newNum[j] = nums[i];
				j++;
			}
		}
    	return newNum;
    }
    // yukun: ends here
    
    // high vote one for permutations I
//    public List<List<Integer>> permute(int[] nums) {
//    	List<List<Integer>> list = new ArrayList<>();
//    	List<Integer> innerList = new ArrayList<Integer>();
//    	recurPermute(nums, list, innerList);
//    	return list;
//    }
//    
//    public void recurPermute(int[] nums, List<List<Integer>> list, List<Integer> innerList){
//        if(innerList.size() == nums.length){
//            list.add(new ArrayList(innerList));
//            return;
//        }
//        for(int i = 0; i < nums.length; i++){
//            if(innerList.contains(nums[i]))
//                continue;
//            else{
//                innerList.add(nums[i]);
//                recurPermute(nums, list, innerList);
//                innerList.remove(innerList.size() - 1);
//            }
//        }
//    }
    
    
    
    public static void printPermutations(List<List<Integer>> list){
    	for (int i = 0; i < list.size(); i++) {
			List<Integer> tmp = list.get(i);
			System.out.print("[ ");
			for (int j = 0; j < tmp.size() - 1; j++) {
				System.out.print(tmp.get(j) + ", ");
			}
			System.out.println(tmp.get(tmp.size() - 1) + " ]");
		}
    }
}
