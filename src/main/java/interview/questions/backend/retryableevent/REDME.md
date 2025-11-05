# Retryable Event Processor

## Task

Implement a simple mechanism that receives a list of events and executes each one with a defined backoff retry logic.

## Requirements

- **Asynchronous execution** - Each event should be run asynchronously
- **Retry logic** - Retries should occur only for failed executions
- **Backoff intervals** - Use the following backoff intervals in seconds: `2, 2, 4, 8, 16`
- **Failure handling** - If all retries fail, throw an error
- **Continued processing** - The processor should continue handling remaining events regardless of individual failures

## Retry Strategy

The retry mechanism should follow an exponential backoff pattern:
1. First retry: 2 seconds
2. Second retry: 2 seconds
3. Third retry: 4 seconds
4. Fourth retry: 8 seconds
5. Fifth retry: 16 seconds

After all 5 retries are exhausted without success, the event should be marked as failed and an error should be thrown.

## Level

**Mid- to Senior-level Backend Engineer**
