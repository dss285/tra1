package tra1.vk3.xtehtava;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class TRAI_20_X3_tommpuur implements TRAI_20_X3 {
    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * Algoritmi on lineaarinen O(n)
     * Tulos on O(n+n+n) = O(3n) = O(n)
     *
     * Luodaan väliaikainen lista, johon lisätään kaikki isommat kuin X, jonka alkiot lisätään A listaan
     * Luodaan lista johon lisätään kaikki pienemmät kuin X.
     * Käydään lista läpi, ja lisätään listoihin ehtojen mukaan.
     * Loopin jälkeen tyhjennetään A lista, jonka jälkeen lisätään kaikki väliaikaisesta listasta.
     *
     * Ei ole kaikkein "järkevin" tapa varmastikkaan, mutta lineaarinen.
     *
     *
     *
     */


    /**
     * SiirrÃ¤ pienemmÃ¤t toiseen listaan.
     * Poistaa listasta kaikki sellaiset alkiot jotka edeltÃ¤vÃ¤t alkiota x.
     * (Ovat "pienempiÃ¤", eli ne alkiot a joille a.compareTo(x) < 0. Poistetut alkiot
     * siirretÃ¤Ã¤n uuteen listaan joka palautetaan.
     * Lista A sÃ¤ilyy muuten jÃ¤rjestyksessÃ¤.
     * @param A syÃ¶telista
     * @param x alkio jota pienemmÃ¤t siirretÃ¤Ã¤n
     * @return siirretyt alkiot listana
     */
    @Override
    public <E extends Comparable<? super E>>  ArrayList<E> siirraPienemmat(ArrayList<E> A, E x) {

        ArrayList<E> U = new ArrayList<>();
        ArrayList<E> lista = new ArrayList<>();
        int cmp;
        for(int i = 0; i < A.size();i++) {//O(n)
            cmp = A.get(i).compareTo(x);
            if(cmp < 0) {
                U.add(A.get(i));
            } else {
                lista.add(A.get(i));
            }
        }
        A.clear(); //O(n)
        A.addAll(lista);//O(n)
        return U;
    }

}
