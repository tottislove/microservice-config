package com.xc;

import java.util.*;
import java.util.stream.Collectors;

public class LeeCodeTest {
    public static void main(String[] args) {
        System.out.println("1111");
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(lists);

        List<List<String>> lists2 = groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(lists2);

        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 4, 5, 3, 2}));


        int[] ints = {100, 4, 200, 1, 0, 0, 4, 5, 3, 2};
        BubbleSort(ints);
        for (int anInt : ints) {
            System.out.print(anInt + "_");
        }

        System.out.println("+++++");
        int[] ints2 = {100, 4, 200, 1, 0, 0, 4, 5, 3, 2};

        moveZeroes(ints2);
        for (int i : ints2) {
            System.out.print(i + ">");
        }

        System.out.println(maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
//        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));


    }


//    public List<List<Integer>> threeSum(int[] nums) {
//        int length = nums.length;
//        Arrays.sort(nums);
//
//    }

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                r--;
            }
        }
        return ans;
    }

    //几种基础排序
//    冒泡
    public static void BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    ;
    //快排 不稳定


    public static void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }


        int longestStreak = 0;
        for (int i : num_set) {
            if (!num_set.contains(i - 1)) {
                int currentNum = i;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charsArray = str.toCharArray();
            Arrays.sort(charsArray);

            String nStrKey = new String(charsArray);
            List<String> list = map.get(nStrKey);
            if (null == list) {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(nStrKey, list);
        }


        return new ArrayList<>(map.values());

        //    return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());

    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charsArray = str.toCharArray();
            Arrays.sort(charsArray);

            map.computeIfAbsent(String.valueOf(charsArray), key -> new ArrayList<>()).add(str);
        }


        return map.values().stream().collect(Collectors.toList());
        //    return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());

    }

    public static List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();


        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());

    }
}
