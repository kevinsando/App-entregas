package mapas.graphs;

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
    @Override
    public String toString() {
        return String.format("%s,%s,%s", getTail(), getHead(), getInfo());
    }
        public double getWeight() {
        double r = 0.0;
        try {
            r = (Double) getInfo();
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return r;
    }
    
    
    
}
