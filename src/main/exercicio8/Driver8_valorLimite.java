import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Driver8_valorLimite {
    //valores limites
    //lotação da barca
    //[0-100]-[101-200]-[200-1000]

    @ParameterizedTest
    @CsvSource({
            //0-100
            "F01A01,   50,   3",
            "F03A18,  100,   3",
            "F11A15,  101,   2",

            //100-200
            "F41A01,   150,   3",
            "F45A08,  200,   3",
            "F30A15,  201,   3",

            //200-1199
            "F49A18,  200,   3",
            "F30A15,  1199,  3",
            //para range acima de 1199 é impossivel simular
            //"F30A15,  1201,  0"

    })
    void testaAssento(String assento, int pessoasNaBarca, int rEsp) {
        GerenciaLugares barca = new GerenciaLugares();
        barca.preencheLugares(pessoasNaBarca,assento);
        int rObs = barca.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({
            "F01A01,   50,   1",
            "F40A08,  150,   1",
            "F35A15, 1200,   1",
            "F10A18,  800,   1"
    })
    void testaAssentoOcupado(String assento, int pessoasNaBarca, int rEsp) {
        GerenciaLugares barca = new GerenciaLugares();
        barca.preencheLugares(pessoasNaBarca,"");
        int rObs = barca.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }
}
