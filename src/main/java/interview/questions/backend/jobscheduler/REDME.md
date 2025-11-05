# Delayed Job Scheduler

## Task

Implement a job scheduler that can schedule tasks to run after a specified delay and execute them in the correct order based on their scheduled execution time. The scheduler should process jobs from a queue and handle timing automatically.

## Requirements

- **Job scheduling** - Accept jobs with a delay time (in milliseconds) and a runnable action to execute
- **Priority-based execution** - Execute jobs in order of their scheduled execution time, not the order they were added
- **Delayed execution** - Wait until each job's scheduled time arrives before executing it
- **Efficient waiting** - Use sleep mechanisms to avoid busy-waiting and CPU spinning
- **Queue management** - Process all scheduled jobs until the queue is empty
- **Job abstraction** - Create a Job class that stores the action and calculates the absolute execution time
- **Comparable implementation** - Jobs should be comparable based on their execution time for priority queue ordering

## Implementation Details

### Job Class
- Should contain the action to execute (Runnable)
- Should store the scheduled execution time (absolute timestamp)
- Should implement `Comparable<Job>` interface for ordering

### Scheduler
- Use a priority queue to maintain jobs ordered by execution time
- Calculate wait time until the next job should execute
- Process jobs sequentially based on their scheduled time

## Example Usage

```java
JobScheduler scheduler = new JobScheduler();

// Schedule jobs with different delays
scheduler.schedule(() -> System.out.println("Job 1"), 3000);  // 3 seconds
scheduler.schedule(() -> System.out.println("Job 2"), 1000);  // 1 second
scheduler.schedule(() -> System.out.println("Job 3"), 2000);  // 2 seconds

// Jobs will execute in order: Job 2 (1s), Job 3 (2s), Job 1 (3s)
scheduler.run();
```

## Level

**Mid- to Senior-level Backend Engineer**
