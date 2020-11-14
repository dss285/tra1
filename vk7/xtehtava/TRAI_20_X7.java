package tra1.vk7.xtehtava;

import java.util.Map;
import java.util.Set;

public interface TRAI_20_X7 {

    /**
     * Palauttaa tiedon siitÃ¤ mitkÃ¤ joukot leikkaavat keskenÃ¤Ã¤n (siis millÃ¤ on yhteisiÃ¤ alkioista).
     * Tulos palautetaan kuvauksena siten, ettÃ¤ jos syÃ¶tteen joukolla Si on yhteisiÃ¤ alkioita
     * jonkun muun syÃ¶tteen joukon Sj kanssa, niin kuvauksen avaimeen Si liittyvÃ¤ssÃ¤ arvossa (joukossa) on
     * viittaus joukkoon Sj (ja pÃ¤invastoin). Kuvaus sisÃ¤ltÃ¤Ã¤ avaimenaan kunkin joukon SS sisÃ¤ltÃ¤mÃ¤n
     * joukon Si ja kunkin avaimen arvona on joukko niistÃ¤ joukoista Sj joilla on vÃ¤hintÃ¤Ã¤n yhteinen alkio
     * joukon Si kanssa.
     * @param SS syÃ¶te (joukkojen joukko)
     * @param <E> joukkojen alkiotyyppi
     * @return kuvaus joukkojen leikkaavuuksista
     */
    public <E> Map<Set<E>, Set<Set<E>>> leikkaavatJoukot(Set<Set<E>> SS);
}
