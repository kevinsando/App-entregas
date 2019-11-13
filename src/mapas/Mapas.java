/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.awt.geom.Point2D;
import mapas.graphs.Graph;
import mapas.graphs.Vertex;

/**
 *
 * @author Kevin
 */
public class Mapas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<Integer,Integer> g1 = new Graph<>();
        g1.add(new Vertex<>(1,new Point2D.Float(30,20)));
        g1.add(new Vertex<>(2,new Point2D.Float(40,20)));
        g1.add(new Vertex<>(3,new Point2D.Float(50,20)));
        g1.add(new Vertex<>(4,new Point2D.Float(60,20)));
        g1.add(new Vertex<>(5,new Point2D.Float(70,20)));
        
        g1.add(1, 4, 32);
        g1.add(1, 2, 2);
        g1.add(3, 1, 3);
        g1.add(4, 3, 52);
        g1.add(4, 5, 52);
        g1.add(4, 2, 52);
        g1.add(5, 2, 36);
        
        System.out.println(g1.toString());
    }
    
}
