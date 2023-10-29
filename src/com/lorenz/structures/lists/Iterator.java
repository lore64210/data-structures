package com.lorenz.structures.lists;

import com.lorenz.nodes.Node;

import java.util.NoSuchElementException;

public class Iterator<T, L extends List<T>, N extends Node<T>> {
    private N currentNode;

    public Iterator(L list) {
        currentNode = (N)list.getFirstNode();
    }

    public void setCurrentNode(N currentNode) {
        this.currentNode = currentNode;
    }

    public boolean hasNext() {
        return currentNode != null;
    }

    public N next() {
        if (!hasNext()) throw new NoSuchElementException("La lista no contiene mas elementos");
        N node = currentNode;
        currentNode = (N)currentNode.getRightNode();
        return node;
    }
}
