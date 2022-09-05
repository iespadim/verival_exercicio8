#Verificação e validação de software
- Esta entrega trata da criação de testes para os exercicios 8 e 9 da lista sobre testes baseados em especificação.

## exercício 8 - barcas e passageiros
para este exercicio foi pedido testes em partições e testes de valor limite.

###aplicando a técnica de partições
partições quanto ao local escolhido:

    a1 - local livre
    a2 - local ocupado
    a3 - local invalido

partições quanto a lotação da barca:

    b1 - lotacao menor que 100 
    b2 - lotacao entre 101 e 200
    b3 - lotacao maior que 200 e menor que 1200
    b4 - lotação maior que 1200

casos de teste levantados:

    a1 b1 | a1 b2 | a1 b3 | a1 b4
    a2 b1 | a2 b2 | a2 b3 | a2 b4
    a3 b1 | a3 b2 | a3 b3 | a3 b4

###aplicando a técnica de valor limite
para esta tecnica foram encontrados os seguintes intervalos

intervalo de passageiros na barca: 0-100

    in point: 50
    on point: 100
    off point: 101

intervalo de passageiros na barca: 101-200

    in point: 105
    on point: 101,200
    off point: 201,100

intervalo de passageiros na barca: 201-1199

    in point: 345
    on point: 201,1199
    off point: 200,1200

## exercício 9 - Ranking e Scores
###aplicando a técnica de partições

### algumas observações:
 através da execução dos casos de teste foi possivel observar que ter um contador para a quantidade de scores guardados acarretou em uma exception nos métodos de obter o melhor e o pior score quando não ha nenhum score guardado no ranking

    Ranking.java:58: 
    java.lang.ArrayIndexOutOfBoundsException: -1
	at Ranking.worstScore(Ranking.java:58)


partições quanto ao ranking

    a1 records vazio
    a2 records cheio
    a3 records populado

partiçoes quanto ao método inserir record

    b1 - foi possivel inserir
    b2 - nao foi possivel inserir

partições quanto ao método obter enésimo record 

    c1 - obtem record em posição existente
    c2 - obtem record em posição inexistente

partições quanto aos métodos obter pior e melhor record

    d1 - obtem o pior record   
    d2 - nao ha record
    e1 - obtem o melhor record