# Page Replacement Simulator
Este projeto simula algoritmos de substituição de páginas em memória, incluindo FIFO, LRU, Clock e NFU. Ele permite que os usuários insiram uma sequência de páginas e um tamanho de quadro para analisar o desempenho de cada algoritmo, mostrando o número de faltas de página ocorridas.

## Pré-requisitos
Java JDK 8 ou superior
Intellij IDEA (Community)
SWING

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
### App: Classe que permite a execução da simulação a partir do terminal, exibindo os resultados para cada algoritmo.
Exemplo de execução:

public static void main(String[] args) {
    int[] pages = new int[]{1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
    int frameSize = 3;
    PageReplacementSimulator simulator = new PageReplacementSimulator(pages, frameSize);
    System.out.println("FIFO: " + simulator.fifo() + " faltas de página");
    System.out.println("LRU: " + simulator.lru() + " faltas de página");
    System.out.println("Relógio: " + simulator.clock() + " faltas de página");
    System.out.println("NFU: " + simulator.nfu() + " faltas de página");
}

### AppGUI: Interface gráfica que permite a inserção interativa da sequência de páginas e do tamanho do quadro, além de exibir os resultados em um gráfico de barras.

Funcionalidades:
  Campos para inserir o tamanho do quadro e a sequência de páginas.
  Botão Calcular para executar os algoritmos e exibir as faltas de página na área de resultados.
  Gráfico de barras comparativo exibindo as faltas de página para cada algoritmo.
#### Como Executar
  Execute a classe App
  
  java AppGUI - Para executar a GUI com os gráficos;
  java App - Para executar o código e mostrar os resultados apenas via terminal;


