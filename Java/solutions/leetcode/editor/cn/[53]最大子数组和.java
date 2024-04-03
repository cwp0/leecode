// 
// @Question: [53]最大子数组和 
// @Author: cwp0
// @CreatedTime: 2024-04-03 12:06:02
// @Description: 
//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
//
// Related Topics 数组 分治 动态规划 👍 6612 👎 0

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {

        // 定义dp[i]数组为以nums[i]结尾的最大子数组和
        int[] dp = new int[nums.length];
        // base case
        dp[0] = nums[0];

        // 状态转移函数
        for (int i = 1; i < nums.length; i++) {
            // dp[i]可以与前面的子数组连接，形成一个值更大的子数组，或者自己作为一个数组，从头开始。
            dp[i] = Math.max(nums[i] + dp[i-1], nums[i]);
        }


        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
