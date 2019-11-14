/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

/**
 *
 * @author Kike
 */
public class caminosMinimos<V, E> {
    //Metodo para determinar los caminos por el algormitmo de Floyd
    public String algoritmoFloyd(long[][] grafo)
    {
        int vertices=grafo.length;
        long grafoAdy[][]=grafo;
        String caminos[][]=new String[vertices][vertices];
        String caminosAux[][]=new String[vertices][vertices];
        String caminoReccorrido="",cadena="",caminitos="";
        Graph<V,E> temp1,temp2,temp3,temp4,min;//min: camino mínimo
        //inicializando matrices String
        for(int i=0;i<vertices;i++)
        {
            for(int j=0;j<vertices;j++)
            {
                caminos[i][j]="";
                caminosAux[i][j]="";
            }
        }
        for(int k=0;k<vertices;k++)
        {
            for(int i=0;i<vertices;i++)
            {
                for(int j=0;j<vertices;j++)
                {
                   // temp1=grafoAdy.getVertex().g;
                }
            }
        }
    return "";
    }
    
}
