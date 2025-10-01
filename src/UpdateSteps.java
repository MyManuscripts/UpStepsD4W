import java.util.Scanner;
import java.util.Set;

public class Main {

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
        } else {
            System.out.println("Проверьте номер сборки");
        }


    }
}