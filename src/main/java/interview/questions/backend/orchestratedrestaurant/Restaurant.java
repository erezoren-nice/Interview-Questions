package interview.questions.backend.orchestratedrestaurant;

import interview.questions.backend.orchestratedrestaurant.model.Chef;
import interview.questions.backend.orchestratedrestaurant.model.Customer;
import interview.questions.backend.orchestratedrestaurant.model.MenuItem;
import interview.questions.backend.orchestratedrestaurant.OrchestrationManager.Entity;
import interview.questions.backend.orchestratedrestaurant.model.Order;
import interview.questions.backend.orchestratedrestaurant.model.Waiters;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;


public class Restaurant {

  final int numOfCustomers;
  final int numOfWaitres;
  final int numOfChefs;
  final ExecutorService customerThreadPool;
  final ExecutorService waitresThreadPool;
  final ExecutorService chefThreadPool;
  final List<MenuItem> menuItems;
  final OrchestrationManager orchestrationManager;

  public Restaurant(int numOfCustomers, int numOfWaitres, int numOfChefs, List<MenuItem> menuItems) {
    this.numOfCustomers = numOfCustomers;
    this.numOfWaitres = numOfWaitres;
    this.numOfChefs = numOfChefs;
    customerThreadPool = Executors.newFixedThreadPool(numOfCustomers);
    waitresThreadPool = Executors.newFixedThreadPool(numOfWaitres);
    this.menuItems = menuItems;
    chefThreadPool = Executors.newFixedThreadPool(numOfChefs);
    orchestrationManager = new OrchestrationManager();
  }

  @SneakyThrows
  public void open() {

    for (int i = 1; i <= numOfChefs; i++) {
      chefThreadPool.submit(new Chef(i, this));
    }
    for (int i = 1; i <= numOfWaitres; i++) {
      waitresThreadPool.submit(new Waiters(i, this));
    }
    for (int i = 1; i <= numOfCustomers; i++) {
      customerThreadPool.submit(new Customer(i, this));
    }

    Thread.sleep(5000);
    customerThreadPool.shutdown();
    customerThreadPool.awaitTermination(1, TimeUnit.MINUTES);
    Thread.sleep(5000);
    waitresThreadPool.shutdown();
    waitresThreadPool.awaitTermination(30, TimeUnit.SECONDS);

    chefThreadPool.shutdownNow();
    System.out.println("Restaurant is closed");
    System.exit(0);
  }

  public void placeOrder(Order order, Entity from, Entity to) {
    orchestrationManager.placeOrder(order, from, to);
  }

  public Order takeOrder(Entity from, Entity to, Customer customer) throws InterruptedException {
    return orchestrationManager.takeOrder(from, to, customer);
  }

  public MenuItem getRandomMenuItem() {
    double randItem = Math.random() * menuItems.size();
    return menuItems.get((int) randItem);
  }

  public static void main(String[] args) {
    List<MenuItem> items = List.of(
        new MenuItem(1, "Soup"),
        new MenuItem(2, "Chicken"),
        new MenuItem(3, "Steak")
    );
    Restaurant restaurant = new Restaurant(10, 5, 3, items);
    restaurant.open();
  }
}
