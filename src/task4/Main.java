package task4;
/*Комп'ютер запущений і готовий до обчислень. Єдине, чого вам не вистачає, щоб провести аналіз вірусу та створити вакцину, це завантажити в нього зразки вірусу та антитіл.

Дотримуючись вказівок, тепер необхідно зламати сейф з біоматеріалами, який знаходиться недалеко від комп'ютера.

Ви підійшли і бачите, що на дверцятах сейфа немає звичного кодового замка з клавіатурою або хоча б замкової щілини. Натомість хитрі інженери залишили головоломку у вигляді секції з 16 комірок (4 на 4) із вбудованою ручкою, яка може рухатися між осередками.

Інструкція свідчить, що ручка не завжди може вільно переміщатися від однієї комірки до іншої. Її рух може бути заблокований в одну або кілька сторін, причому те, які напрями блокуються, залежить від усіх попередніх переміщень. Тобто це свого роду мініатюрний лабіринт, в якому стіни завжди виникають, то пропадають на вашому шляху.

Фактично у вас є одна спроба провести ручку від лівого верхнього кута в правий нижній. Щоб не зайти в глухий кут вам потрібно заздалегідь прорахувати шлях, який має пройти ручка. На щастя, вам відомий алгоритм, згідно з яким замок блокує та розблокує перегородки між комірками.

Цей алгоритм заснований на застосуванні функції MD5 до секретного ключа історії переміщення ручки. Це працює таким чином:

1. У вас є ключ (припустимо, iddqd).
2. Ви застосовуєте до нього функцію MD5, отримуючи хеш (в даному випадку 73bcaaa458bff0d27989ed331b68b64d).
3. Далі ви повинні взяти чотири перші символи хешу (у прикладі це 73bc), які будуть відповідати, по порядку, верхній, нижній, лівій та правій перегородці.
4. Символи b, c, d, e, f означають, що дана стінка відкрита, всі інші значення (тобто 0-9, a) означають, що рух в даному напрямку заблоковано (таким чином 73bc відповідає стану закрито-закрито-відкрито-відкрито для верхньої, нижньої, лівої та правої межі відповідно, тобто можна рухати ручку тільки вліво та вправо), а також не можна виходити за межі поля (у нашому випадку рух вліво заблокований рамками самого замку, тому можна рухатись лише вправо);
5. Тепер потрібно зробити вибір, куди рухати ручку. Рух вгору відповідає символу U (обов'язково верхній регістр), вниз D, вліво L, вправо R. Звісно пересунути ручку можна тільки в дозволених напрямках (у нашому прикладі у нас є тільки варіант руху вправо з вихідної позиції).
6. Після того, як ручка переміщена, потрібно додати до поточного коду знак сторони, куди була зсунута ручка (вийде iddqdR);
7. Далі беремо отриманий код і повторюємо все, починаючи з пункту 2, доки ми не доведемо ручку до правого нижнього кута. Як тільки вона опиниться там, замок відкриється.

Якщо продовжити наш приклад, то хеш від iddqdR буде 9b423a7ea61769284d0ce77cfe406a0c, перші чотири символи 9b42, де тільки другий символ відповідає відкритому стану - напрямок вниз (D). Рухаємось туди. Новий код – iddqdRD. Хеш від нього - 2a424a40bce71947f49e55f87c67862e. О-о-у, здається, всі перегородки заблоковані (символи 2a42), і ми більше не можемо нікуди рухати ручку. На щастя, це тільки приклад, і iddqd не ваш пароль. Реальний секретний код дозволяє згенерувати успішну комбінацію для відкриття замку. З іншого боку, правильних комбінацій може бути кілька.

Ваше завдання реалізувати описаний алгоритм, застосувати його до секретного коду та знайти найкоротший із можливих шляхів. Ця послідовність і вважатиметься правильною відповіддю.

Приклад паролiв та вiдповiдним їм послiдовностям наведенi у прикрiпленому файлi

Отже, алгоритм ви вже знаєте. А ось ваш код доступу: ysrmzgjo. Успіхів!*/

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        String s = "ysrmzgjo";
        getAnswer(s);
        System.out.println(md5Custom(s));
    }

    public static void getAnswer(String s) {
        HashSet<String> answers = new HashSet<>();
        int start = 0;


        String initialS = s;

        while (true) {
            HashMap<Integer, ArrayList<String>> map = new HashMap<>();
            ArrayList<String> mRD = new ArrayList<>();
            mRD.add("R");
            mRD.add("D");
            ArrayList<String> mRLD = new ArrayList<>();
            mRLD.add("R");
            mRLD.add("L");
            mRLD.add("D");
            ArrayList<String> mLD = new ArrayList<>();
            mLD.add("L");
            mLD.add("D");
            ArrayList<String> mRLDU = new ArrayList<>();
            mRLDU.add("R");
            mRLDU.add("L");
            mRLDU.add("D");
            mRLDU.add("U");
            ArrayList<String> mLDU = new ArrayList<>();
            mLDU.add("L");
            mLDU.add("D");
            mLDU.add("U");

            ArrayList<String> mRDU = new ArrayList<>();
            mRDU.add("R");
            mRDU.add("D");
            mRDU.add("U");

            ArrayList<String> mRU = new ArrayList<>();
            mRU.add("R");
            mRU.add("U");

            ArrayList<String> mLU = new ArrayList<>();


            ArrayList<String> mRLU = new ArrayList<>();
            mRLU.add("R");
            mRLU.add("L");
            mRLU.add("U");


            map.put(0, mRD);
            map.put(1, mRLD);
            map.put(2, mRLD);
            map.put(3, mLD);
            map.put(4, mRDU);
            map.put(5, mRLDU);
            map.put(6, mRLDU);
            map.put(7, mLDU);
            map.put(8, mRDU);
            map.put(9, mRLDU);
            map.put(10, mRLDU);
            map.put(11, mLDU);
            map.put(12, mRU);
            map.put(13, mRLU);
            map.put(14, mRLU);
            map.put(15, mLU);


            String answer = "";
            int numberOfSteps = 0;


            String hash = md5Custom(s);
            String code = hash.substring(0, 4);

            boolean pR = false;
            boolean pL = false;
            boolean pU = false;
            boolean pD = false;

            boolean cR = false;
            boolean cL = false;
            boolean cU = false;
            boolean cD = false;


            if (map.get(start).contains("U")) {
                pU = true;

            }
            if (map.get(start).contains("R")) {
                pR = true;


            }
            if (map.get(start).contains("D")) {
                pD = true;


            }
            if (map.get(start).contains("L")) {
                pL = true;


            }

            if (hash.charAt(0) == 'b' ||
                    hash.charAt(0) == 'c' ||
                    hash.charAt(0) == 'd' ||
                    hash.charAt(0) == 'e' ||
                    hash.charAt(0) == 'f') {
                cU = true;

            }
            if (hash.charAt(1) == 'b' ||
                    hash.charAt(1) == 'c' ||
                    hash.charAt(1) == 'd' ||
                    hash.charAt(1) == 'e' ||
                    hash.charAt(1) == 'f') {
                cD = true;

            }
            if (hash.charAt(2) == 'b' ||
                    hash.charAt(2) == 'c' ||
                    hash.charAt(2) == 'd' ||
                    hash.charAt(2) == 'e' ||
                    hash.charAt(2) == 'f') {
                cL = true;

            }
            if (hash.charAt(3) == 'b' ||
                    hash.charAt(3) == 'c' ||
                    hash.charAt(3) == 'd' ||
                    hash.charAt(3) == 'e' ||
                    hash.charAt(3) == 'f') {
                cR = true;

            }
            ArrayList<String> possibleMoves = new ArrayList<>();

            if (pD && cD) {
                possibleMoves.add("D");

            }

            if (pR && cR) {
                possibleMoves.add("R");

            }

            if (pU && cU) {
                possibleMoves.add("U");

            }
            if (pL && cL) {
                possibleMoves.add("L");

            }


            if (possibleMoves.size() == 1) {

                if (possibleMoves.get(0).equals("D")) {
                    start += 4;
                    s += "D";
                } else if (possibleMoves.get(0).equals("U")) {
                    start -= 4;
                    s += "U";

                } else if (possibleMoves.get(0).equals("R")) {
                    start += 1;
                    s += "R";

                } else if (possibleMoves.get(0).equals("L")) {
                    start -= 1;
                    s += "L";

                }
            } else if (possibleMoves.size() == 0 || start >= 15 || start < 0) {


                s = initialS;

                char[] sc = s.toCharArray();
                int u = 0;
                int d = 0;
                int l = 0;
                int r = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (sc[i] == 'U') {
                        u++;
                    } else if (sc[i] == 'D') {
                        d++;
                    } else if (sc[i] == 'R') {
                        r++;
                    } else if (sc[i] == 'L') {
                        l++;
                    }
                }
                System.out.println("is incorrect " + s.substring(8));


                start = 0;
                continue;


            } else {

                s += possibleMoves.get((int) (Math.random() * possibleMoves.size()));
                String r = possibleMoves.get((int) (Math.random() * possibleMoves.size()));
                if (r.equals("U")) {
                    start -= 4;
                } else if (r.equals("D")) {
                    start += 4;
                } else if (r.equals("R")) {
                    start += 1;
                } else if (r.equals("L")) {
                    start -= 1;
                }


            }

            if (start == 15) {

                System.out.println("start is 15  " + s.substring(8));


            }


        }

    }


    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}


