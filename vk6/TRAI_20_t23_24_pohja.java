package tra1.vk6;// TRAI_20_t23_24.java SJ

import java.util.*;

public class TRAI_20_t23_24_pohja {

    public static void main(String[] args) {

        int N = 7;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        Set<Set<Integer>> SS = new HashSet<>();

        Random r = new Random(N + 1);
        System.out.println("SyÃ¶tejoukot:");
        for (int i = 0; i < N; i++) {
            Set<Integer> S = new HashSet<>();
            for (int j = 0; j < N; j++) {
                S.add(r.nextInt(N * 2));
            }
            SS.add(S);
            System.out.println("S" + i + ": " + S);
        }

        Set<Integer> tulos = yhdiste(SS);
        System.out.println("\nKaikkien yhdiste: " + tulos);
        System.out.println();

        tulos = vainYhdessa(SS);
        System.out.println("Vain yhdessÃ¤: " + tulos);
        System.out.println();

    } // main()


    /**
     * 23. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka pa-
     * lauttaa joukkona (Set<E>) kaikki ne alkiot jossakin (tai joissakin) syÃ¶tejoukoissa. Siis
     * yhdisteen. Vihje: iteraattori ja joukko-operaatiot. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param SS  joukkojen joukko
     * @param <E> joukkojen alkioiden tyyppi
     * @return kaikki jossain joukossa esiintyneet alkiot
     */
    public static <E> Set<E> yhdiste(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();
        Iterator<Set<E>> s_iterator = SS.iterator();
        while(s_iterator.hasNext()) {
            tulos.addAll(s_iterator.next());
        }

        // TODO

        return tulos;
    }

    /**
     * 24. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka pa-
     * lauttaa joukkona (Set<E>) kaikki ne alkiot jotka ovat tasan yhdessÃ¤ syÃ¶tejoukoista. Ne
     * alkiot jotka ovat useammassa kuin yhdessÃ¤ syÃ¶tejoukoista eivÃ¤t tule tulokseen. Ã„lÃ¤ muuta
     * syÃ¶tejoukkoja. Vihje: iteraattori ja joukko-operaatiot. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param SS  joukkojen joukko
     * @param <E> joukkojen alkioiden tyyppi
     * @return kaikki ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤ joukossa
     */
    public static <E> Set<E> vainYhdessa(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();
        HashSet<E> duplikaatit = new HashSet<>();
        Iterator<Set<E>> s_iterator = SS.iterator();
        while (s_iterator.hasNext()) {
            Set<E> i = s_iterator.next();
            for(E s : i) {
                if(tulos.contains(s)) {
                    duplikaatit.add(s);
                    tulos.remove(s);
                }
                else if(!duplikaatit.contains(s)) {
                    tulos.add(s);
                }
            }
        }

        // TODO

        return tulos;
    } // vainYhdessa()


} // class
