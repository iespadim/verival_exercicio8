public class GerenciaLugares {
    // [fileiras][lugares]
    public boolean[][] lugares = new boolean[60][20];

    public boolean[][] getLugares() {
        return lugares;
    }

    public int verificaLugar(String FxxAxx) {
        return 0;
    }

    public boolean verificaLugar(int fila, int assento) {
        return lugares[fila][assento];
    }

    public void desenhaBarca() {
        for (int i = 0; i < 60; i++) {
            if(i<10){
                System.out.print("F0" + i+" ");
            }else{
                System.out.print("F" + i+" ");
            }
            for (int j = 0; j < 20; j++) {
                boolean lugar = verificaLugar(i, j);
                if (lugar) {
                    System.out.print("o ");
                } else {
                    System.out.print("v ");
                }
            }
            System.out.println();
        }
    }
}