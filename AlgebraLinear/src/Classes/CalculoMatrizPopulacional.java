/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author David
 */
public class CalculoMatrizPopulacional {

    private double L[][]; //Matriz de Leslie
    private double B[][];//p-ezima potencia da matriz de Leslie
    private double R[][];// Matriz auxiliar
    private double A[];
    private double V[];
    private int n;//Ordem da matriz de leslie
    private double p;// p-esima potencia a se elevar a matriz de leslie
    private int i, j, k, z;
    private int contTaxaFecundidade;

    // public void loop(int matrix[][]) {
    //   for (int i = 0; i < 100; ++i) {
    //     for (int j = 0; j < 100; ++i) {
    //       matrix[i][j] = j;
    // }
    //  }
    //}
    public CalculoMatrizPopulacional() {
        this.L = new double[100][100];
        this.B = new double[100][100];
        this.R = new double[100][100];
        this.A = new double[100];
        this.V = new double[100];
        this.n = 0;
        this.p = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.z = 0;
        this.contTaxaFecundidade = 0;
    }
//set o n antes de executar

    public void taxaDeFecundidade(double taxaDeFecundidade) {//passo 1
        if (this.contTaxaFecundidade <= n) {
            L[0][this.contTaxaFecundidade] = taxaDeFecundidade;
            this.contTaxaFecundidade++;
        }
    }

    public void taxaDeSobrevivencia(double taxaDeSobrevivencia) {//passo 2
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (this.L[i][j] == 0) {
                    if (i == j + 1) {
                        this.L[i][j] = taxaDeSobrevivencia;
                        break;
                    }
                }
            }
        }
    }

    public void imprimirMatrizDeLeslie() {
        System.out.println("Matriz de Leslie");
        System.out.println("");
        for (int i = 0; i < n; i++) {
            System.out.print("Coluna " + i + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(this.L[i][j] + ", ");
                if (j == n) {
                    System.out.println("");
                }
            }
            System.out.println("");
            System.out.println("");
        }
    }

    public void matrizIdentidadeAuxiliar() {//passo 3
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    R[i][j] = 1;
                } else {
                    R[i][j] = 0;
                }
            }
        }
    }

    public void imprimirMatrizIdentidadeAuxiliar() {
        System.out.println("Matriz Identidade Auxiliar:  ");
        System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.R[i][j] + ", ");
                if (j == i) {
                    System.out.println("");
                }
            }
            System.out.println("");
            System.out.println("");
        }

    }

    public void pEzimaPotencia(double p) {//passo 4
        while (this.z <= p - 1) {
            this.z++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    B[i][j] = 0;
                    for (int k = 0; k < n; k++) {
                        B[i][j] = L[i][k] * R[k][j] + B[i][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    R[i][j] = B[i][j];
                }
            }
        }
    }

    public void imprimirMatrizPEzimaPotencia() {
        System.out.println("P-ezima potencia da matriz de Leslie:  ");
        System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.B[i][j] + ", ");
                if (j == i) {
                    System.out.println("");
                }
            }
            System.out.println("");
            System.out.println("");
        }


    }

    public void vetorDistribuicaoEtaria(double s) {//passo 5: preencher o vetor de distribuição Etaria inicial
        for (int i = 0; i < 10; i++) {
            if(this.A[i] == 0){
            this.A[i] = s;
            break;
            }
        }
    }
    
    public void calculoVetorDistribuicaoEtaria(){//passo 6
        for (int i = 0; i < n; i++) {
            V[i]=0;
            for (int j = 0; j < n; j++) {
                V[i] = B[i][j] * A[j] + V[i];
            }
        }
    }
    
    public void imprimirVetorDistribuicaoEtariaInstanteP(){
        System.out.println("O valor de distribuição no instante p: ");
        System.out.println("");
        
        for (int i = 0; i < n; i++) {
            System.out.println(V[i] + ", ");
        }
    }
    public double[][] getL() {
        return L;
    }

    public void setL(double[][] L) {
        this.L = L;
    }

    public double[][] getB() {
        return B;
    }

    public void setB(double[][] B) {
        this.B = B;
    }

    public double[][] getR() {
        return R;
    }

    public void setR(double[][] R) {
        this.R = R;
    }

    public double[] getA() {
        return A;
    }

    public void setA(double[] A) {
        this.A = A;
    }

    public double[] getV() {
        return V;
    }

    public void setV(double[] V) {
        this.V = V;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
