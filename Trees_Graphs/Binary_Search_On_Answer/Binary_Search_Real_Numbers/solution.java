public class SquareRoot {
    /**
     * Binary Search on Answer - Binary Search on Real Numbers
     * Time: O(log(x / precision)), Space: O(1)
     */
    public double sqrt(double x, double precision) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        
        double left = 0.0;
        double right = Math.max(1.0, x);
        
        while (right - left > precision) {
            double mid = left + (right - left) / 2;
            double square = mid * mid;
            
            if (square < x) {
                left = mid;
            } else if (square > x) {
                right = mid;
            } else {
                return mid;
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
        SquareRoot sr = new SquareRoot();
        System.out.printf("%.6f%n", sr.sqrt(2, 1e-6));   // 1.414214
        System.out.printf("%.6f%n", sr.sqrt(10, 1e-6));  // 3.162278
    }
}
