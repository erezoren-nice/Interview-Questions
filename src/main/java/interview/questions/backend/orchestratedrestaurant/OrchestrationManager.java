package interview.questions.backend.orchestratedrestaurant;

import interview.questions.backend.orchestratedrestaurant.model.Customer;
import interview.questions.backend.orchestratedrestaurant.model.Order;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class OrchestrationManager {

  public enum Entity {
    CUSTOMER,
    WAITER,
    CHEF
  }

  final BlockingDeque<Order> customerToWaitresOrders;
  final BlockingDeque<Order> waitresToChefsOrders;
  final BlockingDeque<Order> chefToWaitresOrders;
  Map<Customer, Order> readyToServe;

  public OrchestrationManager() {
    this.customerToWaitresOrders = new LinkedBlockingDeque<>();
    this.waitresToChefsOrders = new LinkedBlockingDeque<>();
    this.chefToWaitresOrders = new LinkedBlockingDeque<>();
    this.readyToServe = new ConcurrentHashMap<>();
  }

  public void placeOrder(Order order, Entity from, Entity to) {
    if (from.equals(Entity.CUSTOMER) && to.equals(Entity.WAITER)) {
      placeWaitresOrder(order);
    }
    if (from.equals(Entity.WAITER) && to.equals(Entity.CHEF)) {
      placeChefOrder(order);
    }
    if (from.equals(Entity.CHEF) && to.equals(Entity.WAITER)) {
      placeChefToWeitresOrder(order);
    }
    if (from.equals(Entity.WAITER) && to.equals(Entity.CUSTOMER)) {
      placeCustomerOrder(order);
    }
  }

  public Order takeOrder(Entity from, Entity to, Customer customer) throws InterruptedException {
    if (from.equals(Entity.CUSTOMER) && to.equals(Entity.WAITER)) {
      return takeCustomerToWaitresOrder();
    }
    if (from.equals(Entity.WAITER) && to.equals(Entity.CHEF)) {
      return takeWaitresToChefOrder();
    }
    if (from.equals(Entity.CHEF) && to.equals(Entity.WAITER)) {
      return takeChefToWaitresOrder();
    }
    if (from.equals(Entity.WAITER) && to.equals(Entity.CUSTOMER)) {
      return takeCustomerOrder(customer);
    }

    return null;
  }

  void placeWaitresOrder(Order order) {
    customerToWaitresOrders.add(order);
  }

  Order takeCustomerToWaitresOrder() throws InterruptedException {
    if (customerToWaitresOrders.isEmpty()) {
      return null;
    }
    return customerToWaitresOrders.takeFirst();
  }

  void placeChefOrder(Order order) {
    waitresToChefsOrders.add(order);
  }

  Order takeWaitresToChefOrder() throws InterruptedException {
    if (waitresToChefsOrders.isEmpty()) {
      return null;
    }
    return waitresToChefsOrders.takeFirst();
  }

  void placeChefToWeitresOrder(Order order) {
    chefToWaitresOrders.add(order);
  }

  Order takeChefToWaitresOrder() throws InterruptedException {
    if (chefToWaitresOrders.isEmpty()) {
      return null;
    }
    return chefToWaitresOrders.takeFirst();
  }

  void placeCustomerOrder(Order order) {
    readyToServe.put(order.getCustomer(), order);
  }

  Order takeCustomerOrder(Customer customer) {
    if (readyToServe.isEmpty()) {
      return null;
    }
    Order order = readyToServe.get(customer);
    if (order != null) {
      readyToServe.remove(customer);
    }
    return order;
  }
}
