package com.lorenz.nodes;

public class DoubleNode<T> implements Node<T> {

  private DoubleNode<T> leftNode;
  private DoubleNode<T> rightNode;
  private T value;

  public DoubleNode(T value) {
    this.value = value;
  }

  public void setLeftNode(DoubleNode<T> node) {
    leftNode = node;
  }

  public DoubleNode<T> getLeftNode() {
    return leftNode;
  }

  public void setRightNode(DoubleNode<T> rightNode) {
    this.rightNode = rightNode;
  }

  public DoubleNode<T> getRightNode() {
    return rightNode;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public T getValue() {
    return value;
  }

}
