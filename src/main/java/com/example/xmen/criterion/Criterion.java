package com.example.xmen.criterion;

import com.example.xmen.model.Node;
import java.util.LinkedList;
import java.util.List;

public interface Criterion {
    List<LinkedList<Node>> generateNodes(String[] strings);
}
