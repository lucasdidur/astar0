/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tiaraju
 */
public class TesteAStar {
    
    public static void main(String[] args) {
        
        Quadrado[][] grade = new Quadrado[5][5];
        for(int i=0; i<grade.length; i++) {
            for(int j=0; j<grade[i].length; j++) {
                grade[i][j] = new Quadrado(i, j);
            }
        }
        
        AStar aStar = new AStar(grade, grade[0][0], grade[2][1]);
        if(aStar.iniciarPesquisa()) {
            for(int i=0; i<aStar.getListaCaminho().size(); i++) {
                System.out.println(aStar.getListaCaminho().get(i).getX() + 
                        "  " + aStar.getListaCaminho().get(i).getX());
            }
        }
    }
    
}
