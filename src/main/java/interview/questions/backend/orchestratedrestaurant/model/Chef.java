package interview.questions.backend.orchestratedrestaurant.model;

import interview.questions.backend.orchestratedrestaurant.Restaurant;
import interview.questions.backend.orchestratedrestaurant.OrchestrationManager.Entity;
import lombok.Data;

@Data
public class Chef implements Runnable {

  final int id;
  final Restaurant restaurant;

  @Override
  public void run() {

    while (!Thread.currentThread().isInterrupted()) {
      try {
        Order order = restaurant.takeOrder(Entity.WAITER, Entity.CHEF, null);
        if (order != null) {
          String menuItemDesc = order.getMenuItem().getDescription();
          System.out.println(String.format("Chef %d takes order %s", id, menuItemDesc));
          Thread.sleep(order.getPreparationTime());
          System.out.println(String.format("Order %s is ready!", menuItemDesc));
          restaurant.placeOrder(order, Entity.CHEF, Entity.WAITER);
        }
      } catch (InterruptedException exception) {
        exception.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }
}
