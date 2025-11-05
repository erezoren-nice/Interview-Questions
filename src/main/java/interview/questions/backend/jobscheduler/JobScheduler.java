package interview.questions.backend.jobscheduler;

import java.util.PriorityQueue;

public class JobScheduler {

  PriorityQueue<Job> jobs = new PriorityQueue<>();

  public void schedule(Job job) {
    jobs.add(job);
  }

  public void run() {
    while (!jobs.isEmpty()) {
      long now = System.currentTimeMillis();
      Job nextJob = jobs.peek();
      if (nextJob.delayTime <= now) {
        jobs.poll().action.run();
      } else {
        try {
          Thread.sleep(Math.min(nextJob.delayTime - now, 1));
        } catch (InterruptedException e) {
          System.out.println("Job interrupted");
          Thread.interrupted();
        }
      }
    }
  }

  public static void main(String[] args) {
    JobScheduler jobScheduler = new JobScheduler();

    jobScheduler.schedule(new Job(1000, () -> System.out.println("First Job")));

    jobScheduler.schedule(new Job(1500, () -> System.out.println("Third Job")));

    jobScheduler.schedule(new Job(2000, () -> System.out.println("Second Job")));

    jobScheduler.run();
  }
}