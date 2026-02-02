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

                if (UpdateConfiguration.ANCIENT_VERSIONS.contains(x)) {
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
                    Integer startIndex = UpdateConfiguration.VERSION_TO_ROUTE_INDEX.get(x);
                    if (startIndex != null) {
                        outputArea.append(UpdateService.buildRouteText(startIndex, x));
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateSteps().setVisible(true));
    }

}