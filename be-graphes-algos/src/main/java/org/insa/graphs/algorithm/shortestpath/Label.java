package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;

import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {

    private Node current_node;
    private boolean marque;
    private double current_cost;
    private int pere;
    
    public Label(Node current_node, boolean marque , double current_cost, int pere){
        this.current_node = current_node;
        this.marque = marque;
        this.current_cost = Double.POSITIVE_INFINITY;
        this.pere = pere;
    }
    
    public Node getNode(){
        return this.current_node;
    }

    public double getCost(){
        return this.current_cost;
    }
    public int getPere(){
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
    public int setPere(int pere){
        this.pere = pere; 
    }
    public void setMarque(boolean Marque){
        this.marque = Marque;
    }
    public int CompareTo(Label label){
        return Double.compare(this.getCost(), label.getCost());
    }
}



        /* ITERATIONS */
        //While il existe des sommets non marqués
        //	x<--ExtractMin(Tas)
        //	Mark(x)<--true
        //	For tous les y successeurs de x
        //		If not Mark(y) then
        //			Cost(y)<--Min(Cost(y),Cost(x)+W(x,y))
        //			If Cost(y) a été mis à jour then
        //				Placer(y, Tas)
        //				Father(y)<--x
        //			end if
        //		end if
        //	end for
        //end while
