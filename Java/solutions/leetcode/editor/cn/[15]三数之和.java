// 
// @Question: [15]三数之和 
// @Author: cwp0
// @CreatedTime: 2024-06-29 16:10:20
// @Description: 
//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 6917 👎 0

import java.util.*;

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // 先排序
        List<List<Integer>> res = new ArrayList<>();
        // 先穷举第一个数
        for (int i = 0; i < n; i++) {
            // 对 target - nums[i] 计算 twoSum
            List<List<Integer>> twoSumRes = towSum(nums, i+1, -nums[i]);
            // 如果存在满足条件的二元组，再加上 nums[i] 就是结果三元组
            for (List<Integer> tuple : twoSumRes) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            // 跳过第一个数字重复的情况
            while (i < n-1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }
    // 从 nums[start] 开始，计算有序数组 nums 中所有和为 target 的二元组
    private List<List<Integer>> towSum(int[] nums, int start, int target) {
        int low = start, high = nums.length - 1;
        // Arrays.sort(nums); // 先排序
        List<List<Integer>> res = new ArrayList<>();
        while (low < high) {
            int sum = nums[low] + nums[high];
            int left = nums[low], right = nums[high]; // 用来标记左右元素，对比重复元素
            if (sum < target) {
                while (low < high && nums[low] == left) low++;
            } else if (sum > target) {
                while (low < high && nums[high] == right) high--;
            } else {
                res.add(new ArrayList<>(Arrays.asList(left, right)));
                while (low < high && nums[low] == left) low++;
                while (low < high && nums[high] == right) high--;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
