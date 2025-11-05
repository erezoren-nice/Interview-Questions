package interview.questions.backend.retryableevent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RetryableTask {

  private int[] delays = new int[] {2, 2, 4, 8, 16};
  private int retryCount = 0;
  private Event event;

  public RetryableTask(Event event) {
    this.event = event;
  }

  public void runWithRetry(ScheduledExecutorService scheduler) {
    try {
      event.getAction().run();
      System.out.println(String.format("Success in running event %d", event.id));
    } catch (Exception ex) {
      if (retryCount < delays.length) {
        scheduler.schedule(() -> runWithRetry(scheduler), delays[retryCount++], TimeUnit.MINUTES);
      } else {
        System.out.println(String.format("Event %d failed after %d attempts", event.getId(), delays.length));
      }
    }
  }
}
