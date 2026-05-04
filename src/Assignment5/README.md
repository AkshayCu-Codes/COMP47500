# Emergency Route Optimization using Graph Algorithms

## Overview
This project investigates the use of graph algorithms for efficient route planning in emergency response systems such as ambulance and fire services.

The road network is modeled as a graph:
- Nodes represent intersections or locations  
- Edges represent roads  
- Weights represent travel cost (distance with variation simulating real-world conditions)

The objective is to evaluate and compare different algorithms in terms of optimality and efficiency.

---

## Algorithms Implemented

### Breadth-First Search (BFS)
- Computes shortest path in terms of number of edges  
- Does not consider edge weights  
- Not suitable for weighted routing problems  

### Dijkstra’s Algorithm
- Computes optimal shortest path for weighted graphs  
- Guarantees minimum cost  
- Explores more nodes to ensure correctness  

### A* Search Algorithm
- Uses heuristic (Euclidean distance) to guide search  
- Produces optimal path when heuristic is admissible  
- Reduces search space compared to Dijkstra  

---

## System Design

### Graph Structure
- Nodes contain coordinates (x, y)  
- Edges are weighted using distance and variation to simulate realistic travel conditions  

### Features
- Connected graph generation  
- Road-like network structure  
- Benchmarking using multiple runs  
- Tracking of visited nodes  

---

## Example Output

### Graph Representation

```
Node-1 -> Node-2(4) Node-3(2)
Node-2 -> Node-1(4) Node-5(6)
Node-3 -> Node-1(2) Node-4(2)
Node-4 -> Node-3(2) Node-5(2)
Node-5 -> Node-2(6) Node-4(2)
```

### Algorithm Comparison

BFS
- Path: [Node-1, Node-2, Node-5]  
- Cost: 10  
- Visited Nodes: 4  

Dijkstra
- Path: [Node-1, Node-3, Node-4, Node-5]  
- Cost: 6  
- Visited Nodes: 5  

A*
- Path: [Node-1, Node-3, Node-4, Node-5]  
- Cost: 6  
- Visited Nodes: 4  

---

## Benchmark Methodology

Each algorithm is executed using:
- 3 warm-up runs  
- 5 measured runs (averaged)

### Metrics
- Execution time (milliseconds)  
- Path cost  
- Number of visited nodes  
---

## Analysis

BFS
- Fast execution  
- Produces suboptimal paths in weighted graphs  

Dijkstra
- Guarantees optimal path  
- Explores more nodes  

A*
- Produces optimal path equivalent to Dijkstra  
- Reduces node exploration  

---

## Conclusion

A* provides a balance between optimality and efficiency.  
Dijkstra guarantees correctness but is more computationally expensive.  
BFS is not suitable for weighted routing problems.

---

## Contributors

Akshay Channapla Udaya Kumar (25211966)  
Sharan Srinivasan Sathyan (25201187)

