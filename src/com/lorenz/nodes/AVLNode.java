package com.lorenz.nodes;

public class AVLNode<T> implements Node<T> {
  private int height;
  private T value;
  private AVLNode<T> rightNode;
  private AVLNode<T> leftNode;

  public AVLNode(T value) {
    this.value = value;
    height = 0;
  }

  public AVLNode(T value, int height) {
    this.value = value;
    this.height = height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  public AVLNode<T> getRightNode() {
    return rightNode;
  }

  public AVLNode<T> getLeftNode() {
    return leftNode;
  }

  public void setRightNode(AVLNode<T> node) {
    rightNode = node;
  }

  public void setLeftNode(AVLNode<T> node) {
    leftNode = node;
  }
}
