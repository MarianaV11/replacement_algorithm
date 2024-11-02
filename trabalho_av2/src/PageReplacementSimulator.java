import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PageReplacementSimulator {

    // Armazena a sequência de páginas
    private int[] pageSequence;
    private int frameSize;

    public PageReplacementSimulator(int[] pageSequence, int frameSize) {
        this.pageSequence = pageSequence;
        this.frameSize = frameSize;
    }

    // Método FIFO
    public int fifo() {
        Set<Integer> pages = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!pages.contains(page)) {
                if (queue.size() == frameSize) {
                    int oldestPage = queue.poll();
                    pages.remove(oldestPage);
                }
                pages.add(page);
                queue.add(page);
                pageFaults++;
            }
        }
        return pageFaults;
    }

    // Método LRU
    public int lru() {
        LinkedList<Integer> memory = new LinkedList<>();
        int pageFaults = 0;
    
        for (int page : pageSequence) {
            // Se a página não está na memória, ocorre uma falta de página
            if (!memory.contains(page)) {
                // Se a memória está cheia, remove a página menos recentemente usada (primeira da lista)
                if (memory.size() == frameSize) {
                    memory.removeFirst();
                }
                // Adiciona a nova página ao final da lista
                memory.addLast(page);
                pageFaults++;  // Incrementa o número de faltas de página
            } else {
                // Se a página já está na memória, mova-a para o final (como a mais recentemente usada)
                memory.remove((Integer) page);
                memory.addLast(page);
            }
        }
        return pageFaults;
    }    

    // Método do Relógio
    public int clock() {
        // Inicializando o simulador de substituição de páginas com o algoritmo Clock
        ClockPageReplacement simulator = new ClockPageReplacement(frameSize);

        // Executando o algoritmo e obtendo o número de faltas de página
        int pageFaults = simulator.clock(pageSequence);
        return pageFaults;
    }

    // Método NFU
    public int nfu() {
        // Inicializando o simulador de substituição de páginas com o algoritmo NFU
        NFUPageReplacement nfu = new NFUPageReplacement(pageSequence, frameSize);

        // Executando o algoritmo NFU e obtendo o número de faltas de página
        int pageFaults = nfu.run();
        return pageFaults;
    }

}
