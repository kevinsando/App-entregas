/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import lists.Iterator;
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
    
    public void add(V h, V t, E info){
        this.add(getVertex(h),getVertex(t),info);
    }
    
    public void add(Vertex<V> tail, Vertex<V> head, E info){
        if(head==null||tail==null){
            throw new NullPointerException("No existe Vertice");
        }
        edges.addLast(new Edge<>(tail,head,info));
    }

    public Vertex<V> getVertex(V info){ //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = vertex.getIterator();
        while(iterator.hasNext()){
            aux=iterator.getNext();
            if(aux.getInfo().equals(info)){
                result = aux;
                break;
            }
        }
        return result;
    }
    
    public List<Edge<V,E>> getEdges(V info){//BUSCA TODOS LOS CAMINOS QUE SALEN DEL VERTICE CON EL PARAMETRO INDICADO
        SimpleLinkedList<Edge<V,E>> result = new SimpleLinkedList<>();
        Iterator<Edge<V,E>> iterator = edges.getIterator();
        Edge<V,E> aux;
        while(iterator.hasNext()){
            aux = iterator.getNext();
            if(aux.getTail().getInfo().equals(info)){
                result.addLast(aux);
            }
        }
        return result;
        
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        Iterator<Edge<V,E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V,E> edgeAux;
        while(vertexIterator.hasNext()){
            vertexAux=vertexIterator.getNext();
            s.append("["+vertexAux.getInfo()+"] -->");
            edgeIterator=this.getEdges(vertexAux.getInfo()).getIterator();
            while(edgeIterator.hasNext()){
                edgeAux = edgeIterator.getNext();
                s.append(edgeAux.getHead().getInfo()+",");
            }
            s.append("\n");
        }
        return s.toString();
    }
    
}