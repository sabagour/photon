package com.test.photon;

import org.junit.Test;

public class SolutionPathTest {

    @Test
    public void test_withInput() {
        int[][] matrix = new int[][] { { 5 }, { 8 }, { 5 }, { 3 }, { 5 } };
        System.out.println(matrix[0].length);
        SolutionPath.Node start = new SolutionPath.Node(0,0);
        System.out.println("Shortest path=" + SolutionPath.findShortestPath(matrix, start));
    }

}
