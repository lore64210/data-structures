package com.lorenz.structures.trees;

import com.lorenz.nodes.AVLNode;

public class AVLTree {

  private AVLNode<Integer> root;

  private int height(AVLNode<Integer> node) {
    return node == null ? -1 : node.getHeight();
  }

  private void updateHeight(AVLNode<Integer> node) {
    node.setHeight(1 + Math.max(height(node.getLeftNode()), height(node.getRightNode())));
  }

  private int getBalance(AVLNode<Integer> node) {
    return node == null ? 0 : height(node.getRightNode()) - height(node.getLeftNode());
  }

}
