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
                Graph<Integer,Integer> g1 = new Graph<>();
        g1.add(new Vertex<>(1,new Point2D.Float(50,56)));
        g1.add(new Vertex<>(2,new Point2D.Float(50,137)));
        g1.add(new Vertex<>(3,new Point2D.Float(47,224)));
        g1.add(new Vertex<>(4,new Point2D.Float(43,312)));
        g1.add(new Vertex<>(5,new Point2D.Float(47,396)));
        g1.add(new Vertex<>(6,new Point2D.Float(144,50)));
        g1.add(new Vertex<>(7,new Point2D.Float(137,130)));
        g1.add(new Vertex<>(8,new Point2D.Float(137,230)));
        g1.add(new Vertex<>(9,new Point2D.Float(133,314)));
        g1.add(new Vertex<>(10,new Point2D.Float(137,400)));
        g1.add(new Vertex<>(11,new Point2D.Float(141,490)));
        g1.add(new Vertex<>(12,new Point2D.Float(141,570)));
        g1.add(new Vertex<>(13,new Point2D.Float(141,664)));
        g1.add(new Vertex<>(14,new Point2D.Float(221,50)));
        g1.add(new Vertex<>(15,new Point2D.Float(221,88)));
        g1.add(new Vertex<>(16,new Point2D.Float(221,138)));
        g1.add(new Vertex<>(17,new Point2D.Float(221,232)));
        g1.add(new Vertex<>(18,new Point2D.Float(223,313)));
        g1.add(new Vertex<>(19,new Point2D.Float(221,410)));
        g1.add(new Vertex<>(20,new Point2D.Float(222,493)));
        g1.add(new Vertex<>(21,new Point2D.Float(222,577)));
        g1.add(new Vertex<>(22,new Point2D.Float(224,662)));
        g1.add(new Vertex<>(23,new Point2D.Float(306,46)));
        g1.add(new Vertex<>(24,new Point2D.Float(308,226)));
        g1.add(new Vertex<>(25,new Point2D.Float(309,313)));
        g1.add(new Vertex<>(26,new Point2D.Float(311,405)));
        g1.add(new Vertex<>(27,new Point2D.Float(314,490)));
        g1.add(new Vertex<>(28,new Point2D.Float(313,576)));
        g1.add(new Vertex<>(29,new Point2D.Float(314,662)));
        g1.add(new Vertex<>(30,new Point2D.Float(400,43)));
        g1.add(new Vertex<>(32,new Point2D.Float(404,219)));
        g1.add(new Vertex<>(33,new Point2D.Float(405,310)));
        g1.add(new Vertex<>(34,new Point2D.Float(403,398)));
        g1.add(new Vertex<>(35,new Point2D.Float(406,488)));
        g1.add(new Vertex<>(36,new Point2D.Float(403,578)));
        g1.add(new Vertex<>(37,new Point2D.Float(401,668)));
        g1.add(new Vertex<>(31,new Point2D.Float(401,134)));

        
        g1.add(1,2,0);
        g1.add(2,3,0);
        g1.add(3,4,0);
        g1.add(4,5,0);
        g1.add(1,6,0);
        g1.add(7,2,0);
        g1.add(3,8,0);
        g1.add(9,4,0);
        g1.add(5,10,0);
        g1.add(6,7,0);
        g1.add(7,8,0);
        g1.add(8,9,0);
        g1.add(9,10,0);
        g1.add(9,11,0);
        g1.add(11,12,0);
        g1.add(12,13,0);
        g1.add(6,14,0);
        //g1.add(15,7,0);
        g1.add(8,17,0);
        g1.add(18,9,0);
        g1.add(10,19,0);
        g1.add(20,11,0);
        g1.add(12,21,0);
        g1.add(22,21,0);
        g1.add(21,20,0);
        g1.add(20,19,0);
        g1.add(19,18,0);
        g1.add(18,17,0);
        g1.add(17,16,0);
        g1.add(16,15,0);
        g1.add(15,14,0);
        g1.add(14,23,0);
        g1.add(17,24,0);
        g1.add(25,18,0);
        g1.add(19,26,0);
        g1.add(21,28,0);
        g1.add(23,24,0);
        g1.add(24,25,0);
        g1.add(25,26,0);
        g1.add(26,27,0);
        g1.add(27,28,0);
        g1.add(28,29,0);
        g1.add(37,36,0);
        g1.add(36,35,0);
        g1.add(35,34,0);
        g1.add(34,32,0);
        g1.add(33,32,0);
        g1.add(31,30,0);
        g1.add(32,31,0);
        g1.add(23,30,0);
        g1.add(24,32,0);
        g1.add(33,25,0);
        g1.add(26,34,0);
        g1.add(28,36,0);
        g1.add(35,27,0);
        
        //g1.add(,,0);
        
        //g1.add(,,0);
        //g1.add(new Vertex<>(1,new Point2D.Float(,)));

//        
//        g1.add(1, 4, 32);
//        g1.add(1, 2, 2);
//        g1.add(3, 1, 3);
//        g1.add(4, 3, 52);
//        g1.add(4, 5, 52);
//        g1.add(4, 2, 52);
//        g1.add(5, 2, 36);
//        g1.add(3, 2, 36);
        
        System.out.println(g1.toString());
        View view = new View();
        view.setGraph(g1);
        view.addCarrier();
        //view.init();  //Iniciar el hilo de movimiento
        view.setVisible(true);
        
        
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

    }

}
