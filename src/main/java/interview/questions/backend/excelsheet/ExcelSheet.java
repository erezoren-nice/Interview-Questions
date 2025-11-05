package interview.questions.backend.excelsheet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExcelSheet {

  static class Cell {

    String content;
    double cellValue;
    boolean evaluated = false;

    Cell(String content) {
      this.content = content;
    }
  }

  static class Spreadsheet {

    Map<String, Cell> cellMap = new HashMap<>();

    void set(String cellId, String content) {
      cellMap.put(cellId.toUpperCase(), new Cell(content));
    }

    double get(String cellId) {
      Set<String> seen = new HashSet<>();
      return evaluate(cellId, seen);
    }

    private double evaluate(String cellId, Set<String> seen) {
      Cell cell = cellMap.get(cellId.toUpperCase());
      if (cell == null) {
        throw new RuntimeException("No such cell");
      }

      if (seen.contains(cellId)) {
        throw new RuntimeException("Cyclic dependency");
      }

      if (cell.evaluated) {
        return cell.cellValue;
      }

      if (!cell.content.startsWith("=")) {
        cell.cellValue = Double.parseDouble(cell.content);
      } else {
        String equation = cell.content.substring(1);
        String[] parts = equation.split("\\+");
        for (String part : parts) {
          part = part.trim();
          if (part.matches("[A-Z]+[0-9]+")) {
            cell.cellValue += evaluate(part, seen);
          } else {
            cell.cellValue += Double.valueOf(part);
          }
        }
      }

      cell.evaluated = true;
      return cell.cellValue;
    }

    void resetSheet() {
      for (Cell cell : cellMap.values()) {
        cell.evaluated = false;
        cell.cellValue = 0;
        cell.content = "";
      }
    }
  }

  public static void main(String[] args) {
    Spreadsheet sheet = new Spreadsheet();
    sheet.set("A1", "10");
    sheet.set("B2", "20");
    sheet.set("C3", "=A1 + B2 + 5");

    System.out.println("C3 = " + sheet.get("C3")); //  should print: 35.0
    sheet.resetSheet();
  }
}
