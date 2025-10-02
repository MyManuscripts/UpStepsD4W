import java.util.Scanner;
import java.util.Set;

public class UpdateSteps {
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


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер сборки: ");
        int x = sc.nextInt();
        sc.close();

        buildRoute(x);
    }

    public static void buildRoute(int x){
        if (GROUP_TO_4773.contains(x)){
            System.out.println("Обновить с версии " + x + " -> 4773 (Sybase 16)\n" +
                    "Создайте DSN на версии Sybase 16.\n" +
                    "--------------------------------------------------\n" +
                    "Обновить с версии 4773 -> 5603 (Sybase 17)\n" +
                    "Создайте DSN на версии Sybase 16.\n" +
                    "Не забудьте установить Sybase 17 перед обновлением.\n" +
                    "--------------------------------------------------");
        } else if (GROUP_TO_5437.contains(x)) {
            System.out.println("Обновить с версии " + x + " -> 5603 (Sybase 17)\n" +
                    "Создайте DSN на версии Sybase 17.\n" +
                    "---------------------------------------------------\n" +
                    "Обновить с версии 5603 -> 5810 (Sybase 17)\n" +
                    "--------------------------------------------------");
        } else if (GROUP_TO_5752.contains(x)) {
            System.out.println("Обновить с версии " +x+ " -> 5810 (Sybase 17)");
            } else if (x==872 || x==875 || x==931 || x==937 || x==968 || x==987) {
                System.out.println(" Слишком древняя версия, обратитесь\n" +
                        " к Техническому директору.\n" +

                        "    _________\n" +
                        "   |\t     |\n" +
                        "   |___   *  |\n" +
                        "    |____    |\n" +
                        "         |   |\n" +
                        "      ___|   |_        _\n" +
                        "     | __|     |_   __||   \n" +
                        "      |  |_      |_|   | \n" +
                        "\t       |         __|\n" +
                        "\t       |      __|\n" +
                        "           |_____|\n" +
                        "         |    |\n" +
                        "        _|   _|");
            } else if (x>5917) {
                System.out.println(" Эта версия из параллельной вселенной.\n" +
                        " Попробуйте выбрать ту, что есть у нас.\n"+
                        "                                       \n"+
                        "                                       \n"+


                        "                ╭┻━━┻╮\n" +
                        "                ┃▉-┈▉┃ \n" +
                        "                ╰━╮╭━╯\n" +
                        "                 ╭╯╰╮\n" +
                        "                 ┃┈┈┃");
            } else {
                System.out.println(" Проверьте номер сборки\n");
            }



    }

}