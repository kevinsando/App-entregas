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
import mapas.view.seleccionRutas;

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
        g1.leerVertices();
        g1.leerAristas();
        View view = new View();
        view.setGraph(g1);
//        view.addCarrier();
        seleccionRutas rutas = new seleccionRutas(view);
        rutas.setVisible(true);

        //System.out.println("Distancia: " + g1.getVertex(1).getDistancia(g1.getVertex(2)));
        //System.out.println("Distancia: " + g1.getVertex(2).getDistancia(g1.getVertex(3)));
        //System.out.println("Distancia: " + g1.getVertex(1).getDistancia(g1.getVertex(3)));
        //System.out.println("Distancia: " + g1.getVertex(3).getDistancia(g1.getVertex(4)));
        //System.out.println("Distancia: " + g1.getVertex(4).getDistancia(g1.getVertex(5)));
        //System.out.println("Distancia: " + g1.getVertex(5).getDistancia(g1.getVertex(2)));
        //System.out.println("Distancia: " + g1.getVertex(3).getDistancia(g1.getVertex(2)));
        //O: El mismo nodo no puede tener relación consigo mismo
        //1000: Infinito
        System.out.println("Tamaño: " + g1.size());
//        System.out.println(g1.toString());
//      System.out.println(g1.toString(g1.ParseMatrizAdy()));
        System.out.println("Caminito mas corto: "+g1.algoritmoFloyd(g1.ParseMatrizAdy(),"33","35").toString());
    }

}
