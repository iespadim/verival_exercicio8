# verival_exercicio8

8) Exercício “hands-on”:

Uma barca de passageiros tem 1200 lugares organizados em 60 fileiras de 20 lugares cada. O sistema de controle de lugares deve controlar tanto a ocupação dos lugares como a distribuição de peso na barca. Desta forma quando o cliente chega para embarcar ele escolhe um lugar e o sistema deve dizer se o lugar está ocupado ou se ele não pode se sentar ali em função da distribuição de peso. As regras de distribuição de peso são as seguintes:

· Os primeiros 100 passageiros só podem se sentar nas fileiras de 1 a 20.

· Os próximos 100 passageiros só podem se sentar nas fileiras de 40 a 60.

· Os demais passageiros podem sentar-se em qualquer lugar livre.

Os lugares são identificados da seguinte forma: F<nro da fileira>A<nro do assento>. A numeração das fileiras e lugares inicia em 1.

Exemplos: F02A12, F45A01, F33A18

A classe “GerenciaLugares” tem um método chamado int verificaLugar(String assento) que pode retornar um dos seguintes valores:

· 0 – Identificador de assento inválido

· 1 – Assento ocupado

· 2 – Assento bloqueado devido a distribuição de peso

· 3 – Ok, assento atribuído ao passageiro.

Gere um conjunto de casos de teste baseados em particionamento e uma classe driver para testar o método “verificaLugar”.

9) Exercício “hands-on”:

Especificação do problema: Um determinado site web deve armazenar o ranking dos 10 melhores jogadores de um determinado game (ordenados por ordem decrescente de pontuação). Cada vez que uma partida oficial ocorre, o administrador do site entra com o nome do jogador e sua pontuação. Se houverem menos de 10 jogadores cadastrados o jogador será inserido no ranking independente de seu score. Na medida em que já houver 10 jogadores cadastrados, novos jogadores só entram se tiverem score maior que o último colocado e sempre que um novo jogador entrar no ranking, o jogador com pontuação mais baixa é eliminado. Os registros devem ser mantidos ordenados em ordem decrescente de pontuação

Esqueleto das classes: para atender a especificação, um desenvolvedor criou as classes “Record” e “Ranking” cujo esqueleto pode ser visto na sequência.

public class Record { public Record(String name, int score) {} public String getName() {} public int getScore() {} public String toString() {} }

public class Ranking {

public Ranking() {}

// Insere novo registro na lista mantendo a ordenação // Retorna true se a inserção foi possível public boolean add(Record record) {} // Retorna à quantidade de registros armazenados public int numRecords() {} // Retorna o i-ésimo registro armazenado ou // null se o valor de i for inválido public Record getScore(int i) {} // Retorna o pior score armazenado // Retorna null se a lista estiver vazia public Record worstScore() {} // Retorna o melhor score armazenado // Retorna null se a lista estiver vazia public Record bestScore() {} }

Tarefas:

a. Gerar um conjunto de casos de teste para a classe “Ranking” utilizando a técnica de particionamento (gere um conjunto para cada método)

b. Usando o JUnit, implementar um driver de teste que exercite a classe “Ranking” com os casos de teste definidos na letra “a”.

c. Solicitar para o professor a implementação da classe “Ranking”, aplicar o driver de teste sobre a mesma e relatar os defeitos encontrados (se houverem).