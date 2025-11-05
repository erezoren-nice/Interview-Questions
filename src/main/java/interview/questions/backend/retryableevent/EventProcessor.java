package interview.questions.backend.retryableevent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventProcessor {

  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

  public void processEvents(List<Event> events) {
    try {
      for (Event e : events) {
        RetryableTask task = new RetryableTask(e);
        task.runWithRetry(scheduler);
      }
    } finally {
      shutdown();
    }
  }

  private void shutdown() {
    // Graceful shutdown
    System.out.println("Shutting down executor");
    scheduler.shutdown();
    try {
      if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
        scheduler.shutdownNow();
        if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
          System.err.println("Executor did not terminate");
        }
      }
    } catch (InterruptedException e) {
      scheduler.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) {
    EventProcessor eventProcessor = new EventProcessor();
    List<Event> events = List.of(
        new Event("Event1", () -> System.out.println("Executing Event1")),
        new Event("Event2", () -> System.out.println("Executing Event2")),
        new Event("Event3", () -> System.out.println("Executing Event3")));
    eventProcessor.processEvents(events);
  }
}
