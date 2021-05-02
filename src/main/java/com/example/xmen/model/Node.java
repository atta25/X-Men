package com.example.xmen.model;

import java.util.Objects;

public class Node {
    private char character;

    public Node(char character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return character == node.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }
}
