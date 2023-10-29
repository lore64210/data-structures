package com.lorenz.structures.lists;

import com.lorenz.nodes.Node;
import com.lorenz.nodes.SimpleNode;

public class CircularLinkedList<T> extends List<T> {

    private SimpleNode<T> first;
    private SimpleNode<T> last;
    private Integer size;

    public CircularLinkedList() {
      size = 0;
      first = null;
      last = null;
    }

    public CircularLinkedList(T value) {
      size = 1;
      first = new SimpleNode<>(value);
      last = first;
    }

    public CircularLinkedList(CircularLinkedList<T> list) {
      size = list.getSize();
      first = list.getNode(0);
      last = list.getNode(size);
      last.setRightNode(first);
    }

    private SimpleNode<T> getNode(Integer position) {
      if (isEmpty() || position < 0) {
        throw new IllegalArgumentException("La posicion no existe");
      }
      try {
        SimpleNode<T> currentNode = first;
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
      return size == 0;
    }

    @Override
    public Integer getSize() {
      return size;
    }

    public T getFirst() {
      return first.getValue();
    }

    public T getLast() {
      return last.getValue();
    }

    public void add(T value, Integer position) {
      SimpleNode<T> nodeToAdd = new SimpleNode<>(value);
      if (isEmpty()) {
        if (position == 0) {
          push(value);
        } else {
          throw new IllegalArgumentException("Posicion incorrecta");
        }
      } else if (position.equals(size + 1)) {
        last.setRightNode(nodeToAdd);
        last = nodeToAdd;
        last.setRightNode(first);
      } else if (position == 0) {
        nodeToAdd.setRightNode(getNode(0));
        first = nodeToAdd;
      } else {
        SimpleNode<T> node = getNode(position - 1);
        nodeToAdd.setRightNode(node.getRightNode());
        node.setRightNode(nodeToAdd);
      }
      size++;
    }

    public T get(Integer position) {
      return getNode(position).getValue();
    }

    @Override
    public void push(T value) {
      if (isEmpty()) {
        first = new SimpleNode<>(value);
        last = first;
      } else {
        last.setRightNode(new SimpleNode<>(value));
        last = last.getRightNode();
        last.setRightNode(first);
      }
      size++;
    }

    @Override
    public T pop() {
      if (isEmpty()) {
        throw new IllegalArgumentException("La lista esta vacia");
      }
      T value = last.getValue();
      last = getNode(size - 1);
      last.setRightNode(first);
      size--;
      return value;
    }

}
