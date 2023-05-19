package org.insa.graphs.algorithm.ShortestPathTest;


import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.*;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.insa.graphs.model.RoadInformation.RoadType;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;


public class DijkstraTest{

    final static String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";
    final static String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_bikini_canal.path";
    final static String MapRoutiere = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/Toulouse.mapgr";
    public static ShortestPathData data;
    public static BellmanFordAlgorithm bellman;
    public static DijkstraAlgorithm dijkstra;
    
    public static void initAll() throws Exception {
        
        final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

        final PathReader pathReader = new BinaryPathReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
        
        data = new ShortestPathData(reader.read(), pathReader.readPath(reader.read()).getOrigin(), pathReader.readPath(reader.read()).getDestination(), ArcInspectorFactory.getAllFilters().get(0));
        bellman = new BellmanFordAlgorithm(data);
        dijkstra = new DijkstraAlgorithm(data);
    }
    
        @Test

        public void TestDijkstra(){
            //Pb à tester chemin inexistant, chemin de longueur nulle, trajet court,trajet long
            assertEquals(bellman,dijkstra);;
        }


}
