import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Driver8 {
    //partições da regra de negocio
    //a1 local livre b         | a2 local ocupado,           | a3 local invalido
    //b1 lotacao menor que 100 | b2 lotacao entre 101 e 200  | b3 lotacao maior que 200

    //casos de teste
    //a1 b1 | a1 b2 | a1 b3
    //a2 b1 | a2 b2 | a1 b3
    //a3 b1 | a3 b2 | a1 b3


    @ParameterizedTest
    @CsvSource({
            "F62A01",
            "F32A21",
            "F2A21",
            "F02A1",
            "Z02A19",
            "F03X19"
    })
    void testaAssentoInvalido(String assento) {
        GerenciaLugares barca = new GerenciaLugares();
        int rEsp = 0;
        int rObs = barca.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }


    @ParameterizedTest
    @CsvSource({
            "F01A01,   50,   3",
            "F40A08,  150,   3",
            "F35A15,  250,   3",
            "F10A18,    0,   3",
            "F42A10,  120,   3",
            "F35A01,  660,   3",
            "F36A17,    0,   2",
            "F47A16,    0,   2",
            "F37A15,  120,   2"
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
