# Bankrupt
Análise de perfis de jogadores em jogo semelhante a **Banco Imobiliário I**

## Pré-requisitos

[IntelliJ IDEA](https://www.jetbrains.com/help/idea/installation-guide.html)
 
Java 8

SDK 1.8

## Nomenclatura

<ul>
   <li> <b>Turno:</b> Vez de cada jogador </li> 
   <li><b>Rodada:</b> Composta pelo conjunto de turnos de cada jogador</li> 
   <li><b>Partida:</b> Composta por todas as rodadas até haver um vencedor</li> 
</ul>

## Como executar

1. Extrair a pasta Bankrupt.zip ou clonar o projeto em <https://github.com/DebAmorim/Bankrupt>;
2. Na IDE IntelliJ basta clicar em executar.

O resultado das simulações será exibido no console.


## Parâmetros da Simulação

Alterações de parâmetros da simulação podem ser feitas no início da classe Main.

<ul>
   <li> <b>Número de partidas:</b> A quantidade de partidas para análise estatística. </li> 
   <li><b>Número Máximo de Rodadas:</b> Quantidade de rodadas que determinam se a partida se encerra por time out.</li> 
   <li><b>Arquivo de Configuração:</b> Arquivo com valores de venda e aluguel de cada propriedade.</li> 
   <li><b>Propriedades Sorteadas:</b> Se marcado como verdadeiro, a ordem das propriedades será sorteada. Se falso, a ordem será a mesma do arquivo de configuração. A mudança deste parâmetro influencia no perfil que mais vence.</li> 
</ul>

