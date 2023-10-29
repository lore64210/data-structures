package com.lorenz.nodes;

public interface Node<T> {

  T getValue();

  void setValue(T value);

  Node<T> getRightNode();

}
