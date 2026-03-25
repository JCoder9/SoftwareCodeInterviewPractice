export function removeBoxes(boxes: number[]): number {
    const n = boxes.length;
    const memo = new Map<string, number>();
    
    function dp(i: number, j: number, k: number): number {
        if (i > j) return 0;
        const key = `${i},${j},${k}`;
        if (memo.has(key)) return memo.get(key)!;
        
        const origI = i;
        while (i < j && boxes[i] === boxes[i + 1]) {
            i++;
            k++;
        }
        
        let result = (k + 1) ** 2 + dp(i + 1, j, 0);
        
        for (let m = i + 1; m <= j; m++) {
            if (boxes[m] === boxes[i]) {
                result = Math.max(result, 
                    dp(i + 1, m - 1, 0) + dp(m, j, k + 1));
            }
        }
        
        memo.set(`${origI},${j},${k}`, result);
        return result;
    }
    
    return dp(0, n - 1, 0);
}
