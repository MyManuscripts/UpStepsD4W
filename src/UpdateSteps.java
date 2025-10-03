import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static javax.swing.SwingUtilities.getRootPane;

public class UpdateSteps extends JFrame {
    private static final Set<Integer> GROUP_TO_1560 = Set.of(1084, 1104, 1140, 1155, 1190, 1198, 1203, 1274, 1289,
            1312, 1350, 1356, 1380, 1448, 1533);
    private static final Set<Integer>GROUP_TO_2718 = Set.of(1560, 1622, 1673, 1709, 1825, 1883, 1892, 1992, 2611, 2655);
    private static final Set<Integer> GROUP_TO_3407 = Set.of(2718, 3252, 3329);
    private static final Set<Integer> GROUP_TO_3856 = Set.of(3407, 3561);
    private static final Set<Integer> GROUP_TO_3999 = Set.of(3632, 3658, 3856);
    private static final Set<Integer> GROUP_TO_4477 = Set.of(3999, 4110, 4202, 4272);
    private static final Set<Integer> GROUP_TO_4773 = Set.of(4336, 4477, 4599, 4690);

    private static final Set<Integer> GROUP_TO_5437 = Set.of(4773, 4842, 4904, 4965, 5029,
            5118, 5199, 5271, 5313, 5393, 5437);
    private static final Set<Integer> GROUP_TO_5752 = Set.of(5509, 5603, 5692, 5752);

    private JTextField versionField;
                                    // JTextField - из бибилиотеки Swing, представляет собой однострочное
                                    // текстовое поле, в которое пользователь может вводить текст
                                    // versionField - имя переменной
    private JTextArea outputArea; // компонент Swing, предназначенный для отображения
                                  // и (опционально) редактирования многострочного текста.

    public UpdateSteps(){

        setTitle("Маршрут обновления БД");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setDefaultCloseOperation(...) — это метод класса JFrame, который устанавливает поведение при закрытии окна.
        // JFrame.EXIT_ON_CLOSE — это одна из возможных констант, означающая:
        // Когда это окно закрывается — завершить всю программу (вызвать System.exit(0))
        setSize(400, 400);

        setLocationRelativeTo(null);
        setResizable(false);


        // Создаём компоненты
        versionField = new JTextField(4); // 4 символа
        versionField.setHorizontalAlignment(JTextField.RIGHT);
        JButton buildButton = new JButton("Построить маршрут");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Панель для ввода — используем GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Устанавливаем отступы


        // Добавляем метку
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Введите текущую версию БД:"), gbc);

        // Добавляем поле ввода
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0; // Не растягивать!
        inputPanel.add(versionField, gbc);

        // Добавляем кнопку
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Занимает две колонки
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buildButton, gbc);

        // Добавляем прокручиваемую область с outputArea
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Устанавливаем layout и добавляем компоненты
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);  // Ввод вверху
        add(scrollPane, BorderLayout.CENTER);  // Вывод в центре


        // Обработчик кнопки
        buildButton.addActionListener(e -> buildRoute());
        getRootPane().setDefaultButton(buildButton);


        /*Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер сборки: ");
        int x = sc.nextInt();
        sc.close();

        buildRoute(x); */
    }


        private void buildRoute() {

            try {
                int x = Integer.parseInt(versionField.getText());
                outputArea.setText("");

                if (GROUP_TO_4773.contains(x)) {
                    outputArea.append("Обновить с версии " + x + " -> 4773 (Sybase 16)\n" +
                            "Создайте DSN на версии Sybase 16.\n" +
                            "--------------------------------------------------\n" +
                            "Обновить с версии 4773 -> 5603 (Sybase 17)\n" +
                            "Создайте DSN на версии Sybase 16.\n" +
                            "Не забудьте установить Sybase 17 перед обновлением.\n" +
                            "--------------------------------------------------");
                } else if (GROUP_TO_5437.contains(x)) {
                    outputArea.append("Обновить с версии " + x + " -> 5603 (Sybase 17)\n" +
                            "Создайте DSN на версии Sybase 17.\n" +
                            "---------------------------------------------------\n" +
                            "Обновить с версии 5603 -> 5810 (Sybase 17)\n" +
                            "--------------------------------------------------");
                } else if (GROUP_TO_5752.contains(x)) {
                    outputArea.append("Обновить с версии " + x + " -> 5810 (Sybase 17)");
                } else if (x == 872 || x == 875 || x == 931 || x == 937 || x == 968 || x == 987) {
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
                            "           |_____|\n" +
                            "         |    |\n" +
                            "        _|   _|");
                } else if (x > 5917) {
                    outputArea.append(" Эта версия из параллельной вселенной.\n" +
                            " Попробуйте выбрать ту, что есть у нас.\n" +
                            "                                       \n" +
                            "                                       \n" +
                            "                ╭┻━━┻╮\n" +
                            "                ┃▉┈▉┃ \n" +
                            "                ╰━╮╭━╯\n" +
                            "                 ╭╯╰╮\n" +
                            "                 ┃┈┈┃");

                } else {
                    outputArea.append(" Проверьте номер сборки\n");
                }


            } catch (NumberFormatException ex) {
                outputArea.append(" Ошибка: введите корректное число\n");
            }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UpdateSteps().setVisible(true);
        });
    }






}