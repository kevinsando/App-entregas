/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.awt.geom.Point2D;
import mapas.graphs.Graph;
import mapas.graphs.Vertex;
import mapas.view.View;

/**
 *
 * @author Kevin
 */
public class Mapas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<Integer, Integer> g1 = new Graph<>();
        g1.add(new Vertex<>(1, new Point2D.Float(1.5f, 2.5f)));
        g1.add(new Vertex<>(2, new Point2D.Float(3.5f, 5.0f)));
        g1.add(new Vertex<>(3, new Point2D.Float(30, 300)));
        g1.add(new Vertex<>(4, new Point2D.Float(400, 300)));
        g1.add(new Vertex<>(5, new Point2D.Float(700, 180)));

        g1.add(1, 4, 32);
        g1.add(1, 2, 2);
        g1.add(3, 1, 3);
        g1.add(4, 3, 52);
        g1.add(4, 5, 52);
        g1.add(4, 2, 52);
        g1.add(5, 2, 36);
        g1.add(3, 2, 36);
        System.out.println("Distancia: " + g1.getVertex(1).getDistancia(g1.getVertex(4)));
        System.out.println("Distancia: " + g1.getVertex(1).getDistancia(g1.getVertex(2)));
        System.out.println("Distancia: " + g1.getVertex(3).getDistancia(g1.getVertex(1)));
        System.out.println("Distancia: " + g1.getVertex(4).getDistancia(g1.getVertex(3)));
        System.out.println("Distancia: " + g1.getVertex(4).getDistancia(g1.getVertex(5)));
        System.out.println("Distancia: " + g1.getVertex(5).getDistancia(g1.getVertex(2)));
        System.out.println("Distancia: " + g1.getVertex(3).getDistancia(g1.getVertex(2)));
        
        System.out.println("Tamaño: " + g1.size());
        System.out.println(g1.toString());
        System.out.println(g1.toString(g1.ParseMatrizAdy()));
        View view = new View();
        view.setGraph(g1);
        view.setVisible(true);
    }

}
