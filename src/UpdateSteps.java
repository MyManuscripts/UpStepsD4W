import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UpdateSteps extends JFrame {

    private static class UpdateStep{
        final int fromVersion;
        final int toVersion;
        final String sybaseVersion;
        final String dsnVersion;
        final boolean requiresNewSybase;

        UpdateStep (int from, int to, String sybase, String dsn, boolean requiresNew){
            this.fromVersion = from;
            this.toVersion = to;
            this.sybaseVersion = sybase;
            this.dsnVersion = dsn;
            this.requiresNewSybase = requiresNew;
        }
    }

    // ----- Полный маршрут обновления -----
    private static final List<UpdateStep> FULL_ROUTE = Arrays.asList(
            new UpdateStep(0, 1560, "Sybase 9.0", "Sybase 9.0", false),
            new UpdateStep(1560, 2718, "Sybase 9.0", "Sybase 9.0", false),
            new UpdateStep(2718, 3407, "Sybase 12", "Sybase 9.0", true),
            new UpdateStep(3407, 3856, "Sybase 12", "Sybase 12", false),
            new UpdateStep(3856, 3999, "Sybase 12", "Sybase 12", false),
            new UpdateStep(3999, 4477, "Sybase 16", "Sybase 12", true),
            new UpdateStep(4477, 4773, "Sybase 16", "Sybase 16", false),
            new UpdateStep(4773, 5603, "Sybase 17", "Sybase 16", true),
            new UpdateStep(5603, 5917, "Sybase 17", "Sybase 17", false)
    );


    // ----- Маппинг: версия → индекс первого шага в маршруте -----
    private static final Map<Integer, Integer> VERSION_TO_ROUTE_INDEX = new HashMap<>();
    private static final Set<Integer> ANCIENT_VERSIONS = Set.of(872, 875, 931, 937, 968, 987);

    static {
        int[] group0 = {1084, 1104, 1140, 1155, 1190, 1198, 1203, 1274, 1289, 1312, 1350, 1356, 1380, 1448, 1533};
        int[] group1 = {1560, 1622, 1673, 1709, 1825, 1883, 1892, 1992, 2611, 2655};
        int[] group2 = {2718, 3252, 3329};
        int[] group3 = {3407, 3561};
        int[] group4 = {3632, 3658, 3856};
        int[] group5 = {3999, 4110, 4202, 4272};
        int[] group6 = {4336, 4477, 4599, 4690};
        int[] group7 = {4773, 4842, 4904, 4965, 5029, 5118, 5199, 5271, 5313, 5393};
        int[] group8 = {5437, 5509, 5603, 5692, 5752, 5810};

        for (int v : group0) VERSION_TO_ROUTE_INDEX.put(v, 0);
        for (int v : group1) VERSION_TO_ROUTE_INDEX.put(v, 1);
        for (int v : group2) VERSION_TO_ROUTE_INDEX.put(v, 2);
        for (int v : group3) VERSION_TO_ROUTE_INDEX.put(v, 3);
        for (int v : group4) VERSION_TO_ROUTE_INDEX.put(v, 4);
        for (int v : group5) VERSION_TO_ROUTE_INDEX.put(v, 5);
        for (int v : group6) VERSION_TO_ROUTE_INDEX.put(v, 6);
        for (int v : group7) VERSION_TO_ROUTE_INDEX.put(v, 7);
        for (int v : group8) VERSION_TO_ROUTE_INDEX.put(v, 8);
    }

    private final JTextField versionField; // + final
    private final JTextArea outputArea; // + final

    public UpdateSteps() {
        setTitle("Маршрут обновления БД");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(414, 500));
        setLocationRelativeTo(null);
        setResizable(false);

        // Иконка
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/up.png"));
            if (icon.getIconWidth() > 0) {
                setIconImage(icon.getImage());
            }
        } catch (Exception ignored) {}

        // Поле ввода и кнопка
        versionField = new JTextField(4);
        versionField.setHorizontalAlignment(JTextField.RIGHT);
        JButton buildButton = new JButton("Построить маршрут");

        // Область вывода
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setLineWrap(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Панель ввода
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Введите текущую версию БД:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 0;
        inputPanel.add(versionField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buildButton, gbc);

        // Обработчик
        buildButton.addActionListener(e -> {
            outputArea.setText("");
            try {
                int x = Integer.parseInt(versionField.getText());

                if (ANCIENT_VERSIONS.contains(x)) {
                    outputArea.append(" Слишком древняя версия, обратитесь\n" +
                            " к Техническому директору.\n" +

                            "    _________\n" +
                            "   |\t     |\n" +
                            "   |___   *  |\n" +
                            "    |____    |\n" +
                            "         |   |\n" +
                            "      ___|   |_        _\n" +
                            "     | __|     |_   __||   \n" +
                            "      |  |_      |_|   | \n" +
                            "\t   |         __|\n" +
                            "\t   |      __|\n" +
                            "          _|_____|\n" +
                            "         |    |\n" +
                            "        _|   _|");
                } else if (x > 5917) {
                    outputArea.append(" Эта версия из параллельной вселенной.\n" +
                            " Попробуйте выбрать ту, что есть у нас.\n" +
                            "\n\n" +
                            "                ╭┻━━┻╮\n" +
                            "                ┃▉-▉┃ \n" +
                            "                ╰━╮╭━╯\n" +
                            "                 ╭╯╰╮\n" +
                            "                 ┃┈┈┃");
                } else {
                    Integer startIndex = VERSION_TO_ROUTE_INDEX.get(x);
                    if (startIndex != null) {
                        outputArea.append(buildRouteText(startIndex, x));
                    } else {
                        outputArea.append(" Проверьте номер сборки\n");
                    }
                }
                outputArea.setCaretPosition(0);
            } catch (NumberFormatException ex) {
                outputArea.append(" Ошибка: введите корректное число\n");
                versionField.selectAll();
                versionField.requestFocusInWindow();
            }
        });

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        getRootPane().setDefaultButton(buildButton);
    }

    private String buildRouteText(int startIndex, int currentVersion) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < FULL_ROUTE.size(); i++) {
            UpdateStep step = FULL_ROUTE.get(i);
            int from = (i == startIndex) ? currentVersion : step.fromVersion;

            sb.append(String.format(" Обновить с версии %d -> %d (%s)\n", from, step.toVersion, step.sybaseVersion));
            sb.append(String.format(" Создайте DSN на версии %s.\n", step.dsnVersion));
            if (step.requiresNewSybase) {
                sb.append(" Не забудьте установить ").append(step.sybaseVersion).append(" перед обновлением.\n");
            }
            sb.append("------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateSteps().setVisible(true));
    }

}