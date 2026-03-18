# Assignment 2 - Binary Search Tree 

A simple Binary Search Tree (BST) implementation in Java that stores key-value pairs and supports efficient insertion, search, deletion, and range search operations.

## How it works

* Keys and values are inserted into the BST
* The BST organizes nodes based on key order
* Smaller keys go to the left, and larger keys go to the right
* Duplicate keys update the existing value instead of creating a new node
* Range search returns all values whose keys lie within a given range
* `Test.java` benchmarks insertion and range search performance for different input sizes

## Classes

| Class       | Description                                                                                                     |
| ----------- | --------------------------------------------------------------------------------------------------------------- |
| `BST.java`  | Generic Binary Search Tree implementation with insert, search, delete, range search, size, and print operations |
| `Main.java` | Demonstrates BST insertion and prints the tree structure                                                        |
| `Test.java` | Benchmarks BST insertion and range search to study performance and time complexity                              |

## Supported Operations

| Operation             | Description                                             |
| --------------------- | ------------------------------------------------------- |
| `insert(key, value)`  | Inserts a key-value pair into the BST                   |
| `search(key)`         | Searches for a key and returns its value                |
| `delete(key)`         | Removes a node with the given key                       |
| `rangeSearch(lo, hi)` | Returns all values whose keys are between `lo` and `hi` |
| `size()`              | Returns the number of nodes in the BST                  |
| `printTree()`         | Prints the BST structure sideways                       |

## Example

```text
Insert:
85 -> Alice
72 -> Bob
91 -> Charlie
60 -> Diana
78 -> Eve
95 -> Frank
55 -> Grace
88 -> Hank
72 -> Ivan
100 -> Jane

Range Search:
70 to 90

Output:
[Ivan, Eve, Alice, Hank]
```

## Tree Structure

```text
                         /----- [100 | Jane]
                 /----- [95 | Frank]
         /----- [91 | Charlie]
         |       \----- [88 | Hank]
 /----- [85 | Alice]
 |       |       /----- [78 | Eve]
 |       \----- [72 | Ivan]
 |               \----- [60 | Diana]
 |                       \----- [55 | Grace]
```

## Benchmark Output

`Test.java` runs the BST with increasing input sizes and reports:

* Node count
* Actual BST size
* Insertion time
* Average range search time
* Average hits returned

This helps verify that:

* Insertion time increases as the tree size grows
* Range search remains efficient by visiting only relevant nodes
* Duplicate keys reduce the final BST size because values are updated instead of adding new nodes

## How to run

1. Open Eclipse
2. Import the project
3. Run `Main.java` to see BST insertion and tree structure
4. Run `Test.java` to benchmark insertion and range search performance

## Cost Analysis

* Insert: `O(log n)` average per operation
* Search: `O(log n)` average
* Delete: `O(log n)` average
* Range Search: `O(log n + k)` average, where `k` is the number of matching results
* Worst case: if the BST becomes unbalanced, operations can degrade to `O(n)`

## Contributors

| Name                         | Student ID |
| ---------------------------- | ---------- |
| Akshay Channapla Udaya Kumar | 25211966   |
| Sharan Srinivasan Sathyan    | 25201187   |
