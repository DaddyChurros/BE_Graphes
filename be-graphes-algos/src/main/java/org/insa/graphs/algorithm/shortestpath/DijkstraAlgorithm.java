package org.insa.graphs.algorithm.shortestpath;


import org.insa.graphs.algorithm.shortestpath.Label;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.EmptyPriorityQueueException;
import org.insa.graphs.model.Arc;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
    Label[] label = new Label[data.getGraph().size()];
    for (int i=0 ; i<data.getGraph().size(); i++){
        label[i] = new Label(data.getGraph().getNodes().get(i),false, Double.POSITIVE_INFINITY, null);
    }

    label[data.getOrigin().getId()].setCost(0);

    BinaryHeap<Label> heap = new BinaryHeap<>();
    heap.insert(label[data.getOrigin().getId()]);

    while(heap.isEmpty() && label[data.getDestination().getId()].getMarque() != true){
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
            double W = data.getCost(Successor) + label[Successor.getDestination().getId()].getCost();
            double costX = label[current.getNode().getId()].getCost();
            
            if(Double.isFinite(costY) && Double.isFinite(costX+W)){
                notifyNodeReached(Successor.getDestination());;
            }

            if(costY > costX + W){
                label[Successor.getDestination().getId()].setCost(label[Successor.getOrigin().getId()].getCost()+data.getCost(Successor));
                label[Successor.getDestination().getId()].setPere(current.getNode().getId());

                try{
                    heap.remove(label[Successor.getDestination().getId()]);
                    heap.insert(label[Successor.getDestination().getId()]);
                }catch(ElementNotFoundException e){
                    heap.insert(label[Successor.getDestination().getId()]);
                }
            }
        }
    }
    }
    return solution;
    }


}
