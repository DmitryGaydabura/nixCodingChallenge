package task1;

/*Завдання 1

На стоянці бази відпочинку, де ви провели ніч, стоїть неймовірна Tesla Model S. Щоб потрапити в авто вам потрібно ввести пароль. Ви знаходите під лобовим склом листок і якісь дивні написи на ньому. Придивившись, виявляється, що власник авто залишив собі підказки, за якими можна відновити пароль:

 - у розрахунок беремо лише 6-значні комбінації
 - комбінації входять до діапазону чисел, які будуть дані у вихідних даних для завдання
 - дві сусідні цифри в комбінації можуть бути однаковими (наприклад 22 в 122345)
 - ліворуч цифри ніколи не зменшуються, вони або збільшуються, або залишаються тими ж (наприклад 11123 або 135679)

Враховуючи правила, зазначені вище, наступне є вірним:

- 111111 задовольняє вимогам (подвоєння 11, ніколи не зменшується)
- 223450 не відповідає вимогам (зменшується пара цифр 50)
- 123789 не відповідає вимогам (немає подвійних цифр)

Паролем є кількість комбінацій чисел, що задовольняють правилам в діапазоні 123117-733395.*/

public class Main {

    public static void main(String[] args) {
        System.out.println(getNumber(123117,733395));
    }
    public static int getNumber(int a , int b ){
        int answer = 0;

        for (int i = a; i < b; i++) {
            boolean isGood = true;
            int[] array = new int[6];
            String is = "" + i;
            String[] arrayString = new String[6];
            for (int j = 1; j < 7; j++) {
                arrayString[j-1] = is.substring(j-1,j);
                array[j-1] = Integer.parseInt(arrayString[j-1]);
            }

            for (int j = 1; j < 6; j++) {
                if(array[j-1] > array[j]){
                    isGood = false;
                    break;
                }
            }
            boolean hasDouble = false;
            for (int j = 1; j < 6; j++) {
                if (array[j - 1] == array[j]) {
                    hasDouble = true;
                    break;
                }
            }
            if(!hasDouble){
                isGood = false;
            }

            if(isGood){
                answer++;
            }

        }
        return answer;
    }
}
