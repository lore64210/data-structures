package com.lorenz.structures.lists;

import com.lorenz.nodes.Node;

public abstract class List<T> {

  Node<T> first;
  abstract boolean isEmpty();

  abstract Integer getSize();

  abstract void push(T value);

  abstract T pop();

  protected Node<T> getFirstNode() {
    return this.first;
  };

}
