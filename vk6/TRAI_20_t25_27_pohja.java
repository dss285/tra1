package tra1.vk6;// TRAI_20_t25_27.java SJ

import java.util.*;

public class TRAI_20_t25_27_pohja {

    // kannattaa testata monipuolisesti erilaisilla syÃ¶tteillÃ¤ ja vaikka
    // tehdÃ¤ eri versioita syÃ¶tteen generoinnista

    public static void main(String[] args) {

        // taulukoiden koko
        int N1 = 15;
        if (args.length > 0)
            N1 = Integer.parseInt(args[0]);

        int N2 = N1 + 2;
        if (args.length > 0)
            N2 = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = N1 + N2;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);

        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 3)
            eri = 1;

        Random r = new Random(siemen);

        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();

        for (int i = 0; i < N1; i++) {
            L1.add(r.nextInt(N1 / 2));
        }
        for (int i = 0; i < N1; i++) {
            L2.add(r.nextInt((N2 / 2) + eri * N1));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (N1 <= 20 && N2 <= 20) {
            System.out.println("L1: " + L1);
            System.out.println("L2: " + L2);
        }

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 25
        System.out.println();
        Map<Integer, Integer> esiintymat1 = esiintymat(L1);
        for (Map.Entry<Integer, Integer> e : esiintymat1.entrySet()) {
            System.out.println("Alkio " + e.getKey() + " esiintyi " + e.getValue() + " kertaa.");
        }
        System.out.println();

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 26
        LinkedList<Integer> xorTulos = listaXor(L1, L2);

        if (N1 <= 20 && N2 <= 20) {
            System.out.println("\nTehtÃ¤vÃ¤ 26, vaintoisessa = " + xorTulos);
        } else {
            System.out.println(xorTulos.size() + " alkiota");
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 27
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nAnna kaksi merkkijonoa vÃ¤lilyÃ¶nnillÃ¤ erotettuna (. . lopettaaksesi): ");
            String A = sc.next();
            String B = sc.next();

            System.out.println("Ovatko anagrammit: " + ovatkoAnagrammit(A, B));

            if (A.equals(".") || B.equals("."))
                break;
        }


    } // main()


    /**
     * 25. Kirjoita algoritmi joka saa parametrinaan kokoelman (Collection<E>) ja joka muodostaa
     * ja palauttaa kuvauksen (HashMap<E, Integer>) jossa on avaimina kaikki eri alkiot jotka
     * kokoelmasta lÃ¶ytyvÃ¤t. Kunkin avaimen kuvana on ko. esiintymien lukumÃ¤Ã¤rÃ¤. Vihje: kaikki
     * kokoelmat tukevat foreach-lÃ¤pikÃ¤yntiÃ¤. Aikavaativuus?
     *
     * @param C   syÃ¶tekokoelma
     * @param <E> alkiotyyppi
     * @return kuvaus jossa avaimina ovat C:n alkiot ja arvona ko. alkion esiintymismÃ¤Ã¤rÃ¤t
     */
    public static <E> HashMap<E, Integer> esiintymat(Collection<E> C) {

        HashMap<E, Integer> es = new HashMap<>();

        for(E i : C) {
            if(es.containsKey(i)) {
                es.replace(i, es.get(i)+1);
            } else {
                es.put(i, 1);
            }
        }

        return es;
    }

    /**
     * 26. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ linkitettyÃ¤ listaa (valintasi
     * mukaan joko java.util.LinkedList tai TraLinkedList) ja joka muodostaa ja palauttaa uuden
     * listan joka sisÃ¤ltÃ¤Ã¤ kaikki ne alkiot jotka ovat vain jommastakummassa listassa (siis niiden
     * joko-tai -leikkauksen (xor)). Jos jokin alkio esiintyy jommassakummassa listassa useasti,
     * mutta ei toisessa listassa, niin se tulee tuloslistaan vain kerran. KÃ¤ytÃ¤ joukkoa tai kuvausta
     * (Set/Map) apuna. Vihje: mieti tarkasti ensin miten kÃ¤ytÃ¤t joukkoa tai kuvausta hyÃ¶dyksi,
     * ja ryhdy tarkentamaan algoritmiasi vasta sitten.
     *
     * @param L1  syÃ¶telista
     * @param L2  syÃ¶telista
     * @param <E> parametrityyppi
     * @return alkiot jotka ovat vain toisessa listassa
     */
    public static <E> LinkedList<E> listaXor(LinkedList<E> L1, LinkedList<E> L2) {

        LinkedList<E> tulos = new LinkedList<>();

        HashSet<E> tmp = new HashSet<>(L1);
        HashSet<E> hset1 = new HashSet<>(L1);
        HashSet<E> hset2 = new HashSet<>(L2);

        tmp.removeAll(hset2);
        hset2.removeAll(hset1);
        tmp.addAll(hset2);
        tulos.addAll(tmp);

        return tulos;

    }


    /**
     * 27. Kirjoita tehokas algoritmi joka tarkastaa ovatko kaksi merkkijonoa toistensa anagrammeja
     * (muokattavissa toiseksi vain kirjainten jÃ¤rjestystÃ¤ vaihtamalla). Siis onko merkkijonoissa
     * samat mÃ¤Ã¤rÃ¤t kutakin eri kirjainta. Algoritmi saa parametrinaan kaksi merkkijonoa ja
     * palauttaa totuusarvon. Vihje: laske kirjainten esiintymismÃ¤Ã¤rÃ¤t kuvaukseen. Koska nyt
     * harjoittelemme kuvauksen kÃ¤yttÃ¶Ã¤, Ã¤lÃ¤ kÃ¤ytÃ¤ taulukoiksi-lajittele-vertaa -ratkaisua vaikka
     * se helppo onkin. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param A syÃ¶temerkkijono
     * @param B syÃ¶temerkkijono
     * @return true jos A ja B ovat toistensa anagrammeja, muuten false
     */
    public static boolean ovatkoAnagrammit(String A, String B) {

        // TODO
        if(A.length() != B.length())
            return false;

        HashMap<Character, Integer> amap = new HashMap<>();
        HashMap<Character, Integer> bmap = new HashMap<>();
        for(int i = 0;i<A.length();i++) {
            if(amap.containsKey(A.charAt(i))) {
                amap.replace(A.charAt(i), amap.get(A.charAt(i))+1);
            } else {
                amap.put(A.charAt(i), 1);
            }

            if(bmap.containsKey(B.charAt(i))) {
                bmap.replace(B.charAt(i), bmap.get(B.charAt(i))+1);
            } else {
                bmap.put(B.charAt(i), 1);
            }
        }

        for(Map.Entry<Character, Integer> i : amap.entrySet()) {
            if(bmap.containsKey(i.getKey())) {
                if(!i.getValue().equals(bmap.get(i.getKey()))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

} // class

