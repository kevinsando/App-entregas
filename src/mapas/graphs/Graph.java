/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import lists.List;
import lists.SimpleLinkedList;

/**
 *
 * @author Jason
 */
public class Graph<V,E> {
    private List<Vertex<V>> vertex;
    private List<Edge<V,E>> edges;
    
    public Graph(){
        vertex = new SimpleLinkedList<>();
        edges = new SimpleLinkedList<>();
    }
    
    public void add(Vertex<V> v){
        this.vertex.addLast(v);
    }
    
    public void add(Edge<V,E> e){
        this.edges.addLast(e);
    }
    
}
