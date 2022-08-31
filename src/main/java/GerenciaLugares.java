public class GerenciaLugares {
    // [fileiras][lugares]
    public boolean[][] lugares = new boolean[61][21];
    public boolean[][] getLugares() {
        return lugares;
    }
    public int passageiros;

    public int verificaLugar(String fila_assento) {
        int fila = new Integer(fila_assento.substring(1,3));
        int assento = new Integer(fila_assento.substring(4,6));
        System.out.println("F"+fila +"A"+assento);
        return verificaLugar(fila,assento);
    }

    private boolean checa(int fila, int assento) {
        return lugares[fila][assento];
    }

    private int verificaLugar(int fila, int assento) {
        //0 out of bounds
        if(fila >60 | assento > 20){
            return 0;
        }else{
            //1 assento ocupado
            if(lugares[fila][assento]){
                return 1;
            }

            //3 assento livre
            //3.1 Os primeiros 100 passageiros só podem se sentar nas fileiras de 1 a 20.
            if(passageiros<100 & fila<20){
                lugares[fila][assento] = true;
                passageiros++;
                return 3;
            }
            //3.2 Os próximos 100 passageiros só podem se sentar nas fileiras de 40 a 60.
            if (passageiros>=100 & passageiros<200 & fila>40 & fila <=60 ){
                lugares[fila][assento] = true;
                passageiros++;
                return 3;
            }
            return 2;
        }
    }

    public void desenhaBarca() {
        for (int i = 1; i < 61; i++) {
            if(i<10){
                System.out.print("F0" + i+" ");
            }else{
                System.out.print("F" + i+" ");
            }
            for (int j = 1; j < 21; j++) {
                boolean lugar = checa(i, j);
                //System.out.print("["+i+"]"+"["+j+"]: ");
                if (lugar) {
                    //assento OCUPADO
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
                //System.out.print("  ");
            }
            System.out.println();
        }
    }
}