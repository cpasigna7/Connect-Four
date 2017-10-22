public class TestDriver {
  public static void main(String[] args) {
    // Start by testing out if the initialization works
    Board standardBoard = new Board();

    // Test dimensions
    if (standardBoard.getNumRows() == 6) {
      System.out.println("PASS: getNumRows() for standard board returns correct row count");
    } else {
      System.out.println("FAIL: getNumRows() for standard board does not return correct row count");
    }
    if (standardBoard.getNumCols() == 7) {
      System.out.println("PASS: getNumCols() for standard board returns correct column count");
    } else {
      System.out.println("FAIL: getNumCols() for standard board does not return correct column count");
    }

    // Now test if custom initialization works
    Board customBoard = new Board(7, 8);

    // Test dimensions
    if (customBoard.getNumRows() == 7) {
      System.out.println("PASS: getNumRows() for custom board returns correct row count");
    } else {
      System.out.println("FAIL: getNumRows() for custom board does not return correct row count");
    }
    if (customBoard.getNumCols() == 8) {
      System.out.println("PASS: getNumCols() for custom board returns correct column count");
    } else {
      System.out.println("FAIL: getNumCols() for custom board does not return correct column count");
    }

    // Print out default player char
    System.out.println("DEFAULT: player1 is " + standardBoard.getPlayerOne());
    System.out.println("DEFAULT: player2 is " + standardBoard.getPlayerTwo());

    // Print out custom player char
    standardBoard.setPlayerOne('X');
    if (standardBoard.getPlayerOne() == 'X') {
      System.out.println("PASS: getPlayerOne() matches input for setPlayerOne()");
    } else {
      System.out.println("FAIL: getPlayerOne() does not match input for setPlayerOne()");
    }
    standardBoard.setPlayerTwo('O');
    if (standardBoard.getPlayerTwo() == 'O') {
      System.out.println("PASS: getPlayerTwo() matches input for setPlayerTwo()");
    } else {
      System.out.println("FAIL: getPlayerTwo() does not match input for setPlayerTwo()");
    }

    // Examine the entire board for spaces
    boolean boardValid = true;
    for (int i = 0; i < standardBoard.getNumRows() && boardValid; i++) {
      for (int j = 0; j < standardBoard.getNumCols() && boardValid; j++) {
        if (standardBoard.getToken(i, j) != ' ') {
          boardValid = false;
        }
      }
    }
    if (boardValid) {
      System.out.println("PASS: board is initially set to all spaces");
    } else {
      System.out.println("FAIL: board is not initially set to all spaces");
    }

    // Make sure that the getToken() method return \0 during an invalid query
    if (standardBoard.getToken(-1, -1) == '\0') {
      System.out.println("PASS: getToken(-1, -1) returns \\0");
    } else {
      System.out.println("FAIL: getToken(-1, -1) does not return \\0");
    }
    if (standardBoard.getToken(-1, standardBoard.getNumCols()) == '\0') {
      System.out.println("PASS: getToken(-1, getNumCols()) returns \\0");
    } else {
      System.out.println("FAIL: getToken(-1, getNumCols()) does not return \\0");
    }
    if (standardBoard.getToken(standardBoard.getNumRows(), -1) == '\0') {
      System.out.println("PASS: getToken(getNumRows(), -1) returns \\0");
    } else {
      System.out.println("FAIL: getToken(getNumRows(), -1) does not return \\0");
    }
    if (standardBoard.getToken(standardBoard.getNumRows(), standardBoard.getNumCols()) == '\0') {
      System.out.println("PASS: getToken(etNumRows(), getNumCols()) returns \\0");
    } else {
      System.out.println("FAIL: getToken(getNumRows(), getNumCols()) does not return \\0");
    }

    // Check bounds
    if (standardBoard.play(1, 0)){
      System.out.println("PASS: c = 0 returns true");
    } else {
      System.out.println("FAIL: c = 0 returns false");
    }
    if (standardBoard.play(2, standardBoard.getNumCols() - 1)) {
      System.out.println("PASS: c = getNumCols()-1 returns true");
    } else {
      System.out.println("FAIL: c = getNumCols()-1 returns false");
    }
    if (!standardBoard.play(1, -1)) {
      System.out.println("PASS: c = -1 returns false");
    } else {
      System.out.println("FAIL: c = -1 returns true");
    }
    if (!standardBoard.play(2, standardBoard.getNumCols())) {
      System.out.println("PASS: c = getNumCols() returns false");
    } else {
      System.out.println("FAIL: c = getNumCols() returns true");
    }

    // Check current play status
    if (standardBoard.canPlay()) {
      System.out.println("PASS: canPlay() returns true to keep playing");
    } else {
      System.out.println("FAIL: canPlay() returns false to stop playing");
    }
    if (standardBoard.isFinished() == -1) {
      System.out.println("PASS: canPlay() returns -1 to keep playing");
    } else {
      System.out.println("FAIL: canPlay() does not return -1 to keep playing");
    }

    // Check overflow of a column
    for (int i = 0; i < standardBoard.getNumRows(); i++) {
      standardBoard.play(i % 2 + 1, 1);
    }
    if (!standardBoard.play(standardBoard.getNumRows() % 2 + 1, 1)) {
      System.out.println("PASS: play() returns false on overflow");
    } else {
      System.out.println("FAIL: play() does not return false on overflow");
    }

    // Try out different scenarios to start playing the game
    int plays;
    // Scenario 1: Tie
    Board b1 = new Board(4, 4);
    int[] c1 = { 0, 1, 0, 1, 0, 1, 1, 0, 2, 3, 2, 3, 2, 3, 3, 2 };
    for (plays = 0; plays < c1.length; plays++) {
      if (b1.isFinished() == -1) {
        b1.play(plays % 2 + 1, c1[plays]);
      } else {
        break;
      }
    }
    if (plays == c1.length && !b1.canPlay() && b1.isFinished() == 0) {
      System.out.println("PASS: tie scenario");
    }
    if (plays != c1.length) {
      System.out.println("FAIL: less plays than expected");
    } else {
      if (b1.canPlay()) {
        System.out.println("FAIL: canPlay() returns true when it should be false");
      }
      if (b1.isFinished() != 0) {
        System.out.println("FAIL: isFinished() does not return 0 for a tie");
      }
    }

    // Scenario 2: Player 1 wins (horizontally)
    Board b2 = new Board(4, 4);
    int[] c2 = { 0, 0, 1, 0, 2, 0, 3 };
    for (plays = 0; plays < c2.length; plays++) {
      if (b2.isFinished() == -1) {
        b2.play(plays % 2 + 1, c2[plays]);
      } else {
        break;
      }
    }
    if (plays == c2.length && b2.canPlay() && b2.isFinished() == 1) {
      System.out.println("PASS: player1 wins scenario horizontally");
    }
    if (plays != c2.length) {
      System.out.println("FAIL: less plays than expected");
    } else {
      if (!b2.canPlay()) {
        System.out.println("FAIL: canPlay() returns false when it should be true");
      }
      if (b2.isFinished() != 1) {
        System.out.println("FAIL: isFinished() does not return 1 for a player1 win horizontally");
      }
    }

    // Scenario 3: Player 2 wins (vertically)
    Board b3 = new Board(4, 4);
    int[] c3 = { 0, 3, 1, 3, 0, 3, 1, 3 };
    for (plays = 0; plays < c3.length; plays++) {
      if (b3.isFinished() == -1) {
        b3.play(plays % 2 + 1, c3[plays]);
      } else {
        break;
      }
    }
    if (plays == c3.length && b3.canPlay() && b3.isFinished() == 2) {
      System.out.println("PASS: player2 wins scenario vertically");
    }
    if (plays != c3.length) {
      System.out.println("FAIL: less plays than expected");
    } else {
      if (!b3.canPlay()) {
        System.out.println("FAIL: canPlay() returns false when it should be true");
      }
      if (b3.isFinished() != 2) {
        System.out.println("FAIL: isFinished() does not return 2 for a player2 win vertically");
      }
    }

    // Scenario 4: Player 1 wins (diagonally)
    Board b4 = new Board(4, 4);
    int[] c4 = { 0, 1, 1, 2, 3, 2, 2, 3, 3, 0, 3 };
    for (plays = 0; plays < c4.length; plays++) {
      if (b4.isFinished() == -1) {
        b4.play(plays % 2 + 1, c4[plays]);
      } else {
        break;
      }
    }
    if (plays == c4.length && b4.canPlay() && b4.isFinished() == 1) {
      System.out.println("PASS: player 1 wins scenario SW-NE diagonally");
    }
    if (plays != c4.length) {
      System.out.println("FAIL: less plays than expected");
    } else {
      if (!b4.canPlay()) {
        System.out.println("FAIL: canPlay() returns false when it should be true");
      }
      if (b4.isFinished() != 1) {
        System.out.println("FAIL: isFinished() does not return 1 for a player1 win SW-NE diagonally");
      }
    }

    // Scenario 5: Player 2 wins (diagonally)
    Board b5 = new Board(4, 4);
    int[] c5 = { 2, 3, 1, 2, 1, 1, 0, 0, 0, 0 };
    for (plays = 0; plays < c5.length; plays++) {
      if (b5.isFinished() == -1) {
        b5.play(plays % 2 + 1, c5[plays]);
      } else {
        break;
      }
    }
    if (plays == c5.length && b5.canPlay() && b5.isFinished() == 2) {
      System.out.println("PASS: player2 wins scenario NW-SE diagonally");
    }
    if (plays != c5.length) {
      System.out.println("FAIL: less plays than expected");
    } else {
      if (!b5.canPlay()) {
        System.out.println("FAIL: canPlay() returns false when it should be true");
      }
      if (b5.isFinished() != 2) {
        System.out.println("FAIL: isFinished() does not return 2 for a player2 win NW-SE diagonally");
      }
    }
  }
}
