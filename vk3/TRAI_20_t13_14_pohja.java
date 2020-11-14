package vk3;// TraI_18_t11_12.java SJ
// PÃ¤Ã¤ohjelma viikon 2 tehtÃ¤viin 11-12

/**
  * 11. Kirjoita tehokas algoritmi joka poistaa kasvavassa jÃ¤rjestyksessÃ¤
  * olevasta taulukkopohjaisesta listasta (ArrayList) duplikaatit. Siis kaikki
  * sellaiset alkiot jotka jo esiintyivÃ¤t samassa listalla aiemmin. Esimerkiksi
  * listasta (A C C E F F) jÃ¤Ã¤ jÃ¤ljelle (A C E F). Ã„lÃ¤ kÃ¤ytÃ¤ joukkotyyppejÃ¤
  * apuna. Vihje: ei kannata tehdÃ¤ remove-operaatioita keskelle. Aikavaativuus?
  *
  * 12. Kirjoita tehokas algoritmi joka poistaa kasvavassa jÃ¤rjestyksessÃ¤
  * olevasta linkitetystÃ¤ listasta (LinkedList) duplikaatit. Siis kaikki
  * sellaiset alkiot jotka jo esiintyivÃ¤t samassa listalla aiemmin.  Ã„lÃ¤ kÃ¤ytÃ¤
  * joukkotyyppejÃ¤ apuna. Vihje: linkitetyllÃ¤ listalla iteraattorin
  * remove-operaatio on vakioaikainen, mutta indeksien kÃ¤yttÃ¶ ei.
  * Aikavaativuus?
  **/



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class TRAI_20_t13_14_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_t13_14 [N] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.valueOf(args[2]);

        // tehtÃ¤vien 11 ja 12 testaus, jÃ¤rjestetty lista

        Integer[] T = new Integer[N];
        // tÃ¤ytetÃ¤Ã¤n alkioilla ja lajitellaan
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < N; i++) {
            T[i] = r.nextInt(N/2);
        }
        java.util.Arrays.sort(T);

        // tulostetaan taulukko jos alkioita ei ole paljoa
        if (N <= 20) {
            System.out.print("T: ");
            for (int i = 0; i < N; i++)
                System.out.print(" " + T[i]);
            System.out.println();
        }

        // muodostetaan taulukosta LinkedList ja ArrayList
        LinkedList<Integer> LL = new LinkedList<Integer>();
        ArrayList<Integer> AL = new ArrayList<Integer>(N);
        for (Integer x : T) {
            LL.add(x);
            AL.add(x);
        }


        // kutsutaan tehtÃ¤vÃ¤Ã¤ 13
        duplikaatitPois(AL);
        System.out.print("TehtÃ¤vÃ¤ 13:");
        if (N <= 20) {
            for (Integer x : AL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(AL.size());

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 14
        duplikaatitPois(LL);
        System.out.print("TehtÃ¤vÃ¤ 14:");
        if (N <= 20) {
            for (Integer x : LL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(LL.size());


    } // main()


    /**
     * Aikavaativuus on O(n), lista käydään vain kerran läpi, ei sisäkkäisiä.
     * Tulos olisi O(3n), 2n tulee kahdesta loopin jälkeisestä toiminnosta, ja muut ovatkin sitten vakioita.
     * Algoritmi on lineaarinen.
     *
     *
     *
     * TehtÃ¤vÃ¤ 13.
     * Duplikaattien poisto jÃ¤rjestetystÃ¤ ArrayList listasta.
     * Lista ei sisÃ¤llÃ¤ null:ja.
     * @param L kÃ¤siteltÃ¤vÃ¤ lista
     * @param <E> alkioiden tyyppi
     */
    public static <E extends Comparable<? super E>> 
    	void duplikaatitPois(ArrayList<E> L) {

        // TODO
        ArrayList<E> korvattava = new ArrayList<>();
        E viimeisin = null;
        for(E o : L) {//O(n)
            if(viimeisin == null) {
                korvattava.add(o);
                viimeisin = o;
            } else if(viimeisin != null && !viimeisin.equals(o)) {
                korvattava.add(o);
                viimeisin = o;
            } else {
                viimeisin = o;
            }
        }
        L.clear();//O(n)
        L.addAll(korvattava);//O(n)

    } // duplikaatitPois()


    
    /**
     * Aikavaativuus O(n), lista käydään vain kerran läpi
     * Melko samanlainen kuin t13. nyt vain poistetaan listasta eikä korvata sitä, kun linkedlistin iteraattorin remove on vakioaikainen.
     * Algoritmi on lineaarinen.
     *
     *
     * TehtÃ¤vÃ¤ 14.
     * Duplikaattien poisto jÃ¤rjestetystÃ¤ LinkedList listasta.
     * Lista ei sisÃ¤llÃ¤ null:ja.
     * @param L kÃ¤siteltÃ¤vÃ¤ lista
     * @param <E> alkioiden tyyppi
     */
    public static <E extends Comparable<? super E>>
    	void duplikaatitPois(LinkedList<E> L) {

        ListIterator<E> li = L.listIterator();
        E viimeisin = null;
        while(li.hasNext()) { // O(n)
            E o = li.next();
            if(viimeisin == null) {
                viimeisin = o;
            } else if(viimeisin != null && viimeisin.equals(o)) {
                viimeisin = o;
                li.remove();
            } else {
                viimeisin = o;
            }
        }
    	
    } // duplikaatitPois()
    

} // class

