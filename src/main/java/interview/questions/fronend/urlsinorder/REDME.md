# Ordered Asynchronous URL Response Printer

## Task

Implement a JavaScript solution that fetches multiple URLs concurrently but prints their responses in the original order, regardless of which requests complete first. The system should handle asynchronous operations while maintaining sequential output order.

## Requirements

- **Concurrent execution** - Invoke all URLs simultaneously without waiting for previous requests to complete
- **Ordered output** - Print responses in the original URL order, even if later URLs complete before earlier ones
- **Buffering mechanism** - Store completed responses until they can be printed in order
- **Continuous polling** - Check for the next response to print at regular intervals
- **Completion detection** - Stop processing once all responses have been printed
- **Closure pattern** - Use closures to maintain state across asynchronous callbacks
- **Simulated async operations** - Mock HTTP calls with random delays to simulate real network behavior

## Expected Behavior

Given a list of URLs that complete in random order, the output should always maintain the original sequence:

```javascript
const urls = ["http://1", "http://2", "http://3", "http://4", "http://5"];

// Even if URL 3 completes first, output should be in order:
// Invoked http://1 - ...
// Invoked http://2 - ...
// Invoked http://3 - ...
// Invoked http://4 - ...
// Invoked http://5 - ...
```

## Implementation Considerations

- Use a buffer/array to store responses indexed by their original position
- Maintain a pointer to track which response should be printed next
- Only print a response when all previous responses have been printed
- Continue checking the buffer until all responses are processed

## Level

**Mid- to Senior-level Frontend Engineer**
