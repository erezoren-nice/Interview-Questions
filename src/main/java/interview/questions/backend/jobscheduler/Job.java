package interview.questions.backend.jobscheduler;

public class Job implements Comparable<Job> {

  Runnable action;
  long delayTime;

  Job(long delayMillis, Runnable action) {
    this.delayTime = System.currentTimeMillis() + delayMillis;
    this.action = action;
  }

  @Override
  public int compareTo(Job o) {
    return Long.compare(this.delayTime, o.delayTime);
  }
}
