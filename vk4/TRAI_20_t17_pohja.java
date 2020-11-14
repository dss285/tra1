package vk4;// TRAI_20_t17.java SJ

/**
 * 17) Palindromi on merkkijono joka myÃ¶s takaperin luettuna on sama. Kun
 * sana talletetaan pakkaan merkki kerrallaan, on helppoa tarkastaa onko
 * sana palindromi vai ei. Kirjoita algoritmi joka tallettaa merkkijonon
 * merkit pakkaan ja joka tarkastaa onko pakan sisÃ¤ltÃ¶ palindromi vai ei.
 * Ota kurssin www-sivulta pÃ¤Ã¤ohjelma jossa on vinkkejÃ¤ miten merkkijono
 * muutetaan pakaksi. Aikavaativuus?
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

public class TRAI_20_t17_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_t17 [merkkijono]
    public static void main(String[] args) {

        String mjono = null;

        if (args.length > 0)
            mjono = args[0];

        if (mjono == null) {
            System.out.print("Anna merkkijono : ");
            Scanner sc = new Scanner(System.in);
            mjono = sc.nextLine();
        }

        System.out.print("Merkkijono '" + mjono + "' ");
        if (onkoPalindromi(mjono))
            System.out.println("on palindromi");
        else
            System.out.println("ei ole palindromi");

    } // main()



    /**
     * Merkkijonosta merkkipakka.
     * @param S syÃ¶temerkkijono
     * @return merkit pakassa
     */
    public static Deque<Character> merkkijonostaPakka(String S) {
        Deque<Character> D = new ArrayDeque<>();

        for (int i = 0; i < S.length(); i++)
            D.addLast(S.charAt(i));

        return D;

    }


    /**
     * Onko merkkijono palindromi vai ei?
     * @param S syÃ¶temerkkijono
     * @return totuusarvo
     */
    public static boolean onkoPalindromi(String S) {
        Deque<Character> D = merkkijonostaPakka(S);
        Iterator<Character> eteenpain = D.iterator();
        Iterator<Character> taaksepain = D.descendingIterator();
        String eteen = "";
        String taakse = "";
        while(eteenpain.hasNext()&&taaksepain.hasNext()) {
            eteen += eteenpain.next();
            taakse += taaksepain.next();
        }
        if(eteen.equals(taakse)) {
            return true;
        } else {
            return false;
        }
    }



} // class

