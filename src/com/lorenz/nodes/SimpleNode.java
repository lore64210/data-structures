package com.lorenz.nodes;

public class SimpleNode<T> implements Node<T> {

  protected T value;
  protected Node<T> rightNode;

  public SimpleNode(T value) {
    this.value = value;
    rightNode = null;
  }

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = (T) value;
  }

  public SimpleNode<T> getRightNode() {
    return (SimpleNode<T>) rightNode;
  }

  public void setRightNode(Node<T> rightNode) {
    this.rightNode = rightNode;
  }

}
