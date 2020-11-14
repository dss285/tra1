package tra1.vk4.xtehtava;

import java.util.LinkedList;

public interface TRAI_20_X4 {

    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste.
     * Muodostaa uusen jÃ¤rjestetyn listan niistÃ¤ syÃ¶telistojen alkioista jotka esiintyvÃ¤t vain
     * toisessa syÃ¶telistoista.
     * Ei muuta syÃ¶telistoja.
     * @param A syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param B syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param <E> alkiotyyppi, toteuttaa Comparable-rajapinnan
     * @return uusi lista jossa kasvavassa jÃ¤rjestyksessÃ¤ ne alkiot jotka ovat vain yhdessÃ¤ syÃ¶telistassa
     */
    public <E extends Comparable<? super E>> LinkedList<E>
                    jarjestettyjenVainToisessaYhdiste(LinkedList<E> A, LinkedList<E> B);

}
