
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Tiaraju Mathyas Guerreiro
 */
public class AStar {

    private Quadrado[][] grade;
    private Quadrado origem;
    private Quadrado destino;
    private ArrayList<Quadrado> listaAberta;
    private ArrayList<Quadrado> listaFechada;
    private ArrayList<Quadrado> listaCaminho;

    public AStar(Quadrado grade[][], Quadrado origem, Quadrado destino) {
        this.grade = grade;
        this.origem = origem;
        this.destino = destino;
        listaAberta = new ArrayList<Quadrado>();
        listaFechada = new ArrayList<Quadrado>();
        listaCaminho = new ArrayList<Quadrado>();
    }

    public boolean iniciarPesquisa() {
        if (getGrade() == null) {
            return false;
        }
        if (getOrigem() == getDestino()) {
            return true;
        }

        listaAberta.add(getOrigem());
        if (pesquisar()) {
            return salvarCaminho();
        }
        return false;
    }

    private boolean pesquisar() {
        //procura quadrado com menor custo de F na lista aberta
        Quadrado corrente = listaAberta.get(0);
        for (int i = 1; i < listaAberta.size(); i++) {
            if (corrente.getCustoF() > listaAberta.get(i).getCustoF()) {
                corrente = listaAberta.get(i);
            }
        }

        listaFechada.add(corrente); //adiciona o quadrado corrente na lista fechada
        listaAberta.remove(corrente);

        if (corrente == destino) {   //encontrou
            return true;
        }

        //Varre todos os quadrados adjacentes ao quadrado corrente
        int x;
        int y;

        x = corrente.getX();
        y = corrente.getY();

        int direita = x + 1;
        int esquerda = x - 1;
        int acima = y - 1;
        int abaixo = y + 1;

        //quadrado adjacente da direita
        if (direita < grade[0].length) {
            Quadrado adjacenteDireta = grade[direita][y];
            if (!listaFechada.contains(adjacenteDireta)) {  //nao nao for bloqueio e nao estiver na lista fechada
                int custoG = corrente.getCustoG() + 1;      //calcula custo G - soma 1
                int custoH = Math.abs(destino.getX() - adjacenteDireta.getX()) + //calcula custo H
                        Math.abs(destino.getY() - adjacenteDireta.getY());       //de forma heuristica

                if (!listaAberta.contains(adjacenteDireta)) {    //se quadrado nao estiver na lista aberta
                    adjacenteDireta.setPai(corrente);           //faz quadrado corrente pai deste
                    listaAberta.add(adjacenteDireta);           //adiciona na lista aberta
                    adjacenteDireta.setCustoG(custoG);
                    adjacenteDireta.setCustoH(custoH);
                } else {                                        //se quadrado ja estiver na lista aberta
                    if (adjacenteDireta.getCustoH() > custoH) { //e seu custo atual G for maior que o novo
                        adjacenteDireta.setPai(corrente);       //entao troca quadrado pai
                        adjacenteDireta.setCustoG(custoG);      // e atualiza custos
                        adjacenteDireta.setCustoH(custoH);
                    }
                }
            }
        }

        //quadrado adjacente da esquerda
        if (esquerda > 0) {
            Quadrado adjacenteEsquerda = getGrade()[esquerda][y];
            if (!listaFechada.contains(adjacenteEsquerda)) {  //nao nao for bloqueio e nao estiver na lista fechada
                int custoG = corrente.getCustoG() + 1;      //calcula custo G - soma 1
                int custoH = Math.abs(getDestino().getX() - adjacenteEsquerda.getX()) + //calcula custo H
                        Math.abs(getDestino().getY() - adjacenteEsquerda.getY());       //de forma heuristica

                if (!listaAberta.contains(adjacenteEsquerda)) {    //se quadrado nao estiver na lista aberta
                    adjacenteEsquerda.setPai(corrente);           //faz quadrado corrente pai deste
                    listaAberta.add(adjacenteEsquerda);           //adiciona na lista aberta
                    adjacenteEsquerda.setCustoG(custoG);
                    adjacenteEsquerda.setCustoH(custoH);
                } else {                                        //se quadrado ja estiver na lista aberta
                    if (adjacenteEsquerda.getCustoH() > custoH) { //e seu custo atual G for maior que o novo
                        adjacenteEsquerda.setPai(corrente);       //entao troca quadrado pai
                        adjacenteEsquerda.setCustoG(custoG);      // e atualiza custos
                        adjacenteEsquerda.setCustoH(custoH);
                    }
                }
            }
        }

        //quadrado adjacente de cima
        if (acima > 0) {
            Quadrado adjacenteAcima = getGrade()[x][acima];
            if (!listaFechada.contains(adjacenteAcima)) {  //nao nao for bloqueio e nao estiver na lista fechada
                int custoG = corrente.getCustoG() + 1;      //calcula custo G - soma 1
                int custoH = Math.abs(getDestino().getX() - adjacenteAcima.getX()) + //calcula custo H
                        Math.abs(getDestino().getY() - adjacenteAcima.getY());       //de forma heuristica

                if (!listaAberta.contains(adjacenteAcima)) {    //se quadrado nao estiver na lista aberta
                    adjacenteAcima.setPai(corrente);           //faz quadrado corrente pai deste
                    listaAberta.add(adjacenteAcima);           //adiciona na lista aberta
                    adjacenteAcima.setCustoG(custoG);
                    adjacenteAcima.setCustoH(custoH);
                } else {                                        //se quadrado ja estiver na lista aberta
                    if (adjacenteAcima.getCustoH() > custoH) { //e seu custo atual G for maior que o novo
                        adjacenteAcima.setPai(corrente);       //entao troca quadrado pai
                        adjacenteAcima.setCustoG(custoG);      // e atualiza custos
                        adjacenteAcima.setCustoH(custoH);
                    }
                }
            }
        }

        //quadrado adjacente abaixo
        if (abaixo < grade.length) {
            Quadrado adjacenteAbaixo = grade[x][abaixo];
            if (!listaFechada.contains(adjacenteAbaixo)) {  //nao nao for bloqueio e nao estiver na lista fechada
                int custoG = corrente.getCustoG() + 1;      //calcula custo G - soma 1
                int custoH = Math.abs(getDestino().getX() - adjacenteAbaixo.getX()) + //calcula custo H
                        Math.abs(getDestino().getY() - adjacenteAbaixo.getY());       //de forma heuristica

                if (!listaAberta.contains(adjacenteAbaixo)) {    //se quadrado nao estiver na lista aberta
                    adjacenteAbaixo.setPai(corrente);           //faz quadrado corrente pai deste
                    listaAberta.add(adjacenteAbaixo);           //adiciona na lista aberta
                    adjacenteAbaixo.setCustoG(custoG);
                    adjacenteAbaixo.setCustoH(custoH);
                } else {                                        //se quadrado ja estiver na lista aberta
                    if (adjacenteAbaixo.getCustoH() > custoH) { //e seu custo atual G for maior que o novo
                        adjacenteAbaixo.setPai(corrente);       //entao troca quadrado pai
                        adjacenteAbaixo.setCustoG(custoG);      // e atualiza custos
                        adjacenteAbaixo.setCustoH(custoH);
                    }
                }
            }
        }

        if (listaAberta.isEmpty()) {    //nao ha nenhum caminho
            return false;
        }

        return pesquisar();     //pesquisa recursivamente
    }

    private boolean salvarCaminho() {
        Quadrado corrente = getDestino();

        if (corrente == null) {
            return false;
        }

        do {
            listaCaminho.add(corrente);
        } while (corrente.getPai() != null);
        return true;

    }
    
    public void setBloqueio(Quadrado bloqueio) {
        listaFechada.add(bloqueio);
    }

    //getters e setters
    public Quadrado[][] getGrade() {
        return grade;
    }

    public void setGrade(Quadrado[][] grade) {
        this.grade = grade;
    }

    public Quadrado getOrigem() {
        return origem;
    }

    public void setOrigem(Quadrado origem) {
        this.origem = origem;
    }

    public Quadrado getDestino() {
        return destino;
    }

    public void setDestino(Quadrado destino) {
        this.destino = destino;
    }

    public ArrayList<Quadrado> getListaCaminho() {
        return listaCaminho;
    }
    
}
