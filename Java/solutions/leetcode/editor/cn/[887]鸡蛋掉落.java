// 
// @Question: [887]鸡蛋掉落 
// @Author: cwp0
// @CreatedTime: 2024-04-14 15:35:25
// @Description: 
//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。 
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。 
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？ 
//
// 示例 1： 
//
// 
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
//如果它没碎，那么肯定能得出 f = 2 。 
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
// 
//
// 示例 2： 
//
// 
//输入：k = 2, n = 6
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：k = 3, n = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 100 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 数学 二分查找 动态规划 👍 991 👎 0

import java.util.Arrays;

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 备忘录
    private int[][] memo;
    public int superEggDrop(int k, int n) {
        memo = new int[k+1][n+1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(k, n);
    }

    // 定义dp[K][N]为给定K枚鸡蛋，共有N层楼，确定f确切的值的最小操作次数
    private int dp(int K, int N) {
        // base case
        // 如果K为1，只能从最底层一层一层的尝试
        if (K == 1) return N;
        // 层数为0，不用尝试
        if (N == 0) return 0;

        if (memo[K][N] != -1) return memo[K][N];

        int res = Integer.MAX_VALUE;
        // 状态转移函数
        // 二分搜索，加快搜索
        int low = 1, high = N;
        while (low <= high) {
            int mid = (low + high) / 2;
            // 中间层分鸡蛋碎了和没碎两种情况
            // 碎了的话就说明f在更底层，此时共有mid-1层楼，这里的-1是为了排除mid层
            int broken = dp(K-1, mid-1);
            // 如果没碎，说明f在更高层，此时共有N-mid层楼，+1同上
            int not_broken = dp(K, N-mid);
            if (broken > not_broken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }
        memo[K][N] = res;
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
