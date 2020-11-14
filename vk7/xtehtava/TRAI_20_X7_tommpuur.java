package tra1.vk7.xtehtava;

import java.util.*;

public class TRAI_20_X7_tommpuur implements TRAI_20_X7 {

    /**
     *
     * aikavaativuuden parametrit: m = syÃ¶tejoukkojen mÃ¤Ã¤rÃ¤, n = syÃ¶tejoukkojen yhteinen alkiomÃ¤Ã¤rÃ¤.
     *
     * Aikavaativuus on O(n), tarkemmin O(n*m^2)
     * Pahimmillaan joka ikinen alkio ja setti käydään kokonaan läpi joka kerta.
     * Tämä on neliöllinen ratkaisu, mahdollinen "paras" minkä keksin olisi voinut olla O(nlogn+mlogm) (en ole 100% varma aikavaativuudesta)
     * Tämä arvio perustuu siihen, jos pystyttäisiin jokaisen iteraation aikana lisäämään kaikkiin  leikkaaviin, jolloin mahdollisesti osan iteraatioista voisi suoraan skipata,
     * mutta en tiedä onko mahdollista.
     * Tämä teoria ei ole todistettu, mutta koodissa on optimoitavaa, mikäli tämä on mahdollinen
     *
     */

    /**
     * Palauttaa tiedon siitÃ¤ mitkÃ¤ joukot leikkaavat keskenÃ¤Ã¤n (siis millÃ¤ on yhteisiÃ¤ alkioita).
     * Tulos palautetaan kuvauksena siten, ettÃ¤ jos syÃ¶tteen joukolla Si on yhteisiÃ¤ alkioita
     * jonkun muun syÃ¶tteen joukon Sj kanssa, niin kuvauksen avaimeen Si liittyvÃ¤ssÃ¤ arvossa (joukossa) on
     * viittaus joukkoon Sj (ja pÃ¤invastoin). Kuvaus sisÃ¤ltÃ¤Ã¤ avaimenaan kunkin joukon SS sisÃ¤ltÃ¤mÃ¤n
     * joukon Si ja kunkin avaimen arvona on joukko niistÃ¤ joukoista Sj joilla on vÃ¤hintÃ¤Ã¤n yhteinen alkio
     * joukon Si kanssa.
     *
     * @param SS syÃ¶te (joukkojen joukko)
     * @return kuvaus joukkojen leikkaavuuksista
     */
    @Override
    public <E> Map<Set<E>, Set<Set<E>>> leikkaavatJoukot(Set<Set<E>> SS) {
        Map<Set<E>, Set<Set<E>>> tulos = new HashMap<>();

        // TODO
        for(Set<E> a : SS) {
            if(!tulos.containsKey(a)) {
                Set<Set<E>> setti = new HashSet<>();
                tulos.put(a, setti);
            }
            for(Set<E> b : SS) {
                if(a != b) {
                    if (leikkaako(a, b)) {
                        Set<Set<E>> setti = tulos.get(a);
                        setti.add(b);
                        tulos.put(a, setti);
                    }
                }
            }
        }
        return tulos;
    }
    private <E> boolean leikkaako(Set<E> a, Set<E> b) {
        if(a.size() < b.size()) {
            for (E s : a) {
                if (b.contains(s)) {
                    return true;
                }
            }
        } else {
            for(E s : b) {
                if(a.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

}
