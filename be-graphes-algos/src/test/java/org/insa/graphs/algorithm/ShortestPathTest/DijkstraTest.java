package org.insa.graphs.algorithm.ShortestPathTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.util.Elements.Origin;

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
    public static ShortestPathData dataT,dataT2, dataC, dataH,dataB;
    public static BellmanFordAlgorithm bell;
   
    public static void initAll() throws Exception {

        Node Origine , Destination ,Origine2, Destination2, Origine3, Destination3, Origine4, Destination4;


        //Cas Toulouse

        final String Toulouse = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/Toulouse.mapgr";

        final GraphReader readerT = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Toulouse))));
        
        Origine = readerT.read().getNodes().get(3605);
        Destination = readerT.read().getNodes().get(7781);

        
        dataT = new ShortestPathData(readerT.read(), Origine, Destination, ArcInspectorFactory.getAllFilters().get(0));

        dataT2 = new ShortestPathData(readerT.read(), readerT.read().getNodes().get(7174), readerT.read().getNodes().get(16557), ArcInspectorFactory.getAllFilters().get(0));



        
        //Cas Carre

        final String Carre = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre-dense.mapgr";

        final GraphReader readerC = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Carre))));

        Origine2 = readerC.read().getNodes().get(315979);
        Destination2 = readerC.read().getNodes().get(78657);

        dataC = new ShortestPathData(readerT.read(), Origine2, Destination2, ArcInspectorFactory.getAllFilters().get(0));

        

        
        //Cas Haute Garonne
        
        final String Haute_Garonne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
        
        final GraphReader readerH = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Haute_Garonne))));
        
        Origine3 = readerH.read().getNodes().get(778);
        Destination3 = readerH.read().getNodes().get(32568);



        dataH = new ShortestPathData(readerT.read(), Origine3, Destination3, ArcInspectorFactory.getAllFilters().get(0));




        //Cas bretagne

        final String Bretagne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";

        final GraphReader readerB = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Bretagne))));

        Origine4 = readerB.read().getNodes().get(657101);
        Destination4 = readerB.read().getNodes().get(56068);


        dataB = new ShortestPathData(readerT.read(), Origine4, Destination4, ArcInspectorFactory.getAllFilters().get(0));

    }

    //Pb à tester chemin inexistant, chemin de longueur nulle, trajet court,trajet long
    
        @Test

        public void TestToulouse(){
            //algo valide?
            //assertFalse(new DijkstraAlgorithm(dataT).getSolution().isValid());
            
            //Dijkstra renvoie le même resultat que bellman
            //assertEquals(n,new BellmanFordAlgorithm(dataT).doRun().getPath().getLength(),new DijkstraAlgorithm(dataT).getDoRun().getPath().getLength(),0);
            
            //Est ce que Dijkstra renvoie un chemin different
            assertNotEquals(new DijkstraAlgorithm(dataT), new DijkstraAlgorithm((dataT2)));


            
        }   

        @Test

        public void TestCheminInexistant(){
             //algo valide?
           // assertFalse(new DijkstraAlgorithm(dataB).getSolution().isValid());
            
            //Dijkstra renvoie le même resultat que bellman
            //assertFalse(new DijkstraAlgorithm(dataB).getDoRun().isFeasible());
            
        }


}
