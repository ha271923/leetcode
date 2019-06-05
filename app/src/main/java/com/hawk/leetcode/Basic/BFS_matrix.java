package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_matrix extends BaseClass {
    private Queue<Node> queue;
    static ArrayList<Node> nodes=new ArrayList<Node>();

    static class Node
    {
        int data;
        boolean visited;

        Node(int data)
        {
            this.data=data;
        }
    }

    public BFS_matrix()
    {
        queue = new LinkedList<Node>();
    }

    // find neighbors of node using adjacency matrix
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    public ArrayList<Node> findNeighbours(int adjacency_matrix[][],Node x)
    {
        int nodeIndex=-1;

        ArrayList<Node> neighbours=new ArrayList<Node>();
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(x))
            {
                nodeIndex=i;
                break;
            }
        }

        if(nodeIndex!=-1)
        {
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if(adjacency_matrix[nodeIndex][j]==1)
                {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }

    public void bfs(int adjacency_matrix[][], Node node)
    {
        queue.add(node);
        node.visited=true;
        while (!queue.isEmpty())
        {
            Node element=queue.remove();
            System.out.print(element.data + " > ");
            ArrayList<Node> neighbours=findNeighbours(adjacency_matrix,element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n=neighbours.get(i);
                if(n!=null && !n.visited)
                {
                    queue.add(n);
                    n.visited=true;

                }
            }
        }
    }

    public Object test()
    {
        Node node40 =new Node(40);
        Node node10 =new Node(10);
        Node node20 =new Node(20);
        Node node30 =new Node(30);
        Node node60 =new Node(60);
        Node node50 =new Node(50);
        Node node70 =new Node(70);

        nodes.add(node40);
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node60);
        nodes.add(node50);
        nodes.add(node70);

/**
 *    adjacency Map:
 *    40 ---> 20 ---> 50 ---> 70
 *     |      /|\             ^
 *     |     / | \            |
 *     V    /  |  \           /
 *    10 <--   |  --> 60 --->
 *     |       |      ^
 |       V      |
 *     | ---> 30 ---> |
 *
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >
 * */

        int adjacency_matrix[][] = {
//                N0, N1, N2, N3, N4, N5, N6
                {  0,  1,  1,  0,  0,  0,  0  }, // N0: 40
                {  0,  0,  0,  1,  0,  0,  0  }, // N1 :10
                {  0,  1,  0,  1,  1,  1,  0  }, // N2: 20
                {  0,  0,  0,  0,  1,  0,  0  }, // N3: 30
                {  0,  0,  0,  0,  0,  0,  1  }, // N4: 60
                {  0,  0,  0,  0,  0,  0,  1  }, // N5: 50
                {  0,  0,  0,  0,  0,  0,  0  }, // N6: 70
        };
        System.out.println("The BFS traversal of the graph is ");
        BFS_matrix bfsExample2 = new BFS_matrix();
        bfsExample2.bfs(adjacency_matrix, node40); // start node

        return null;

    }
}

