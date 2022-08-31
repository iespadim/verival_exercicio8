public class Main {
    GerenciaLugares bilheteria;

    public static void main(String args[]) {
        GerenciaLugares bilheteria = new GerenciaLugares();

        System.out.println( bilheteria.verificaLugar("F03A19"));
        System.out.println( bilheteria.verificaLugar("F21A19"));
        bilheteria.passageiros=100;
        System.out.println( bilheteria.verificaLugar("F41A19"));


        bilheteria.desenhaBarca();
    }
}