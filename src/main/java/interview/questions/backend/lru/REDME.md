# LRU Cache Implementation

## Task

Implement a LRU (Least Recently Used) cache.

## Description

LRU cache is a data structure that stores recently used items in a fast way. The cache has a fixed capacity, and when the capacity is reached, the least recently used item is evicted to make room for new items.

### How It Works

For example, if you have a cache with a size of 3:

1. You store items A, B, and C (cache is full)
2. You try to store item D
3. The cache will remove the least recently used item (the one that hasn't been accessed for the longest time)
4. Then store the new item D

## Requirements

- Implement a cache with a fixed capacity
- Support `get(key)` operation to retrieve values
- Support `put(key, value)` operation to store values
- When capacity is exceeded, evict the least recently used item
- Both operations should run in O(1) time complexity

## Level

**Mid- to Senior-level Backend Engineer**
