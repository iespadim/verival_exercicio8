import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GerenciaLugares {
    public boolean[][] lugares = new boolean[61][21];
    private int passageiros = 0;

    public int[] leString(String fila_assento){
        int[] result = new int[2];
        final Pattern pattern = Pattern.compile("^F([0-9]+[0-9]+)+A([0-9]+[0-9]+)+");
        final Matcher matcher = pattern.matcher(fila_assento);

        if(matcher.find()){
            result[0] = new Integer(matcher.group(1));
            result[1] = new Integer(matcher.group(2));
        }
        return result;
    }

    public int verificaLugar(String fila_assento) {
        int fila = leString(fila_assento)[0];
        int assento = leString(fila_assento)[1];

        if (fila+assento>0){
            return verificaLugar(fila,assento);
        }
        return 0;
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
            }else {
                //3 assento livre
                //3.1 Os primeiros 100 passageiros só podem se sentar nas fileiras de 1 a 20.
                if (passageiros <= 100 & fila <= 20) {
                    lugares[fila][assento] = true;
                    passageiros++;
                    return 3;
                }

                //3.2 Os próximos 100 passageiros só podem se sentar nas fileiras de 40 a 60.
                if (passageiros > 100 & passageiros <= 200 & fila >= 40) {
                    lugares[fila][assento] = true;
                    passageiros++;
                    return 3;
                }

                //3.3 demais casos
                if (passageiros > 200) {
                    lugares[fila][assento] = true;
                    passageiros++;
                    return 3;
                }
            }
            return 2;
        }
    }

    public int contaPassageiros(){
        return passageiros;
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
                if (lugar) {
                    //assento OCUPADO
                    System.out.print("1 ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    public void preencheLugares(int qtd_desejada, String reserva) {
        int[] fa = leString(reserva);

        while (passageiros<qtd_desejada){
            //barca cheia
            if (qtd_desejada>1200){
                return;
            }

            //itera lugares
            for (int f = 1; f <=60; f++) {
                for (int a = 1; a <=20; a++) {

                    if(passageiros<qtd_desejada){
                        if (!(f ==fa[0] & a==fa[1])){
                            verificaLugar(f,a);
                        }
                    }else{
                        return;
                    }
                }
            }
        }
    }

}