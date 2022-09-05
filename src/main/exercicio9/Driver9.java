import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

public class Driver9 {
    //partições da regra de negocio
    //a1 records vazio                     | a2 records cheio                       | a3 records populado
    //b1 foi possivel inserir              | b2 nao foi possivel inserir
    //c1 obtem record em posição existente | c2 obtem record em posição inexistente
    //d1 obtem o pior record               | d2 nao ha record
    //e1 obtem o melhor record             | e2 nao ha record

    @ParameterizedTest
    @CsvSource({
            //nome, score, valor esperado, csv ranking (pessoa1,score1,pessoaN,scoreN,...)
            //a1 b1
            "Joao da Silva, 1, true,''",
            //a1 b2
            "Joao da Silva, -1, false,''",
            //a2b1 - a classe define no maximo 20 entradas no ranking, diferentemente do enunciado que define 10.
            "maria, 5, false,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'",
            //a2b2 - a classe define no maximo 20 entradas no ranking, diferentemente do enunciado que define 10.
            "maria, 2, false,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'",
            //a3b1
            "Joao da Silva, 5, true,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10'",
            //a3b2
            "pessoa 6, -1, false,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8'"
    })
    void testaAdd(String nome, int score, boolean rEsp, @ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);
        Record r = new Record(nome, score);

        boolean rObs = ranking.add(r);

        Assertions.assertEquals(rEsp, rObs);
    }



    //a1 records vazio                     | a2 records cheio                       | a3 records populado
    @ParameterizedTest
    @CsvSource({
            //a1
            "0, ''",
            //a2
            "10,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'",
            //a3
            "5, 'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9'"
    })
    void testaNumRecords(int countExpected, @ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);

        int rObs = ranking.numRecords();
        int rEsp = countExpected;

        Assertions.assertEquals(rEsp, rObs);
    }



    //a1 records vazio                     | a2 records cheio                       | a3 records populado
    //c1 obtem record em posição existente | c2 obtem record em posição inexistente
    //a1c1 a1c2 a2c1 a2c2 a3c1 a3c2
    @ParameterizedTest
    @CsvSource({
            //a2c2
            "4,pessoa 5, 9,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'"
    })
    void testaGetScore(int index,String expectedName,int expectedScore,@ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);

        Record expectedRecord = new Record(expectedName, expectedScore);
        Record foundRecord = ranking.getScore(index);


        Assertions.assertEquals(expectedRecord.getName(), foundRecord.getName());
        Assertions.assertEquals(expectedRecord.getScore(), foundRecord.getScore());
    }

    //a1 records vazio                     | a2 records cheio                       | a3 records populado
    //c1 obtem record em posição existente | c2 obtem record em posição inexistente
    //a1c1 a1c2 a2c1 a2c2 a3c1 a3c2
    @ParameterizedTest
    @CsvSource({
            //a3c2
            "7,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9'",
            "-1,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9'",
            "17,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9,pessoa 6, 10,pessoa 7, 10, pessoa 8, 10, pessoa 9, 9, pessoa 10, 9'"
    })
    void testaGetScoreFail(int index,@ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);

        Assertions.assertNull(ranking.getScore(index));
    }

    //partições da regra de negocio
    //a1 records vazio                     | a2 records cheio                       | a3 records populado
    //d1 obtem o pior record               | d2 nao ha record
    @ParameterizedTest
    @CsvSource({
            //a1
            ",,''",
            //a2d1
            "pessoa 10,8,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'",
            //a3d1
            "pessoa 5,9, 'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9'"
    })
    void testaWorstScore(String expectedName,int expectedScore,@ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);

        Record expectedRecord = new Record(expectedName, expectedScore);
        Record foundRecord = ranking.worstScore();

        Assertions.assertEquals(expectedRecord.getName(), foundRecord.getName());
        Assertions.assertEquals(expectedRecord.getScore(), foundRecord.getScore());
    }


    @Test
    void testaWorstScoreVazio() {
        Ranking ranking = new Ranking();

        Assertions.assertNull(ranking.worstScore());
    }


    @ParameterizedTest
    @CsvSource({
            "pessoa 1,10,'pessoa 1, 10,pessoa 2, 10, pessoa 3, 10, pessoa 4, 9, pessoa 5, 9, pessoa 6, 9, pessoa 7, 8, pessoa 8, 8, pessoa 9, 8, pessoa 10, 8'",
            "pessoa 5,7, 'pessoa 5, 7,pessoa 2, 5, pessoa 3, 4, pessoa 4, 4, pessoa 5, 3'"
    })
    void testaBestScore(String expectedName,int expectedScore,@ConvertWith(StringArrayConverter.class) String[] scores) {
        Ranking ranking = new Ranking();
        preencheRanking(scores, ranking);

        Record expectedRecord = new Record(expectedName, expectedScore);
        Record foundRecord = ranking.bestScore();

        Assertions.assertEquals(expectedRecord.getName(), foundRecord.getName());
        Assertions.assertEquals(expectedRecord.getScore(), foundRecord.getScore());
    }


    @Test
    void testaBestScoreVazio() {
        Ranking ranking = new Ranking();

        Assertions.assertNull(ranking.bestScore());
    }

    void preencheRanking(String[] scores, Ranking ranking) {
        if (scores.length > 1) {
            for (int i = 0; i < scores.length; i = i + 2) {
                ranking.add(new Record(scores[i], Integer.parseInt(scores[i + 1])));
            }
        }
    }
}
