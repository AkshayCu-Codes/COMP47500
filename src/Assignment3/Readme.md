# Assignment 3 — Priority-Based Airport Runway Scheduler using MinHeap

## Overview
This assignment implements a MinHeap-based Airport Runway Scheduler as part of COMP47500 Advanced Data Structures in Java. The scheduler manages a queue of incoming flights and always dispatches the most critical flight first based on fuel level, emergency status and flight type.

---

## Problem Statement
At a busy airport, multiple flights are circling and waiting to land. Not all of them can wait equally — a flight with 7% fuel cannot wait behind a flight with 62% fuel just because it arrived first. A MinHeap solves this by always keeping the most urgent flight at the top of the queue, enabling O(log n) dispatch instead of O(n) linear scan.

---

## Priority Scale

| Priority | Condition |
|---|---|
| 1 | Emergency declared and fuel below 10% |
| 2 | Emergency declared |
| 3 | Fuel below 10% only |
| 4 | Fuel below 30% |
| 5 | Long haul flight |
| 6 | Normal flight |

If two flights share the same priority, the one with lower fuel lands first.

---

## Project Structure
```
Assignment3/
├── flight.java          ← flight data object and priority calculator
├── minHeap.java         ← core MinHeap data structure
├── airportRunway.java   ← main runner, submits and dispatches flights
└── runwayTest.java      ← benchmarks MinHeap vs brute force array scan
```

---

## How to Run

1. Open Eclipse
2. Import the `Assignment3` package
3. Run `airportRunway.java` for the main demo
4. Run `runwayTest.java` for the benchmark comparison

---

## Sample Output
```
========================================
  DUBLIN AIRPORT - LANDING QUEUE       
========================================

Slot 1: CLEARED TO LAND
  Flight   : EI404  - New York to Dublin
  Priority : 1
  Fuel     : 8% remaining
  Queued   : 14:05
  Landed   : 14:11

Slot 2: CLEARED TO LAND
  Flight   : LH324  - Frankfurt to Dublin
  Priority : 2
  Fuel     : 25% remaining
  Queued   : 14:12
  Landed   : 14:21
```

---

## Benchmark Results

Real world airport scales used (flights per hour):

| Airport | Flights/hour | MinHeap Dispatch (ns) | Array Dispatch (ns) |
|---|---|---|---|
| Dublin | 30 | 17,500 | 10,200 |
| London Heathrow | 90 | 24,100 | 61,100 |
| Atlanta | 180 | 36,400 | 204,100 |
| Dubai | 200 | 44,000 | 284,500 |

The crossover point falls between 30 and 90 flights per hour. Any airport busier than Dublin benefits significantly from the MinHeap approach.

---

## Time Complexity

| Operation | Complexity |
|---|---|
| insert() | O(log n) |
| removeMin() | O(log n) |
| min() | O(1) |
| isEmpty() | O(1) |

---
## Authors
- Akshay — 25211966
- Sharan — 25201187
