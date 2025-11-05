package interview.questions.backend.orchestratedrestaurant.model;

import interview.questions.backend.orchestratedrestaurant.Restaurant;
import interview.questions.backend.orchestratedrestaurant.OrchestrationManager.Entity;
import lombok.Data;

@Data
public class Waiters implements Runnable {

  final int id;
  final Restaurant restaurant;

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Order order = restaurant.takeOrder(Entity.CUSTOMER, Entity.WAITER, null);
        Order fromChef = restaurant.takeOrder(Entity.CHEF, Entity.WAITER, null);

        if (order != null) {
          String menuItemDesc = order.getMenuItem().getDescription();
          System.out.println(String.format("Waiters %d takes order %s", id, menuItemDesc));
          Thread.sleep(1000);
          restaurant.placeOrder(order, Entity.WAITER, Entity.CHEF);
          System.out.println(String.format("Waiters %d places order %s", id, menuItemDesc));
        }

        if (fromChef != null) {
          System.out.println(String.format("Waiters %d got order %s from Chef", id, fromChef.getMenuItem().getDescription()));
          Thread.sleep(3000);
          restaurant.placeOrder(fromChef, Entity.WAITER, Entity.CUSTOMER);
        }
      } catch (InterruptedException exception) {
        exception.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }
}
