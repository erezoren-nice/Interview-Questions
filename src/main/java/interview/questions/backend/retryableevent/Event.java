package interview.questions.backend.retryableevent;

public class Event {

  String id;
  Runnable action;

  public Event(String id, Runnable action) {
    this.id = id;
    this.action = action;
  }

  public String getId() {
    return id;
  }

  public Runnable getAction() {
    return action;
  }
}
