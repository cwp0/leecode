// 
// @Question: [312]戳气球 
// @Author: cwp0
// @CreatedTime: 2024-04-14 21:15:48
// @Description: 
//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1332 👎 0

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n+2];
        points[0] = 1;
        points[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i-1];
        }
        // base case为 j <= i 时，区间内没有没有区间可以扎破，已经被初始化为0
        // 定义dp[i][j]为扎破开区间(i, j)内的所有区间获得的硬币。
        int[][] dp = new int[n+2][n+2];
        // i 从下往上遍历
        for (int i = n; i >= 0; i--) {
            // j 从左往右遍历
            for (int j = i+1; j <= n+1; j++) {
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i]*points[k]*points[j]);
                }
            }
        }

        return dp[0][n+1];
    }


}
//leetcode submit region end(Prohibit modification and deletion)
