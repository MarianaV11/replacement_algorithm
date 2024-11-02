public class App {
    public static void main(String[] args) {
        // Exemplo de uso
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5, 1, 1, 2, 2, 1}; // Exemplo de sequência
        int frameSize = 3; // Exemplo de tamanho do quadro

        PageReplacementSimulator simulator = new PageReplacementSimulator(pages, frameSize);
        System.out.println("FIFO: " + simulator.fifo() + " faltas de página");
        System.out.println("LRU: " + simulator.lru() + " faltas de página");
        System.out.println("Relógio: " + simulator.clock() + " faltas de página");
        System.out.println("NFU: " + simulator.nfu() + " faltas de página");
    }
}
