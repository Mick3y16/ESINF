package ex04;

public class Ex04 {

    public static int[][] labyrinth = {
        {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public static void main(String[] args) {
        int[][] pathView = move(labyrinth, 0, 0);

        if (pathView != null) {
            for (int i = 0; i < pathView.length; i++) {
                for (int j = 0; j < pathView[0].length; j++) {
                    System.out.print(pathView[i][j] + " ");
                }

                System.out.println("");
            }
        } else {
            System.out.println("No path found!");
        }
    }

    public static int[][] move(int[][] labyrinth, int yPos, int xPos) {
        labyrinth[yPos][xPos] = 9;

        if (yPos == labyrinth.length - 1 && xPos == labyrinth[0].length - 1) {
            return labyrinth;
        }

        if (yPos > 0 && labyrinth[yPos - 1][xPos] == 1) {
            if (move(labyrinth, yPos - 1, xPos) != null) {
                return labyrinth;
            }
        }

        if (xPos < labyrinth[0].length - 1 && labyrinth[yPos][xPos + 1] == 1) {
            if (move(labyrinth, yPos, xPos + 1) != null) {
                return labyrinth;
            }
        }

        if (yPos < labyrinth.length - 1 && labyrinth[yPos + 1][xPos] == 1) {
            if (move(labyrinth, yPos + 1, xPos) != null) {
                return labyrinth;
            }
        }

        if (xPos > 0 && labyrinth[yPos][xPos - 1] == 1) {
            if (move(labyrinth, yPos, xPos - 1) != null) {
                return labyrinth;
            }
        }

        labyrinth[yPos][xPos] = 2;
        return null;
    }

}
