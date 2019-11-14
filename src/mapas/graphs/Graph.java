/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import lists.Iterator;
import lists.List;
import lists.SimpleLinkedList;

/**
 *
 * @author Jason
 */
public class Graph<V, E> {

    private List<Vertex<V>> vertex;
    private List<Edge<V, E>> edges;
    private int cantV;

    public Graph() {
        vertex = new SimpleLinkedList<>();
        edges = new SimpleLinkedList<>();
    }

    public void add(Vertex<V> v) {
        this.vertex.addLast(v);
        cantV++;
    }

    public void add(Edge<V, E> e) {
        this.edges.addLast(e);
    }

    public void add(V h, V t, E info) {
        this.add(getVertex(h), getVertex(t), info);
    }

    public void add(Vertex<V> tail, Vertex<V> head, E info) {
        if (head == null || tail == null) {
            throw new NullPointerException("No existe Vertice");
        }
        edges.addLast(new Edge<>(tail, head, info));
    }

    public int[][] ParseMatrizAdy() {
        int cantidad = this.cantV;
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        int[][] matrizAdy = new int[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {

            for (int j = 0; j < cantidad; j++) {

                matrizAdy[i][j] = 0;
            }
        }
          // matrizAdy[i][j] = ((Integer) this.getEdges().get(i).getInfo()) + (this.getEdges().get(j).getHead().getDistancia(this.getEdges().get(j).getTail()));
             while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
           // s.append("[" + vertexAux.getInfo() + "] -->");
            edgeIterator = this.getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                matrizAdy[(Integer)vertexAux.getInfo()-1][(Integer)edgeAux.getHead().getInfo()-1]=(int) ((Integer)edgeAux.getInfo()+edgeAux.getHead().getDistancia(vertexAux));
               // s.append(edgeAux.getHead().getInfo()+" Peso("+edgeAux.getInfo()+")" + ",");
            }
           // s.append("\n");
    }        return matrizAdy;
}

    public List<Vertex<V>> getVertex() {
        return vertex;
    }

    public void setVertex(List<Vertex<V>> vertex) {
        this.vertex = vertex;
    }

    public List<Edge<V, E>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<V, E>> edges) {
        this.edges = edges;
    }

    public Vertex<V> getVertex(V info) { //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = vertex.getIterator();
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            if (aux.getInfo().equals(info)) {
                result = aux;
                break;
            }
        }
        return result;
    }

    public List<Edge<V, E>> getEdges(V info) {//BUSCA TODOS LOS CAMINOS QUE SALEN DEL VERTICE CON EL PARAMETRO INDICADO
        SimpleLinkedList<Edge<V, E>> result = new SimpleLinkedList<>();
        Iterator<Edge<V, E>> iterator = edges.getIterator();
        Edge<V, E> aux;
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            if (aux.getTail().getInfo().equals(info)) {
                result.addLast(aux);
            }
        }
        return result;

    }

    public int size() {
        return cantV;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
            s.append("[" + vertexAux.getInfo() + "] -->");
            edgeIterator = this.getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                s.append(edgeAux.getHead().getInfo() + " Peso(" + edgeAux.getInfo() + ")" + ",");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String toString(int[][] mat) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                s.append(mat[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void paint(Graphics g) {
        Graphics2D media = (Graphics2D) g;
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        while (vertexIterator.hasNext()) {
            media.setStroke(new BasicStroke(5f));
            media.setColor(Color.BLACK);
            vertexAux = vertexIterator.getNext();
            media.drawOval((int) vertexAux.getPosition().x, (int) vertexAux.getPosition().y, 30, 30);
            edgeIterator = getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                media.setStroke(new BasicStroke(3f));
                media.setColor(Color.cyan);
                media.drawLine(
                        (int) edgeAux.getTail().getPosition().getX() + 15,
                        (int) edgeAux.getTail().getPosition().getY() + 15,
                        (int) edgeAux.getHead().getPosition().getX() + 15,
                        (int) edgeAux.getHead().getPosition().getY() + 15);

            }
        }

    }
}
