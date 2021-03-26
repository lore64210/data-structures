package com.lorenz;

import com.lorenz.structures.lists.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        while(list.hasNext()) {
            System.out.println(list.next());
        }
    }
}
