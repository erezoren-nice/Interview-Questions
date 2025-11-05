package interview.questions.backend.orchestratedrestaurant.model;

import lombok.Data;

@Data
public class Order {

  final int id;
  final MenuItem menuItem;
  final Customer customer;

  final long preparationTime;
}
