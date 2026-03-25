export function maxCoins(nums: number[]): number {
    const n = nums.length;
    const balloons: number[] = [1, ...nums, 1];
    const dp: number[][] = Array(n + 2).fill(null).map(() => Array(n + 2).fill(0));
    for (let length = 2; length < n + 2; length++) {
        for (let left = 0; left < n + 2 - length; left++) {
            const right = left + length;
            for (let k = left + 1; k < right; k++) {
                const coins = balloons[left] * balloons[k] * balloons[right];
                const total = coins + dp[left][k] + dp[k][right];
                dp[left][right] = Math.max(dp[left][right], total);
            }
        }
    }
    return dp[0][n + 1];
}
