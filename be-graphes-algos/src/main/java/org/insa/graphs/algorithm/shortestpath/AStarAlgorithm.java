package org.insa.graphs.algorithm.shortestpath;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    @Override
    public Label[] creerlabel(ShortestPathData data){
        Label[] label = new LabelStar[data.getGraph().size()];
        for (int i=0 ; i<data.getGraph().size(); i++){
            label[i] = new LabelStar(data.getGraph().getNodes().get(i),false, Double.POSITIVE_INFINITY, null,data.getDestination());
        }
        return label;
    }
}
