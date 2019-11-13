/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

/**
 *
 * @author Jason
 */
public class Edge<V,E>{
    private Vertex<V> head;
    private Vertex<V> tail;
    private E info;
    
    public Edge(Vertex<V> tail, Vertex<V> head, E info){
        this.head = head;
        this.tail = tail;
        this.info = info;
    }

    public Vertex<V> getHead() {
        return head;
    }

    public void setHead(Vertex<V> head) {
        this.head = head;
    }

    public Vertex<V> getTail() {
        return tail;
    }

    public void setTail(Vertex<V> tail) {
        this.tail = tail;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }
    
    
    
}
