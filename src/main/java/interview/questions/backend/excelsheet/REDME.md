# Excel Sheet Calculator

## Task

Implement an Excel sheet class that can store cell values and evaluate formulas with cell references.

## Features

- **Setting cell values** - Store numeric values or formulas in cells identified by standard Excel notation (e.g., `A1`, `B2`, `C3`)
- **Getting cell values** - Retrieve the computed value of a cell, evaluating any formulas if necessary
- **Formula evaluation** - Support formulas that start with `=` and can reference other cells and perform addition operations (e.g., `=A1+B2+5`)
- **Dependency resolution** - When a cell references other cells, automatically evaluate those dependencies recursively
- **Cycle detection** - Detect and throw an error if circular dependencies exist (e.g., A1 references B1, which references A1)
- **Memoization** - Cache evaluated results to avoid redundant calculations when the same cell is referenced multiple times

## Requirements

- Cell IDs should be case-insensitive
- Formulas should support multiple additions in a single expression
- Throw appropriate errors for invalid cell references or circular dependencies
- Only addition operator needs to be supported (no subtraction, multiplication, or division required)

## Example Usage

```java
sheet.set("A1", "10");
sheet.set("B2", "20");
sheet.set("C3", "=A1+B2+5");
System.out.println(sheet.get("C3")); // Should output: 35.0
```

## Level

**Mid- to Senior-level Backend Engineer**
