package interview.questions.backend.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

  //y=x
  //y=x++
  // y=++x
  static Map<String, Integer> memory = new HashMap<>();

  public static void main(String[] args) {

    while (true) {
      System.out.print("Enter a command: ");
      Scanner scanner = new Scanner(System.in);
      String command = scanner.next();

      command = command.trim();
      if (command.equals("exit")) {
        System.out.println("Bye Bye..");
        System.exit(0);
      }
      if (command.endsWith(";")) {
        command = command.substring(0, command.length() - 1);
      }
      if (command.contains("=")) {
        eval(command);
      }

      memory.entrySet().forEach(es -> {
        System.out.println(String.format("%s=%d", es.getKey(), es.getValue()));
      });
    }
  }

  private static void eval(String command) {
    int value = 0;
    String[] parts = command.split("=");
    String var = parts[0];
    String val = parts[1];
    if (val.matches("\\d+")) {
      value = Integer.parseInt(val);
    } else if (val.matches("\\+\\+\\w+")) {
      String varName = val.substring(2);
      int varValue = memory.getOrDefault(varName, 0);
      varValue += 1;
      memory.put(varName, varValue);
      value = varValue;
    } else if (val.matches("\\w+\\+\\+")) {
      String varName = val.substring(0, 1);
      int varValue = memory.getOrDefault(varName, 0);
      value = varValue;
      varValue += 1;
      memory.put(varName, varValue);
    } else if (memory.get(val) != null) {
      value = memory.get(val);
    } else {
      System.out.println("Unsupported expression: " + val);
      return;
    }

    memory.put(var, value);
  }
}
