package com.lorenz.structures.lists;

import com.lorenz.nodes.DoubleNode;

public class Stack<T> implements List<T> {

  Integer size;
  DoubleNode<T> first;

  public Stack() {
    size = 0;
  }

  public Stack(T value) {
    size = 1;
    first = new DoubleNode<>(value);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Integer getSize() {
    return size;
  }

  @Override
  public void push(T value) {
    if (isEmpty()) {
      first = new DoubleNode<>(value);
    } else {
      DoubleNode<T> newNode = new DoubleNode<>(value);
      first.setRightNode(newNode);
      newNode.setLeftNode(first);
      first = newNode;
    }
    size++;
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      throw new IllegalArgumentException("La pila esta vacia");
    }
    first = first.getLeftNode();
    return first.getRightNode().getValue();
  }
}
