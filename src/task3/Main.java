package task3;
/*У вас на руках інструкція як пройти до щитової, починаючи від того місця, де ви знаходитесь. Лабораторія просто якийсь лабіринт – весь поверх це набір комірок, з коридорами між ними.


Інструкція каже, що вам потрібно почати з поточних координат (того місця, де ви стоїте прямо зараз) і повернутись обличчям на північ. Потім слідувати наявній послідовності: або повернути ліворуч (L), або праворуч (R) на 90 градусів, потім пройти вказану кількість блоків, яка завершиться на новому перехресті.


У вас немає часу дотримуватися цієї інструкції, проходячи весь шлях ногами. Тому ви вирішуєте обчислити вашу точку призначення. Виходячи з того, що ви можете рухатися тільки «*коридорною сіткою лабораторії», яка довжина найкоротшого шляху до щитової?

Для прикладу:
 - рухаючись R2, L3 переміщає вас на 2 блоки на схід і 3 блоки на північ, або на 5 блоків далі
 - R2, R2, R2 переміщає вас на 2 блоки на південь від стартової позиції, що на 2 блоки далі
 - R5, L5, R5, R3 переміщає вас на 12 блоків далі*/
public class Main {
    public static void main(String[] args) {
        String s = "L2, L3, L3, L4, R1, R2, L3, R3, R3, L1, L3, R2, R3, L3, R4, R3, R3, L1, L4, R4, L2, R5, R1, L5, R1, R3, L5, R2, L2, R2, R1, L1, L3, L3, R4, R5, R4, L1, L189, L2, R2, L5, R5, R45, L3, R4, R77, L1, R1, R194, R2, L5, L3, L2, L1, R5, L3, L3, L5, L5, L5, R2, L1, L2, L3, R2, R5, R4, L2, R3, R5, L2, L2, R3, L3, L2, L1, L3, R5, R4, R3, R2, L1, R2, L5, R4, L5, L4, R4, L2, R5, L3, L2, R4, L1, L2, R2, R3, L2, L5, R1, R1, R3, R4, R1, R2, R4, R5, L3, L5, L3, L3, R5, R4, R1, L3, R1, L3, R3, R3, R3, L1, R3, R4, L5, L3, L1, L5, L4, R4, R1, L4, R3, R3, R5, R4, R3, R3, L1, L2, R1, L4, L4, L3, L4, L3, L5, R2, R4, L2";
        getCoordinate(s);
    }

    private static void getCoordinate(String s) {

        String[] array = s.replace(" ", "").split(",");
        int totalR = 0;
        int totalL = 0;
        int totalU = 0;
        int totalD = 0;
        int dir = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith("L") && dir == 0) {
                dir = 3;
                totalL += Integer.parseInt(array[i].substring(1));

            } else if (array[i].startsWith("L") && dir == 1) {
                dir = 0;
                totalU += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("L") && dir == 2) {
                dir = 1;
                totalR += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("L") && dir == 3) {
                dir = 2;
                totalD += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("R") && dir == 0) {
                dir = 1;
                totalR += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("R") && dir == 1) {
                dir = 2;
                totalD += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("R") && dir == 2) {
                dir = 3;
                totalL += Integer.parseInt(array[i].substring(1));
            } else if (array[i].startsWith("R") && dir == 3) {
                dir = 0;
                totalU += Integer.parseInt(array[i].substring(1));
            }
        }

        System.out.println(" r " + totalR);
        System.out.println(" l " + totalL);
        System.out.println(" u " + totalU);
        System.out.println(" d " + totalD);
        System.out.println(totalR-totalL);
        System.out.println(totalU-totalD);

    }
}
