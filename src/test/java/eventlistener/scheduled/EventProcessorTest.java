package eventlistener.scheduled;

import interview.questions.backend.retryableevent.EventProcessor;
import java.util.List;
import org.junit.jupiter.api.Test;

class EventProcessorTest {

  EventProcessor eventProcessor = new EventProcessor();

  @Test
  public void test() {
    eventProcessor.processEvents(List.of());
  }
}