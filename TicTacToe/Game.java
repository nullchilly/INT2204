import java.util.*;

enum CellStatus {
  EMPTY, X, O;
}

class Cell {
  CellStatus status;

  Cell() {
    status = CellStatus.EMPTY;
  }

  static String to_string(Cell a) {
    switch (a.status) {
      case EMPTY:
        return " ";
      case X:
        return "X";
      case O:
        return "O";
    }
    return "Bruh";
  }
}

class Board {
  Cell[][] a = new Cell[3][3];

  Board() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        a[i][j] = new Cell();
      }
    }
  }

  void place(boolean turn) {
    Scanner scanner = new Scanner(System.in);
    int x = scanner.nextInt(), y = scanner.nextInt();
    --x;
    --y;
    if (x < 0 || y < 0 || x > 2 || y > 2 || a[x][y].status != CellStatus.EMPTY) {
      System.out.print("Invalid move, input again: ");
      place(turn);
      return;
    }
    a[x][y].status = turn ? CellStatus.X : CellStatus.O;
  }

  void display() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print("[" + Cell.to_string(a[i][j]) + "]");
      }
      System.out.println();
    }
  }

  boolean check() {
    for (int i = 0; i < 3; i++) {
      boolean row = true;
      for (int j = 1; j < 3; j++)
        if (a[i][j].status != a[i][j - 1].status)
          row = false;
      if (a[i][0].status != CellStatus.EMPTY && row)
        return true;
    }
    for (int j = 0; j < 3; j++) {
      boolean column = true;
      for (int i = 1; i < 3; i++)
        if (a[i][j].status != a[i - 1][j].status)
          column = false;
      if (a[0][j].status != CellStatus.EMPTY && column)
        return true;
    }
    boolean diagonal1 = true, diagonal2 = true;
    for (int i = 1; i < 3; i++) {
      if (a[i][i].status != a[i - 1][i - 1].status)
        diagonal1 = false;
      if (a[i][3 - i - 1].status != a[i - 1][3 - i].status)
        diagonal2 = false;
    }
    if (a[0][0].status != CellStatus.EMPTY && a[0][2].status != CellStatus.EMPTY && (diagonal1 || diagonal2))
      return true;
    return false;
  }
}

public class Game {
  public static void main(String[] args) {
    System.out.println("Tic tac toe");
    Board board = new Board();
    boolean turn = false;
    for (int i = 0; i < 9; i++) {
      turn = !turn;
      if (turn)
        System.out.println("X turn");
      else
        System.out.println("O turn");
      board.place(turn);
      board.display();
      if (board.check()) {
        System.out.println(turn ? "X win" : "O win");
        return;
      }
    }
    System.out.println("Draw");
  }
}