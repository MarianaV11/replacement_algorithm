import java.util.ArrayList;

public class ClockPageReplacement {
  private class Page {
    int number;
    boolean used;

    Page(int number) {
      this.number = number;
      this.used = true; // Bit de uso inicia como verdadeiro ao ser carregada
    }
  }

  private final int frameSize;
  private final ArrayList<Page> memory;
  private int pointer;

  public ClockPageReplacement(int frameSize) {
    this.frameSize = frameSize;
    this.memory = new ArrayList<>(frameSize);
    this.pointer = 0;
  }

  public int clock(int[] pageSequence) {
    int pageFaults = 0;

    for (int pageNumber : pageSequence) {
      // Verifica se a página já está na memória
      boolean pageFound = false;
      for (Page page : memory) {
        if (page.number == pageNumber) {
          page.used = true; // Atualiza bit de uso se a página já está na memória
          pageFound = true;
          break;
        }
      }

      // Se a página não foi encontrada, ocorre uma falta de página
      if (!pageFound) {
        pageFaults++;
        // Se a memória não está cheia, adiciona a nova página diretamente
        if (memory.size() < frameSize) {
          memory.add(new Page(pageNumber));
        } else {
          // A memória está cheia, substitui usando o algoritmo Clock
          while (true) {
            Page currentPage = memory.get(pointer);
            if (currentPage.used) {
              // Dá uma segunda chance e avança o ponteiro
              currentPage.used = false;
            } else {
              // Substitui a página e interrompe o loop
              memory.set(pointer, new Page(pageNumber));
              break;
            }
            pointer = (pointer + 1) % frameSize; // Avança o ponteiro circularmente
          }
        }
      }
    }
    return pageFaults;
  }
}
