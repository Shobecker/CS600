/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularqueue;

/**
 *
 * @author Elias
 */
import java.util.Scanner;

public class CircularQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Queue q = new Queue(10);
        q.enqueue(1);
        q.print();
        q.enqueue(2);
        q.print();
        q.enqueue(3);
        q.print();
        q.enqueue(4);
        q.print();
        System.out.println(q.dequeue());
        q.print();
        System.out.println(q.dequeue());
        q.print();
        System.out.println(q.dequeue());
        q.print();
        System.out.println(q.dequeue());
        q.print();
        System.out.println(q.dequeue());
        q.print();
    }

}

class Queue {

    int front, rear;
    int[] Q;

    public Queue(int size) {
        /*
       * -=-=| JACOB IS A DUMB SHIT NOTE |=-=-
       * 'size+1' because one spot is always kept empty (the 'front' index)
       * otherwise, "empty" and "full" conditions will match,
       * and mayhem will ensue.  Mayhem was upon me, before I realized this.
       * there are other ways to do avoid this:
       * http://en.wikipedia.org/wiki/Circular_buffer#Difficulties
         */
        Q = new int[size + 1];
        front = 0;
        rear = 0;
    }

    public void enqueue(int val) {
        rear = (rear + 1) % Q.length;
        if (rear == front) { //if full
            System.out.println("Queue is full.");
            if (rear == 0) {
                rear = Q.length - 1;
            } else {
                rear = rear - 1;
            }
            return;
        }
        Q[rear] = val;
    }

    public int dequeue() {
        if (rear == front) {//if empty
            System.out.println("Queue is empty.");
            return -1;
        }
        int tmp = Q[front];
        front = (front + 1) % Q.length;
        return tmp;
    }

    public void print() {
        int curr = front;
        System.out.print("Queue state: ");
        if (curr == rear) {
            System.out.print("[empty]");
        } else {
            while (curr != rear) {
                curr = (curr + 1) % Q.length;
                System.out.print(Q[curr] + " ");
            }
        }
        System.out.println();
    }
}
