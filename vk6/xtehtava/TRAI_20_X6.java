package tra1.vk6.xtehtava;

import java.util.Set;

public interface TRAI_20_X6 {

    /**
     * Vain yhdessÃ¤ -yhdiste.
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan yhdessÃ¤ kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     * @param S1    syÃ¶tejoukko
     * @param S2    syÃ¶tejoukko
     * @param S3    syÃ¶tejoukko
     * @param <E>   alkioiden tyyppi
     * @return  tulosjoukko
     */
    public <E> Set<E> vainYhdessa(Set<E> S1, Set<E> S2, Set<E> S3);

}
