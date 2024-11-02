import java.util.ArrayList;
import java.util.Random;

public class NRUPageReplacement {
  private class Page {
    int number;
    boolean used;
    boolean modified;

    Page(int number) {
      this.number = number;
      this.used = false;
      this.modified = false;
    }
  }

  private final int frameSize;
  private final ArrayList<Page> memory;
  private final Random random;

  public NRUPageReplacement(int frameSize) {
    this.frameSize = frameSize;
    this.memory = new ArrayList<>(frameSize);
    this.random = new Random();
  }

  public int nru(int[] pageSequence) {
    int pageFaults = 0;

    for (int pageNumber : pageSequence) {
      boolean pageFound = false;

      // Verifica se a página já está na memória
      for (Page page : memory) {
        if (page.number == pageNumber) {
          page.used = true; // Atualiza bit de uso
          pageFound = true;
          break;
        }
      }

      if (!pageFound) {
        pageFaults++;

        if (memory.size() < frameSize) {
          // Se há espaço livre na memória, adiciona a nova página
          memory.add(new Page(pageNumber));
        } else {
          // Substitui uma página usando a classificação do algoritmo NRU
          int indexToReplace = selectPageToReplace();
          memory.set(indexToReplace, new Page(pageNumber));
        }
      }

      // Simulação de modificação aleatória de página (opcional)
      if (!memory.isEmpty()) {
        int modifiedPageIndex = random.nextInt(memory.size());
        memory.get(modifiedPageIndex).modified = random.nextBoolean();
      }

      // Resetar o bit de uso periodicamente (simulação)
      resetUsageBits();
    }
    return pageFaults;
  }

  private int selectPageToReplace() {
    ArrayList<Integer> candidates = new ArrayList<>();

    // Tenta substituir na ordem das classes (Classe 0, depois Classe 1, e assim por
    // diante)
    for (int classType = 0; classType <= 3; classType++) {
      candidates.clear();

      for (int i = 0; i < memory.size(); i++) {
        Page page = memory.get(i);

        if (getPageClass(page) == classType) {
          candidates.add(i);
        }
      }

      if (!candidates.isEmpty()) {
        break; // Encontra uma página de uma classe específica para substituir
      }
    }
    return candidates.get(random.nextInt(candidates.size()));
  }

  private int getPageClass(Page page) {
    if (!page.used && !page.modified) {
      return 0;
    } else if (!page.used && page.modified) {
      return 1;
    } else if (page.used && !page.modified) {
      return 2;
    } else {
      return 3;
    }
  }

  // Método para simular o reset dos bits de uso periodicamente
  private void resetUsageBits() {
    for (Page page : memory) {
      page.used = false; // Limpa o bit de uso para simular a periodicidade do NRU
    }
  }
}
