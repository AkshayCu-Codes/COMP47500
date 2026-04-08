# Assignment 2 – BST-Based Store Inventory System

## Overview

This project implements a **store inventory system using a Binary Search Tree (BST)** in Java.
Each item is stored using **price as the key**, making it easy to:

* search by exact price
* list items in sorted order
* find items within a price range
* get the cheapest and most expensive item

The project also includes a **benchmark test** comparing the BST approach with an `ArrayList`.

---

## Files in the Project

| File                  | Purpose                                                |
| --------------------- | ------------------------------------------------------ |
| `BST.java`            | Generic Binary Search Tree implementation              |
| `Item.java`           | Represents a store item with name, price, and category |
| `StoreInventory.java` | Wrapper class that uses the BST for store operations   |
| `StoreRunner.java`    | Runs sample tests on the inventory system              |
| `StoreTester.java`    | Benchmarks BST vs `ArrayList`                          |

---

## Main Idea

The BST stores:

| Part      | Value in this project |
| --------- | --------------------- |
| **Key**   | `price` (`Double`)    |
| **Value** | `Item`                |

This means all items are automatically organised by **price**.

---

## Main Operations

| Operation         | Description                            |
| ----------------- | -------------------------------------- |
| `addItem()`       | Inserts a new item into the BST        |
| `findItem()`      | Searches for an item by exact price    |
| `removeItem()`    | Deletes an item by price               |
| `findInRange()`   | Returns all items within a price range |
| `listAll()`       | Returns all items sorted by price      |
| `cheapest()`      | Returns the lowest-priced item         |
| `mostExpensive()` | Returns the highest-priced item        |
| `printTree()`     | Prints the BST structure               |

---

## What Each File Does

### `BST.java`

This is the core data structure.

| Method                | Purpose                                 |
| --------------------- | --------------------------------------- |
| `insert(key, value)`  | Adds a node to the tree                 |
| `search(key)`         | Finds a value using the key             |
| `delete(key)`         | Removes a node from the tree            |
| `rangeSearch(lo, hi)` | Finds values between two keys           |
| `inOrder()`           | Returns values in ascending key order   |
| `min()`               | Returns the value with the smallest key |
| `max()`               | Returns the value with the largest key  |
| `printTree()`         | Displays the BST visually               |

### `Item.java`

Stores product details.

| Field      | Meaning           |
| ---------- | ----------------- |
| `name`     | Name of the item  |
| `price`    | Price of the item |
| `category` | Item category     |

### `StoreInventory.java`

Connects store logic with the BST and provides inventory-specific operations.

### `StoreRunner.java`

Tests the inventory system with sample products.
It demonstrates:

* insertion
* exact search
* range search
* sorted listing
* BST printing

### `StoreTester.java`

Compares BST and `ArrayList` performance.

For each dataset size, it:

1. generates random items with unique prices
2. measures insertion time
3. measures range search time
4. prints the results in a table

---

## Time Complexity

| Operation      | BST                              | ArrayList                         |
| -------------- | -------------------------------- | --------------------------------- |
| Insert         | `O(log n)` average, `O(n)` worst | `O(1)` amortized append           |
| Exact search   | `O(log n)` average, `O(n)` worst | `O(n)`                            |
| Range search   | `O(log n + k)` average           | `O(n)`                            |
| Sorted listing | `O(n)` using in-order traversal  | `O(n log n)` if sorting is needed |
| Min / Max      | `O(log n)` average               | `O(n)`                            |

`k` = number of matching results.

---

## Benchmark Summary

| Observation                                      | Interpretation                                   |
| ------------------------------------------------ | ------------------------------------------------ |
| `ArrayList` insertion is faster                  | Appending has lower overhead                     |
| BST insertion is slower                          | Each item must be placed in the correct position |
| BST range search is often faster                 | It can skip branches outside the range           |
| `ArrayList` range search scans all items         | Every item must be checked                       |
| At very large sizes, BST may lose some advantage | A wide search range reduces pruning              |

---

## Example Benchmark Output

```text
+==========================================================+
|         BST  vs  ArrayList   —  Benchmark                |
|        Range search window: € 250 -  € 750               |
|  Each timing = average of 5 runs  (after 3 warm-up runs) |
+==========================================================+

Size          BST Insert     AL Insert        BST Range      AL Range      
──────────────────────────────────────────────────────────────────────────────────────────
10            6.90 µs        1.33 µs          3.40 µs        3.27 µs       
100           159.53 µs      8.67 µs          45.30 µs       55.17 µs      
1K            357.80 µs      62.93 µs         38.73 µs       124.10 µs     
10K           3.14 ms        598.87 µs        281.63 µs      1.06 ms       
100K          71.03 ms       3.40 ms          4.59 ms        21.35 ms      
1M            1.387 s        11.64 ms         62.26 ms       18.00 ms      
10M           23.990 s       145.96 ms        373.12 ms      198.19 ms     
──────────────────────────────────────────────────────────────────────────────────────────
```

---

## Example BST Structure Output

```text
BST internal structure
                 /----- [549.0 | 4K Monitor                    €  549.00  [Electronics]]
         /----- [299.0 | Wireless Headphones           €  299.00  [Electronics]]
         |       |               /----- [199.0 | Smart Watch                   €  199.00  [Electronics]]
         |       |       /----- [129.99 | Mechanical Keyboard           €  129.99  [Electronics]]
         |       \----- [74.95 | Yoga Mat                      €   74.95  [Sports]]
 /----- [49.99 | Running Shoes                 €   49.99  [Sports]]
 |       |               /----- [34.99 | Denim Jeans                   €   34.99  [Clothing]]
 |       |       /----- [19.99 | T-Shirt (M)                   €   19.99  [Clothing]]
 |       \----- [12.5 | Python Cookbook               €   12.50  [Books]]
 |               |       /----- [9.95 | Notebook A5                   €    9.95  [Books]]
 |               \----- [8.99 | Olive Oil 1L                  €    8.99  [Food]]
 |                       \----- [3.49 | Sparkling Water 6pk           €    3.49  [Food]]
```

---

## Theoretical vs Practical

Theoretically, a BST is more efficient for ordered and range-based queries.
In practice, performance also depends on:

| Factor             | Effect                                               |
| ------------------ | ---------------------------------------------------- |
| Dataset size       | Larger inputs can change performance patterns        |
| Tree shape         | An unbalanced BST can become slower                  |
| Search range width | Wide ranges reduce BST pruning                       |
| Memory access      | `ArrayList` may benefit from cache-friendly scanning |

---

## How to Run

| Step | Action                                         |
| ---- | ---------------------------------------------- |
| 1    | Compile all files in the `Assignment2` package |
| 2    | Run `StoreRunner` to test functionality        |
| 3    | Run `StoreTester` to execute the benchmark     |

---

## Contributors

| Name                         | Student ID |
| ---------------------------- | ---------- |
| Akshay Channapla Udaya Kumar | 25211966   |
| Sharan Srinivasan Sathyan    | 25201187   |

---

## Conclusion

This project shows how a **BST can be used in a store inventory system** where items are naturally ordered by price.

* Use **BST** when ordered search and range queries matter.
* Use **ArrayList** when simple insertion is the main priority.
