import java.util.HashMap;
import java.util.Map;

class NFUPageReplacement {
    private final int[] pageSequence; // Sequência de páginas a serem acessadas
    private final int frameSize; // Tamanho do frame de memória
    private final Map<Integer, Integer> frameCounter; // Contadores de uso para cada página no frame
    private final Map<Integer, Integer> pageFrames; // Mapeamento de páginas para frames

    public NFUPageReplacement(int[] pageSequence, int frameSize) {
        this.pageSequence = pageSequence;
        this.frameSize = frameSize;
        this.frameCounter = new HashMap<>();
        this.pageFrames = new HashMap<>();
    }

    public int run() {
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!pageFrames.containsKey(page)) {
                pageFaults++; // Incrementa o contador de faltas de página
            }
            accessPage(page);
            // displayFrames();
        }

        return pageFaults;
    }

    private void accessPage(int pageNumber) {
        if (pageFrames.containsKey(pageNumber)) {
            // Incrementa o contador de acessos para a página
            frameCounter.put(pageNumber, frameCounter.get(pageNumber) + 1);
            // System.out.println("Página " + pageNumber + " acessada.");
        } else {
            if (pageFrames.size() < frameSize) {
                // Se houver espaço disponível, adiciona a página no frame
                pageFrames.put(pageNumber, pageFrames.size());
                frameCounter.put(pageNumber, 1);
                // System.out.println("Página " + pageNumber + " carregada na memória.");
            } else {
                // Realiza substituição de página pelo NFU
                int pageToReplace = findLeastFrequentlyUsedPage();
                pageFrames.remove(pageToReplace);
                frameCounter.remove(pageToReplace);

                pageFrames.put(pageNumber, pageToReplace);
                frameCounter.put(pageNumber, 1);

                // System.out.println("Página " + pageToReplace + " substituída por " + pageNumber);
            }
        }
    }

    private int findLeastFrequentlyUsedPage() {
        int minAccesses = Integer.MAX_VALUE;
        int pageToReplace = -1;

        for (Map.Entry<Integer, Integer> entry : frameCounter.entrySet()) {
            int page = entry.getKey();
            int accesses = entry.getValue();
            if (accesses < minAccesses) {
                minAccesses = accesses;
                pageToReplace = page;
            }
        }
        return pageToReplace;
    }

    public void displayFrames() {
        System.out.println("Frames atuais na memória:");
        for (Map.Entry<Integer, Integer> entry : pageFrames.entrySet()) {
            int page = entry.getKey();
            System.out.println("Página: " + page + ", Contador de uso: " + frameCounter.get(page));
        }
        System.out.println();
    }
}
