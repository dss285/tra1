package tra1.vk6.xtehtava;

import java.util.HashSet;
import java.util.Set;

public class TRAI_20_X6_tommpuur implements TRAI_20_X6 {

    /**
     * Yksinkertainen, poistetaan kaikista seteistä muista seteistä olevat.
     * Näin saadaan alkiot, jotka ovat vain kerran setissä.
     * Aikavaativuus HashSeteillä on O(n).
     *
     */
    /**
     * Vain yhdessÃ¤ -yhdiste.
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan yhdessÃ¤ kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     *
     * @param S1 syÃ¶tejoukko
     * @param S2 syÃ¶tejoukko
     * @param S3 syÃ¶tejoukko
     * @return tulosjoukko
     */
    @Override
    public <E> Set<E> vainYhdessa(Set<E> S1, Set<E> S2, Set<E> S3) {

        Set<E> tulos = new HashSet<>();
        HashSet<E> set1 = new HashSet<E>();
        HashSet<E> set2 = new HashSet<E>();
        HashSet<E> set3 = new HashSet<E>();

        set1.addAll(S1);
        set2.addAll(S2);
        set3.addAll(S3);

        set1.removeAll(S2);
        set1.removeAll(S3);

        set2.removeAll(S1);
        set2.removeAll(S3);

        set3.removeAll(S1);
        set3.removeAll(S2);

        tulos.addAll(set1);
        tulos.addAll(set2);
        tulos.addAll(set3);

        return tulos;
    }
}
