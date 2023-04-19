package org.insa.graphs.algorithm.shortestpath;



import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.EmptyPriorityQueueException;

import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {


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




    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
    // Creation des labels des nodes    
    Label[] label = new Label[data.getGraph().size()];
    for (int i=0 ; i<data.getGraph().size(); i++){
        label[i] = new Label(data.getGraph().getNodes().get(i),false, Double.POSITIVE_INFINITY, null);
    }
    //On met le cout de l'origine a 0
    label[data.getOrigin().getId()].setCost(0);

    //Initialisation du tas et de son premier element
    BinaryHeap<Label> heap = new BinaryHeap<>();
    heap.insert(label[data.getOrigin().getId()]);
/*
    Graph graph = data.getGraph();
    boolean found = false;
    for (int i = 0; !found && i < graph.getNodes().size(); ++i) {
        found = true;
        for (Node node: graph.getNodes()) {
            for (Arc arc: node.getSuccessors()) {

                // Small test to check allowed roads...
                if (!data.isAllowed(arc)) {
                    continue;
                }
                double costY = label[arc.getDestination().getId()].getCost();
                double W = data.getCost(arc) + label[arc.getDestination().getId()].getCost();
                double costX = label[arc.getOrigin().getId()].getCost();

                if(Double.isFinite(costY) && Double.isFinite(costX+W)){
                    notifyNodeReached(arc.getDestination());
                }
                
                if(costY > costX + W){
                label[arc.getDestination().getId()].setCost(label[arc.getOrigin().getId()].getCost()+data.getCost(arc));
                label[arc.getDestination().getId()].setPere(arc);


                try{
                    heap.remove(label[arc.getDestination().getId()]);
                    heap.insert(label[arc.getDestination().getId()]);
                }catch(ElementNotFoundException e){
                    heap.insert(label[arc.getDestination().getId()]);
                }
            }
        }
    }
}
    
    
    ShortestPathSolution solution = null;

    if(!label[data.getDestination().getId()].getMarque()){
        solution = new ShortestPathSolution(data, Status.INFEASIBLE);
    }
    else{
        
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        
        Arc arc= label[data.getDestination().getId()].getPere();

        notifyDestinationReached(data.getDestination());
        
        while(arc != null){
            arcs.add(arc);
            //arc = label[data.getDestination().getId()].getPere().getOrigin();
        }
            Collections.reverse(arcs);

        

            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    }
    return solution;
} */

    //On prend le minimum 
    while(!heap.isEmpty() && label[data.getDestination().getId()].getMarque() != true){
        Label current;

        try{
            current = heap.deleteMin();
        }catch(EmptyPriorityQueueException e){
            break;
        }

        label[current.getNode().getId()].setMarque(true);

        for (Arc Successor : data.getGraph().get(current.getNode().getId()).getSuccessors()){
            if (!data.isAllowed(Successor)){
                continue;
            }
            if(!label[Successor.getDestination().getId()].getMarque()){
                double costY = label[Successor.getDestination().getId()].getCost();
                double W = data.getCost(Successor);
                double costX = label[current.getNode().getId()].getCost();
                
                if(Double.isInfinite(costY)){
                    notifyNodeReached(Successor.getDestination());;
                }

                if(costY > costX + W){


                    try{
                        heap.remove(label[Successor.getDestination().getId()]);
                
                    }catch(ElementNotFoundException e){
                        
                    }
                    label[Successor.getDestination().getId()].setCost(costX + W);
                    label[Successor.getDestination().getId()].setPere(Successor);
                    heap.insert(label[Successor.getDestination().getId()]);
                }
            }
        }
        }

    ShortestPathSolution solution = null;

    if(!label[data.getDestination().getId()].getMarque()){
        solution = new ShortestPathSolution(data, Status.INFEASIBLE);
    }
    else{
        
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        
        Arc current = label[data.getDestination().getId()].getPere();

        notifyDestinationReached(data.getDestination());
        
        while(current != null){
            arcs.add(current);
            current = label[current.getOrigin().getId()].getPere();
        }
        Collections.reverse(arcs);
        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));
        }
    return solution;
    }
}
 


