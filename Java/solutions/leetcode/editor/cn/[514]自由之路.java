// 
// @Question: [514]自由之路 
// @Author: cwp0
// @CreatedTime: 2024-04-09 11:27:57
// @Description: 
//电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。 
//
// 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。 
//
// 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按
//钮，以此逐个拼写完 key 中的所有字符。 
//
// 旋转 ring 拼出 key 字符 key[i] 的阶段中： 
//
// 
// 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于
//字符 key[i] 。 
// 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段
//）, 直至完成所有拼写。 
// 
//
// 
//
// 示例 1： 
//
// 
//
//
// 
//
//
// 
//输入: ring = "godding", key = "gd"
//输出: 4
//解释:
// 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
// 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
// 当然, 我们还需要1步进行拼写。
// 因此最终的输出是 4。
// 
//
// 示例 2: 
//
// 
//输入: ring = "godding", key = "godding"
//输出: 13
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ring.length, key.length <= 100 
// ring 和 key 只包含小写英文字母 
// 保证 字符串 key 一定可以由字符串 ring 旋转拼出 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 字符串 动态规划 👍 319 👎 0

import java.util.*;

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录ring字符串中每个字符出现的index
    private HashMap<Character, List<Integer>> map = new HashMap<>();
    //备忘录
    private int[][] memo;
    public int findRotateSteps(String ring, String key) {
        int m = ring.length(), n = key.length();

        // 首先创建备忘录
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // 将ring的所有字符出现的index记录在HashMap中，可以更快的查询
        for (int i = 0; i < m; i++) {
            char c = ring.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new LinkedList<>());
            }
            map.get(c).add(i);
        }

        return dp(ring, 0, key, 0);
    }

    // 定义dp[i][j]为圆盘指针在ring[i]，输入key[j..]所需要的最少次数。
    private int dp(String ring, int i, String key, int j) {
        int m = ring.length(), n = key.length();
        // base case
        // 表明key已经全部被输入
        if (j == n) return 0;
        // 查找备忘录，避免重复计算
        if (memo[i][j] != -1) return memo[i][j];

        int res = Integer.MAX_VALUE;
        for (int k : map.get(key.charAt(j))) {
            // 从i转动到k所需要指针的拨动次数
            int num = Math.abs(i-k);
            // 顺时针转 和 逆时针转 取最小值
            num = Math.min(num, m - num);
            // 将ring指针拨到k处，继续输入key[j+1..]
            int subProblem = dp(ring, k, key, j + 1);

            // 1 代表拨动指针后 按下中间的button
            // num 代表将ring指针从i拨到k所需要的转动次数
            res = Math.min(res, 1 + num + subProblem);
        }
        memo[i][j] = res;
        return memo[i][j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
