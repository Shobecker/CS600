/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs600_assingment3_problem2;

/**
 *
 * @author Elias
 */
import java.util.*;
import java.io.*;

//implement circular queue using array
class Queue {

    int front, rear, index, maxSize;
    String[] Q;

    public Queue(int size) {
        //making a circular queue. One spot must be kept empty, if not "empty" and "full" conditions will be the same
        Q = new String[size + 1];
        front = 0;
        rear = 0;
        this.maxSize = size;
        index = 0;
    }

    //put value at end of queue.
    public void enqueue(String value) {

        //check if queue is full
        rear = (rear + 1) % Q.length;
        if (isFull()) {
            System.out.println("Queue is full. Cannot add element");
            if (rear == 0) {
                rear = Q.length - 1;
            } else {
                rear = rear - 1;
            }
            return;
        }
        Q[rear] = value;
        index++;
    }

    //remove value from front of queue
    public String dequeue() {
        //check if queue is empty
        if (isEmpty()) {
            return "Queue is empty.";
        }
        String deQElem = Q[front];
        front = (front + 1) % Q.length;
        index--;
        return deQElem;
    }

//print the queue
    public void print() {
        int current = front;
        System.out.print("Queue: ");
        //check if empty
        if (isEmpty()) {
            System.out.print("[Empty]");
        } else {
            while (current != rear) {
                current = (current + 1) % Q.length;
                System.out.print(Q[current] + " ");
            }
        }
        System.out.println("");
    }

    //check if queue is empty
    public boolean isEmpty() {
        return (front == rear);
    }

    public String peek() {
        if (isEmpty()) {
            return "Queue is full";
        }
        int temp = front + 1;
        if (front >= maxSize) {
            temp = 0;
        }
        return Q[temp];
    }

    //check if queue is full
    public boolean isFull() {
        return (index == maxSize);

    }
}

public class Josephus_Problem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Enter the number of soldiers and thier names
        System.out.print("How many soldiers? ");
        int size = scanner.nextInt();
        Queue q = new Queue(size);
        System.out.println("Enter " + size + " soldiers names");

        //put the names of the soldiers into the queue
        Scanner myscan = new Scanner(System.in);
        for (int i = 1; i <= size; i++) {
            String name;
            name = myscan.nextLine();
            q.enqueue(name + "("+ i + ")");
        }
        
        //print the names added
        q.print();
        System.out.println("Enter the position: ");

        //get the elimating order
        int pNum = scanner.nextInt();
        System.out.println("Eliminating Order:");

        while (q.index != 1) {
            for (int i = 1; i < pNum; i++) {
                String temp = q.peek();
                q.dequeue();
                q.enqueue(temp);
            }
            String temp = q.peek();
            q.dequeue();
            System.out.println(temp);
        }

        System.out.println("Survior: " + q.peek());
    }

}
