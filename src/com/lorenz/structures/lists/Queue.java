package com.lorenz.structures.lists;

import com.lorenz.nodes.DoubleNode;

public class Queue<T> implements List<T> {

  DoubleNode<T> first;
  DoubleNode<T> last;
  Integer size;

  public Queue() {
    size = 0;
  }

  public Queue(T value) {
    size = 1;
    first = new DoubleNode<>(value);
    last = first;
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
    DoubleNode<T> newNode = new DoubleNode<>(value);
    if (isEmpty()) {
      first = last = newNode;
    } else {
      last.setLeftNode(newNode);
      newNode.setRightNode(last);
      last = newNode;
    }
    size++;
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      throw new IllegalArgumentException("La cola esta vacia");
    }
    T value = first.getValue();
    first = first.getLeftNode();
    size--;
    return value;
  }

  public T get() {
    if (isEmpty()) {
      throw new IllegalArgumentException("La cola esta vacia");
    }
    return first.getValue();
  }

}
