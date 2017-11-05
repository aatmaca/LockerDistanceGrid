package pack;

import java.util.LinkedList;
import java.util.Queue;

public class LockerDistanceGrid {

	public static void main(String args[]) {

		int[][] resultMatrix = getLockerDistanceGrid(5, 7, new int[] { 2,4 }, new int[] { 3,7 });

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(resultMatrix[i][j]);
			}
			System.out.println();
		}
	}

	static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) {

		int[][] resultMatrix = new int[cityWidth][cityLength];

		// initialize matrix by Integer.MAX_VALUE. We also use Integer.MAX_VALUE
		// to check if this cell is visited or not.
		for (int i = 0; i < cityWidth; i++) {
			for (int j = 0; j < cityLength; j++) {
				resultMatrix[i][j] = Integer.MAX_VALUE;
			}
		}

		Queue<int[]> queue = new LinkedList<int[]>();

		// set locker position distance as zero
		// and enqueue current Locker positions
		for (int i = 0; i < lockerXCoordinates.length; i++) {
			resultMatrix[lockerYCoordinates[i] - 1][lockerXCoordinates[i] - 1] = 0;
			queue.add(new int[] { lockerYCoordinates[i] - 1, lockerXCoordinates[i] - 1, 0 });
		}

		// while there are coordinates to process
		while (queue.isEmpty() == false) {
			// dequeue next node
			int[] coordinate = (int[]) queue.poll();

			int rowCoordinate = coordinate[0];
			int columnCoordinate = coordinate[1];
			int newDistance = coordinate[2] + 1;

			// there are four alternatives

			for (int j = columnCoordinate - 1; j <= columnCoordinate + 1; j = j + 2) {

				if (j < 0 || j >= cityLength) {
					continue;
				}

				if (resultMatrix[rowCoordinate][j] == Integer.MAX_VALUE) {
					resultMatrix[rowCoordinate][j] = newDistance;
					// enqueue new elements from next level in order
					queue.add(new int[] { rowCoordinate, j, newDistance });
				} else if (resultMatrix[rowCoordinate][j] > newDistance) {
					resultMatrix[rowCoordinate][j] = newDistance;
				}

			}

			for (int i = rowCoordinate - 1; i <= rowCoordinate + 1; i = i+2) {

				if (i < 0 || i >= cityWidth ) {
					continue;
				}

				if (resultMatrix[i][columnCoordinate] == Integer.MAX_VALUE) {
					resultMatrix[i][columnCoordinate] = newDistance;
					// enqueue new elements from next level in order
					queue.add(new int[] { i, columnCoordinate, newDistance });
				} else if (resultMatrix[i][columnCoordinate] > newDistance) {
					resultMatrix[i][columnCoordinate] = newDistance;
				}

			}

		}

		return resultMatrix;

	}
}