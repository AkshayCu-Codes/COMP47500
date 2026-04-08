# Assignment 2 – BST-Based Store Inventory System

## Overview

This project implements a **store inventory system using a Binary Search Tree (BST)** in Java. The system stores items using **price as the key**, which allows efficient ordered operations such as exact search, sorted listing, minimum/maximum lookup, and **range-based retrieval**.

In addition to the core implementation, the project includes a **benchmark test file** that compares the BST approach with an `ArrayList` for insertion and range search performance across different dataset sizes.

---

## Project Structure

### `BST.java`

A generic Binary Search Tree implementation using:

* `K` as the key type
* `V` as the value type

#### Supported operations

* `insert(key, value)` – inserts a node into the BST
* `search(key)` – searches for a value by exact key
* `delete(key)` – removes a node by key
* `rangeSearch(lo, hi)` – returns all values whose keys fall within a range
* `inOrder()` – returns all values in ascending key order
* `min()` – returns the value with the smallest key
* `max()` – returns the value with the largest key
* `printTree()` – prints the BST structure visually
* `size()` and `isEmpty()` – basic utility methods

---

### `Item.java`

Represents a product in the store.

#### Attributes

* `name` – item name
* `price` – item price
* `category` – item category

The `toString()` method is overridden so that items print in a clean formatted style.

---

### `StoreInventory.java`

This class acts as a wrapper around the BST and provides inventory-specific methods.

#### Supported operations

* `addItem(name, price, category)`
* `removeItem(price)`
* `findItem(price)`
* `findInRange(lo, hi)`
* `listAll()`
* `cheapest()`
* `mostExpensive()`
* `size()`
* `isEmpty()`
* `printTree()`

This keeps the project modular by separating store logic from tree implementation.

---

### `StoreRunner.java`

A simple runner class used to test the store inventory system.

#### Demonstrates

* inserting sample items
* printing all items sorted by price
* searching for an exact item by price
* performing range searches
* printing the internal BST structure

---

### `StoreTester.java`

The benchmark file for performance comparison between:

* **BST-based inventory**
* **ArrayList-based storage**

#### What it does

For each dataset size, the test file:

1. Generates random items with **unique prices**
2. Measures **insertion time** for both BST and `ArrayList`
3. Measures **range search time** for both structures using the interval **€250–€750**
4. Prints the results in a formatted table

Unique prices are necessary because the BST uses **price as the key**, and duplicate keys would overwrite existing values.

---

## Key Features

* Generic BST implementation
* Store inventory built on top of BST
* Exact search by price
* Efficient range search using BST traversal
* Sorted output using in-order traversal
* Cheapest and most expensive item lookup
* Tree visualisation
* Practical benchmarking against `ArrayList`

---

## Time Complexity

### BST Operations

* Insert: **O(log n)** average, **O(n)** worst case
* Search: **O(log n)** average, **O(n)** worst case
* Delete: **O(log n)** average, **O(n)** worst case
* Range Search: **O(log n + k)** average, where `k` is the number of results
* In-order Traversal: **O(n)**
* Min / Max: **O(log n)** average, **O(n)** worst case

### ArrayList Operations in Benchmark

* Insert (append): **O(1)** amortized per item
* Range Search: **O(n)** because every item must be checked

---

## Benchmark Interpretation

The benchmark results show a clear trade-off between the two data structures:

* **ArrayList is faster for insertion** because items are simply appended.
* **BST takes longer for insertion** because it must place each item in the correct ordered position.
* For **range search**, the BST performs better at many small and medium dataset sizes because it can skip branches that are outside the target interval.
* At very large sizes, the advantage of BST may reduce if the search range is broad, since many nodes still need to be visited.

### Theoretical vs Practical View

Theoretically, a BST is more efficient for ordered range queries than a linear scan. However, in practice, performance also depends on factors such as:

* dataset size
* tree shape
* search interval width
* memory access patterns
* implementation overhead

---

## Example Output

The benchmark compares:

* **BST Insert vs ArrayList Insert**
* **BST Range vs ArrayList Range**

This helps show where a BST is beneficial and where a simple list may still perform better.

---

## How to Run

1. Compile all Java files in the `Assignment2` package.
2. Run `StoreRunner` to test functionality.
3. Run `StoreTester` to execute the benchmark.

---

## Contributors

| Name                         | Student ID |
| ---------------------------- | ---------- |
| Akshay Channapla Udaya Kumar | 25211966   |
| Sharan Srinivasan Sathyan    | 25201187   |

---

## Conclusion

This project demonstrates how a Binary Search Tree can be applied to a real-world inventory scenario where items are naturally ordered by price. It also shows that data structure choice depends on the operation being prioritised: `ArrayList` is better for simple bulk insertion, while BST is more suitable for ordered queries and selective range searches.
