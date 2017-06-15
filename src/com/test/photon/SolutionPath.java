package com.test.photon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SolutionPath {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 3 }, { 8, 9, 1, 2 }, { 9, 6, 5, 4 } };

        System.out.println(matrix.length);

        Node start = new Node(1, 0);

        System.out.println("Shortest path has cost=" + findShortestPath(matrix, start));

    }

    public static int findShortestPath(int[][] matrix, Node start) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Invalid matrix");
        }
        List<Integer> costs = new ArrayList<Integer>();

        int adjacentNodeCost = matrix[start.getX()][start.getY()];

        Node adjacentNode = findAdjacentNeighbour(matrix, start);
        while (adjacentNode != null) {
            adjacentNodeCost = adjacentNodeCost + matrix[adjacentNode.getX()][adjacentNode.getY()];
            adjacentNode = findAdjacentNeighbour(matrix, adjacentNode);
        }
        costs.add(adjacentNodeCost);

        int belowDiagonalNodeCost = matrix[start.getX()][start.getY()];
        Node belowDiagonalNeighbour = findBelowDiagonalNeighbour(matrix, start);
        while (belowDiagonalNeighbour != null) {
            belowDiagonalNodeCost = belowDiagonalNodeCost + matrix[belowDiagonalNeighbour.getX()][belowDiagonalNeighbour.getY()];
            belowDiagonalNeighbour = findBelowDiagonalNeighbour(matrix, belowDiagonalNeighbour);
        }
        if (belowDiagonalNodeCost > matrix[start.getX()][start.getY()]) {
            costs.add(belowDiagonalNodeCost);
        }

        int topDiagonalNodeCost = matrix[start.getX()][start.getY()];
        if (matrix.length > 0 && (start.getX() != 0 || start.getY() != 0)) {
            Node topDiagonalNeighbour = findTopDiagonalNeighbour(matrix, start);
            while (topDiagonalNeighbour != null) {
                topDiagonalNodeCost = topDiagonalNodeCost + matrix[topDiagonalNeighbour.getX()][topDiagonalNeighbour.getY()];
                topDiagonalNeighbour = findTopDiagonalNeighbour(matrix, topDiagonalNeighbour);
            }
        }
        if (topDiagonalNodeCost > matrix[start.getX()][start.getY()]) {
            costs.add(topDiagonalNodeCost);
        }

        if (!costs.isEmpty()) {
            Collections.sort(costs);
            return costs.get(0);
        }
        return matrix[start.getX()][start.getY()];

    }

    public static Node findAdjacentNeighbour(int[][] matrix, Node node) {
        int currentX = node.getX();
        int currentY = node.getY();

        if (currentY + 1 <= matrix[0].length - 1) {
            return new Node(currentX, currentY + 1);
        }
        return null;
    }

    public static Node findBelowDiagonalNeighbour(int[][] matrix, Node node) {
        int currentX = node.getX();
        int currentY = node.getY();

        if (currentX + 1 <= matrix.length - 1 && currentY + 1 <= matrix[0].length - 1) {
            return new Node(currentX + 1, currentY + 1);
        }
        return null;
    }

    public static Node findTopDiagonalNeighbour(int[][] matrix, Node node) {
        int currentX = node.getX();
        int currentY = node.getY();

        if (currentX - 1 >= 0 && currentY + 1 <= matrix[0].length - 1) {
            return new Node(currentX - 1, currentY + 1);
        }
        return null;
    }

}
