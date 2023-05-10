package org.insa.graphs.algorithm.shortestpath;
import javax.print.attribute.standard.MediaSize.Other;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.*;

public class LabelStar extends Label{
    private double estimated_cost;
	public LabelStar(Node current_node, boolean marque, double current_cost, Arc pere, Node current_desti) {
        
		super(current_node, marque, current_cost, pere);
        this.estimated_cost = Point.distance(current_node.getPoint(), current_desti.getPoint());
	}


	public double getEstimatedCost(){
        return this.estimated_cost;
    }

    @Override
    public double getTotalCost(){
        return this.current_cost + this.estimated_cost ;
    }
}
