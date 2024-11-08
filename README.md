# Page Replacement Simulator
Este projeto simula algoritmos de substituição de páginas em memória, incluindo FIFO, LRU, Clock e NFU. Ele permite que os usuários insiram uma sequência de páginas e um tamanho de quadro para analisar o desempenho de cada algoritmo, mostrando o número de faltas de página ocorridas.

## Pré-requisitos
Java JDK 8 ou superior
Intellij IDEA (Community)
SWING

## Utilizadas
openjdk 23 2024-09-17
OpenJDK Runtime Environment (build 23)
OpenJDK 64-Bit Server VM (build 23, mixed mode, sharing)

## Estrutura do Projeto
### PageReplacementSimulator: Classe principal que implementa os algoritmos de substituição de páginas.
Construtor: PageReplacementSimulator(int[] pageSequence, int frameSize)
pageSequence: Array de inteiros representando a sequência de páginas a serem acessadas.
frameSize: Número de quadros disponíveis na memória.
Métodos:
  fifo(): Executa o algoritmo de substituição de página FIFO.
  lru(): Executa o algoritmo de substituição de página LRU.
  clock(): Executa o algoritmo de substituição de página Clock.
  nfu(): Executa o algoritmo de substituição de página NFU.

### AppGUI: Interface gráfica que permite a inserção interativa da sequência de páginas e do tamanho do quadro, além de exibir os resultados em um gráfico de barras.
Exemplo de execução:

![image](https://github.com/user-attachments/assets/fdbb6834-1dda-4e03-8e44-d42787f12c85)

Funcionalidades:
  Campos para inserir o tamanho do quadro e a sequência de páginas.
  Botão Calcular para executar os algoritmos e exibir as faltas de página na área de resultados.
  Gráfico de barras comparativo exibindo as faltas de página para cada algoritmo.
#### Como Executar
  Execute a classe App
  
  java AppGUI - Para executar a GUI com os gráficos;
  java App - Para executar o código e mostrar os resultados apenas via terminal;


