package com.lorenz.structures.trees;

import com.lorenz.nodes.DoubleNode;

public class BinaryTreeSearch {

  private DoubleNode<Integer> root;

  public BinaryTreeSearch(Integer value) {
    this.root = new DoubleNode<>(value);
  }

  public BinaryTreeSearch() {}

  public void add(Integer value) {
    root = recursiveAdd(root, value);
  }

  public boolean containsValue(Integer value) {
    return recursiveContainsValue(root, value);
  }

  public void delete(Integer value) {
    if (containsValue(value)) {
      root = recursiveDeleteValue(value, root);
    }
  }

  private DoubleNode<Integer> recursiveDeleteValue(Integer value, DoubleNode<Integer> currentNode) {
    if (currentNode.getValue().equals(value)) {
      if (currentNode.getRightNode() == null && currentNode.getLeftNode() == null) {
        return null;
      } else if (currentNode.getRightNode() != null && currentNode.getLeftNode() == null) {
        return currentNode.getRightNode();
      } else if (currentNode.getRightNode() == null && currentNode.getLeftNode() != null) {
        return currentNode.getLeftNode();
      } else {
        Integer newValue = findSmallest(currentNode.getRightNode());
        currentNode.setValue(newValue);
        delete(value);
        return currentNode;
      }
    } else if (currentNode.getValue() > value) {
      return recursiveDeleteValue(value, currentNode.getLeftNode());
    } else if (currentNode.getValue() < value) {
      return recursiveDeleteValue(value, currentNode.getRightNode());
    } else {
      throw new IllegalArgumentException("No existe el valor a borrar");
    }
  }

  private Integer findSmallest(DoubleNode<Integer> currentNode) {
    if (currentNode.getLeftNode() != null) {
      return findSmallest(currentNode.getLeftNode());
    }
    return currentNode.getValue();
  }

  public void printOrdered() {
    printOrdered(root);
  }

  public void printOrdered(DoubleNode<Integer> currentNode) {
    if (currentNode == null) {
      return;
    }
    if (currentNode.getLeftNode() != null) {
      printOrdered(currentNode.getLeftNode());
    }
    System.out.println(currentNode.getValue());
    if (currentNode.getRightNode() != null) {
      printOrdered(currentNode.getRightNode());
    }
  }

  private boolean recursiveContainsValue(DoubleNode<Integer> currentNode, Integer value) {
    if (currentNode == null) {
      return false;
    }
    if (value.equals(currentNode.getValue())) {
      return true;
    }
    return currentNode.getValue() > value ?
        recursiveContainsValue(currentNode.getLeftNode(), value ) :
        recursiveContainsValue(currentNode.getRightNode(), value );
  }

  private DoubleNode<Integer> recursiveAdd(DoubleNode<Integer> currentNode, Integer value) {
    if (currentNode == null) {
      return new DoubleNode<>(value);
    }
    if (value < currentNode.getValue()) {
      currentNode.setLeftNode(recursiveAdd(currentNode.getLeftNode(), value));
    } else {
      currentNode.setRightNode(recursiveAdd(currentNode.getRightNode(), value));
    }
    return currentNode;
  }
}
