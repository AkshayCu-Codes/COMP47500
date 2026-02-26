# Assignment 1 - JVM Lite: Stack Based Expression Evaluator

A simple virtual machine that mimics how the JVM uses a stack to evaluate mathematical expressions.

---

## How it works

1. User enters an infix expression e.g. `10 + 5 * 3`
2. JVM compiles it into stack instructions (PUSH, ADD, SUB, MUL, DIV, MOD)
3. Instructions are executed on the stack
4. Result is printed

---

## Classes

| Class | Description |
|-------|-------------|
| `Stack.java` | Custom stack implementation using ArrayList |
| `JVM.java` | Compiles and executes expressions using the stack |
| `Main.java` | User interface - enter expressions and see results |
| `Test.java` | Stress tests the JVM and Stack to check for time complexity | 

---

## Supported Operators

| Operator | Description |
|----------|-------------|
| `+` | Addition |
| `-` | Subtraction |
| `*` | Multiplication |
| `/` | Division |
| `%` | Modulus |

---

## Example

```
>> 10 + 5 * 3

Compiled Instructions:
  PUSH 10
  PUSH 5
  PUSH 3
  MUL
  ADD
  PRINT

Executing...
PUSH 10 -> [10]
PUSH 5  -> [10, 5]
PUSH 3  -> [10, 5, 3]
MUL     -> [10, 15]
ADD     -> [25]
Output: 25
```

---

## How to run

1. Open Eclipse
2. Import the project
3. Run `Main.java` for the expression evaluator
4. Run `Test.java` to stress test the JVM

---

## Contributors

| Name | Student ID |
|------|------------|
| Akshay Channapla Udaya Kumar | 25211966 |
| Sharan Srinivasan Sathyan | 25201187 |
