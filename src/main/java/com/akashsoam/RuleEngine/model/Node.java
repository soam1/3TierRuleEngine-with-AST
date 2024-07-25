package com.akashsoam.RuleEngine.model;

import jakarta.persistence.*;

public class Node {
    private String type; // "operator" or "operand"
    private Node left;
    private Node right;
    private Object value; // Used for operand nodes

    public Node(){}
    public Node(String type) {
        this.type = type;
    }

    public Node(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
