package com.lorenz.structures.lists;

import com.lorenz.nodes.DoubleNode;
import com.lorenz.nodes.Node;

public class DoubleLinkedList<T> extends List<T> {
    private DoubleNode<T> first;
    private DoubleNode<T> last;
    private Integer size;
    private Iterator<T, DoubleLinkedList<T>, DoubleNode<T>> iterator;

    public DoubleLinkedList() {
        size = 0;
        first = last = null;
        iterator = new Iterator<>(this);
    }
    public DoubleLinkedList(T value) {
        size = 1;
        first = new DoubleNode<>(value);
        last = first;
        iterator = new Iterator<>(this);
    }

    public DoubleLinkedList(DoubleLinkedList<T> list) {
        size = list.getSize();
        first = list.getNode(0);
        last = list.getNode(size);
        iterator = new Iterator<>(this);
    }

    protected DoubleNode<T> getNode(Integer position) {
        if (isEmpty() || position < 0) {
            throw new IllegalArgumentException("La posicion no existe");
        }
        try {
            DoubleNode<T> currentNode = first;
            for (int i = 0; i < position; i++) {
                currentNode = currentNode.getRightNode();
            }
            return currentNode;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("La posicion no existe");
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() != 0;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    public void add(T value) {
        if (first == null) {
            first = last = new DoubleNode<>(value);
            iterator.setCurrentNode(first);
        } else {
            DoubleNode<T> previousLast = last;
            last.setRightNode(new DoubleNode<>(value));
            last = last.getRightNode();
            last.setLeftNode(previousLast);
        }
        size++;
    }

    public void add(T value, Integer position) {
        DoubleNode<T> nodeToAdd = new DoubleNode<>(value);
        if (isEmpty()) {
            if (position == 0) {
                push(value);
            } else {
                throw new IllegalArgumentException("Posicion incorrecta");
            }
        } else if (position.equals(size + 1)) {
            last.setRightNode(nodeToAdd);
            nodeToAdd.setLeftNode(last);
            last = nodeToAdd;
        } else if (position > size + 1) {
            throw new IllegalArgumentException("Posicion incorrecta");
        } else if (position == 0) {
            nodeToAdd.setRightNode(first);
            first.setLeftNode(nodeToAdd);
            first = nodeToAdd;
        } else {
            DoubleNode<T> prevNode = getNode(position - 1);
            DoubleNode<T> nextNode = prevNode.getRightNode();
            nodeToAdd.setRightNode(nextNode);
            nodeToAdd.setLeftNode(prevNode);
            prevNode.setRightNode(nodeToAdd);
            nextNode.setLeftNode(nodeToAdd);
        }
        size++;
    }

    public void delete(T value) {
        this.iterator.setCurrentNode(this.first);
        int currentPosition = 0;
        do {
            DoubleNode<T> current = iterator.next();
            if (current.getValue().equals(value)) {
                this.delete(currentPosition);
                break;
            }
            currentPosition++;
        } while(this.iterator.hasNext());
        this.iterator.setCurrentNode(this.first);
    }

    public void delete(int position) {
        DoubleNode<T> current = getNode(position);
        if (position == 0) {
            this.first = current.getRightNode();
            current.setRightNode(null);
            this.first.setLeftNode(null);
        } else if (position == this.size - 1) {
            this.pop();
        } else {
            current.getLeftNode().setRightNode(current.getRightNode());
            current.getRightNode().setLeftNode(current.getLeftNode());
        }
        this.size--;
    }


    public T get(Integer position) {
        return getNode(position).getValue();
    }

    @Override
    public void push(T value) {
        if (isEmpty()) {
            first = new DoubleNode<>(value);
            last = first;
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(value);
            last.setRightNode(newNode);
            newNode.setLeftNode(last);
            last = newNode;
        }
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("La lista esta vacia");
        }
        T value = last.getValue();
        DoubleNode<T> newLast = last.getLeftNode();
        newLast.setRightNode(null);
        last = newLast;
        size--;
        return value;
    }

    public Integer getPositionOf(T value) {
        this.iterator.setCurrentNode(this.first);
        int position = -1;
        int currentPosition = 0;
        do {
            DoubleNode<T> current = iterator.next();
            if (current.getValue().equals(value)) {
                position = currentPosition;
                break;
            }
            currentPosition++;
        } while(this.iterator.hasNext());
        this.iterator.setCurrentNode(this.first);
        return position;
    }
}
