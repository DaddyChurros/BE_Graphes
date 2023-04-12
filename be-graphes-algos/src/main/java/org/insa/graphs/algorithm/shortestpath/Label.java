package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;

import org.insa.graphs.model.Node;

public class Label  {

    private Node current_node;
    private boolean marque;
    private double current_cost;
    private Arc pere;
    
    public Label(Node current_node){
        this.current_node = current_node;
        this.marque = false;
        this.current_cost = Double.POSITIVE_INFINITY;
        this.pere = null;
    }
    
    public Node getNode(){
        return this.current_node;
    }

    public double getCost(){
        return this.current_cost;
    }
    public Arc getPere(){
        return this.pere;
    }
    public void marquer(){
        this.marque = true;
    }
    public boolean getMarque(){
        return this.marque;
    }

    public void setCost(double cost){
        this.current_cost=cost;
    }
    public void setPere(Arc pere){
        this.pere = pere; 
    }
    public void setMarque(boolean Marque){
        this.marque = Marque;
    }

}
