/**
 * Topological Sort - Build Order / Project Dependencies
 * 
 * Related LeetCode Problems:
 * - Similar to Course Schedule but with direct dependencies
 * - Build order with multi-level dependencies
 * - Parallel build optimization
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

// Basic build order
export function buildOrder(projects: string[], dependencies: [string, string][]): string[] {
    const graph = new Map<string, string[]>();
    const inDegree = new Map<string, number>();
    
    for (const project of projects) {
        graph.set(project, []);
        inDegree.set(project, 0);
    }
    
    for (const [before, after] of dependencies) {
        graph.get(before)!.push(after);
        inDegree.set(after, inDegree.get(after)! + 1);
    }
    
    const queue: string[] = [];
    for (const project of projects) {
        if (inDegree.get(project) === 0) {
            queue.push(project);
        }
    }
    
    const buildSequence: string[] = [];
    
    while (queue.length > 0) {
        const project = queue.shift()!;
        buildSequence.push(project);
        
        for (const dependent of graph.get(project)!) {
            inDegree.set(dependent, inDegree.get(dependent)! - 1);
            if (inDegree.get(dependent) === 0) {
                queue.push(dependent);
            }
        }
    }
    
    return buildSequence.length === projects.length ? buildSequence : [];
}

// Parallel build order (batches)
export function parallelBuildOrder(projects: string[], dependencies: [string, string][]): string[][] {
    const graph = new Map<string, string[]>();
    const inDegree = new Map<string, number>();
    
    for (const project of projects) {
        graph.set(project, []);
        inDegree.set(project, 0);
    }
    
    for (const [before, after] of dependencies) {
        graph.get(before)!.push(after);
        inDegree.set(after, inDegree.get(after)! + 1);
    }
    
    const batches: string[][] = [];
    let currentBatch: string[] = [];
    
    for (const project of projects) {
        if (inDegree.get(project) === 0) {
            currentBatch.push(project);
        }
    }
    
    while (currentBatch.length > 0) {
        batches.push([...currentBatch]);
        const nextBatch: string[] = [];
        
        for (const project of currentBatch) {
            for (const dependent of graph.get(project)!) {
                inDegree.set(dependent, inDegree.get(dependent)! - 1);
                if (inDegree.get(dependent) === 0) {
                    nextBatch.push(dependent);
                }
            }
        }
        
        currentBatch = nextBatch;
    }
    
    const total = batches.reduce((sum, batch) => sum + batch.length, 0);
    return total === projects.length ? batches : [];
}

// Minimum build time
export function minimumBuildTime(
    projects: string[], 
    dependencies: [string, string][], 
    buildTimes: Map<string, number>
): number {
    const graph = new Map<string, string[]>();
    const inDegree = new Map<string, number>();
    const earliestTime = new Map<string, number>();
    
    for (const project of projects) {
        graph.set(project, []);
        inDegree.set(project, 0);
        earliestTime.set(project, 0);
    }
    
    for (const [before, after] of dependencies) {
        graph.get(before)!.push(after);
        inDegree.set(after, inDegree.get(after)! + 1);
    }
    
    const queue: string[] = [];
    for (const project of projects) {
        if (inDegree.get(project) === 0) {
            queue.push(project);
        }
    }
    
    while (queue.length > 0) {
        const project = queue.shift()!;
        const completionTime = earliestTime.get(project)! + buildTimes.get(project)!;
        
        for (const dependent of graph.get(project)!) {
            earliestTime.set(dependent, 
                           Math.max(earliestTime.get(dependent)!, completionTime));
            inDegree.set(dependent, inDegree.get(dependent)! - 1);
            if (inDegree.get(dependent) === 0) {
                queue.push(dependent);
            }
        }
    }
    
    let maxTime = 0;
    for (const project of projects) {
        maxTime = Math.max(maxTime, earliestTime.get(project)! + buildTimes.get(project)!);
    }
    
    return maxTime;
}

// Test cases
if (require.main === module) {
    const projects = ["a", "b", "c", "d", "e", "f"];
    const deps: [string, string][] = [
        ["a", "d"], ["f", "b"], ["b", "d"], ["f", "a"], ["d", "c"]
    ];
    
    console.log("Build order:", buildOrder(projects, deps));
    
    console.log("\nParallel build batches:");
    const batches = parallelBuildOrder(projects, deps);
    batches.forEach((batch, i) => {
        console.log(`  Batch ${i + 1}:`, batch);
    });
    
    const buildTimes = new Map([
        ["a", 3], ["b", 2], ["c", 1], ["d", 4], ["e", 2], ["f", 1]
    ]);
    
    console.log("\nMinimum build time:", minimumBuildTime(projects, deps, buildTimes));
}
