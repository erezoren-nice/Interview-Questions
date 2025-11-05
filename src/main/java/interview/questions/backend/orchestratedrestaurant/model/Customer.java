package interview.questions.backend.orchestratedrestaurant.model;

import interview.questions.backend.orchestratedrestaurant.Restaurant;
import interview.questions.backend.orchestratedrestaurant.OrchestrationManager.Entity;
import lombok.Data;

@Data
public class Customer implements Runnable {

  final int id;
  final Restaurant restaurant;

  @Override
  public void run() {
    try {
      Order order = new Order(id * 2, restaurant.getRandomMenuItem(), this, (long) ((Math.random() * 4000) + 1000));
      String menuItemDesc = order.getMenuItem().getDescription();
      System.out.println(String.format("Customer %d orders order %s", id, menuItemDesc));
      Thread.sleep(3000);
      restaurant.placeOrder(order, Entity.CUSTOMER, Entity.WAITER);
      System.out.println(String.format("Customer %d placed order %s", id, menuItemDesc));
      while (!Thread.currentThread().isInterrupted()) {
        Order doneOrder = restaurant.takeOrder(Entity.WAITER, Entity.CUSTOMER, this);
        if (doneOrder != null) {
          System.out.println(String.format("Customer %d got order %s from waiters", id, doneOrder.getMenuItem().getDescription()));
        }
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
      Thread.interrupted();
    }
  }
}
