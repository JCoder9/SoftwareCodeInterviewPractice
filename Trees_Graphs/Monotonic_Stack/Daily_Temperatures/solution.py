"""Monotonic Stack - Daily Temperatures (LC 739)
Find days until warmer temperature for each day.
Monotonic decreasing stack. Found warmer day for previous cooler days.
Time: O(n), Space: O(n)"""

def daily_temperatures(temperatures):
    n = len(temperatures)
    result = [0] * n  # Default: 0 days (no warmer day)
    stack = []  # Monotonic decreasing stack
    
    for i in range(n):
        # Found a warmer day for previous cooler days
        while stack and temperatures[i] > temperatures[stack[-1]]:
            prev_day = stack.pop()
            result[prev_day] = i - prev_day
        
        stack.append(i)
    
    return result

if __name__ == "__main__":
    temps = [73, 74, 75, 71, 69, 72, 76, 73]
    print(daily_temperatures(temps))  # [1, 1, 4, 2, 1, 1, 0, 0]
