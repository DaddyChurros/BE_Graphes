package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;

public class Label {

    public Node current_node;
    public boolean marque;
    private int current_cost;
    public Arc pere;
    
    public Label(Node current, boolean marque, int cost, Arc pere){
        this.current_node = current;
        this.marque = marque;
        this.current_cost = cost;
        this.pere = pere;
    }
    
    public int getCost(){
        return this.current_cost;
    }

    public void linkLabel(Graph graph){
        final int nb_nodes = graph.size();
        
        
    }
}
