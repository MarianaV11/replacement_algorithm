import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AppGUI extends JFrame {
  private JTextField frameSizeField;
  private JTextField pagesField;
  private JTextArea resultsArea;
  private JPanel chartPanel;

  // Variável de instância para o simulador
  private PageReplacementSimulator simulator;

  public AppGUI() {
    setTitle("Simulador de Substituição de Páginas");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1400, 800);
    setLayout(new BorderLayout());

    // Painel de entrada
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(3, 2));

    inputPanel.add(new JLabel("Tamanho do Quadro:"));
    frameSizeField = new JTextField();
    inputPanel.add(frameSizeField);

    inputPanel.add(new JLabel("Sequência de Páginas (ex: 1,2,3,4):"));
    pagesField = new JTextField();
    inputPanel.add(pagesField);

    JButton calculateButton = new JButton("Calcular");
    inputPanel.add(calculateButton);

    add(inputPanel, BorderLayout.NORTH);

    // Área de Resultados
    resultsArea = new JTextArea();
    resultsArea.setEditable(false);
    add(new JScrollPane(resultsArea), BorderLayout.CENTER);

    // Painel para gráfico
    chartPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
      }
    };
    chartPanel.setPreferredSize(new Dimension(500, 200));
    add(chartPanel, BorderLayout.SOUTH);

    // Listener para calcular
    calculateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        calculatePageFaults();
      }
    });
  }

  private void calculatePageFaults() {
    try {
      int frameSize = Integer.parseInt(frameSizeField.getText());
      String[] pageStrings = pagesField.getText().split(",");
      int[] pages = new int[pageStrings.length];
      for (int i = 0; i < pageStrings.length; i++) {
        pages[i] = Integer.parseInt(pageStrings[i].trim());
      }

      simulator = new PageReplacementSimulator(pages, frameSize);
      int fifoFaults = simulator.fifo();
      int lruFaults = simulator.lru();
      int clockFaults = simulator.clock();
      int nfuFaults = simulator.nfu();

      resultsArea.setText("Resultados:\n");
      resultsArea.append("FIFO: " + fifoFaults + " faltas de página\n");
      resultsArea.append("LRU: " + lruFaults + " faltas de página\n");
      resultsArea.append("Relógio: " + clockFaults + " faltas de página\n");
      resultsArea.append("NFU: " + nfuFaults + " faltas de página\n");

      // Redesenhar o gráfico com novos dados
      chartPanel.repaint();
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void drawChart(Graphics g) {
    // Verificar se o simulator está inicializado
    if (simulator == null) {
      return;  // Sai do método se simulator for null
    }

    int[] faults = {
            simulator.fifo(),
            simulator.lru(),
            simulator.clock(),
            simulator.nfu()
    };

    String[] labels = { "FIFO", "LRU", "Relógio", "NFU" };
    int maxFaults = Arrays.stream(faults).max().orElse(1);

    int width = chartPanel.getWidth();
    int height = chartPanel.getHeight();
    int barWidth = width / faults.length;
    int barMaxHeight = height - 20;

    for (int i = 0; i < faults.length; i++) {
      int barHeight = (faults[i] * barMaxHeight) / maxFaults;
      g.setColor(Color.BLUE);
      g.fillRect(i * barWidth, height - barHeight, barWidth - 10, barHeight);
      g.setColor(Color.BLACK);
      g.drawString(labels[i], i * barWidth + (barWidth / 4), height - 5);
      g.drawString(String.valueOf(faults[i]), i * barWidth + (barWidth / 4), height - barHeight - 5);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new AppGUI().setVisible(true);
    });
  }
}
