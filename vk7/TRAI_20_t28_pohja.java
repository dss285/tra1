package tra1.vk7;// TRAI_20_t28.java SJ
// PÃ¤Ã¤ohjelma ja pohja viikon 7 tehtÃ¤vÃ¤Ã¤n 28


import java.util.*;

/**
 * 28. Kirjoita algoritmi joka lajittelee tehokkaasti lÃ¤hes kasvavassa jÃ¤rjestyksessÃ¤ olevan taulukon
 (taulukko tai Vector/ArrayList). TÃ¤ssÃ¤ â€lÃ¤hesâ€ tarkoittaa, ettÃ¤ taulukon pÃ¤Ã¤osa on tÃ¤smÃ¤l-
 leen kasvavassa jÃ¤rjestyksessÃ¤, mutta korkeintaan m (pienehkÃ¶ vakio) viimeistÃ¤ alkiota ovat
 sekaisin ja voivat sisÃ¤ltÃ¤Ã¤ pienempiÃ¤kin alkioita. KÃ¤ytÃ¤ apuna prioriteettijonoa ja lomita
 lopusta alkaen prioriteettijonoon sijoitetut alkujaan sekaiset alkiot jÃ¤rjestettyyn taulukkoon.
 Aikavaativuudeksi tulee O(n+klogk).
 **/


public class TRAI_20_t28_pohja {

    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_t28 [N] [K] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤ listassa
    // K on sekaisten mÃ¤Ã¤rÃ¤ lopussa
    // ja S on satunnaislukusiemen, ja P on pienin arvottava luku
    public static void main(String[] args) {

        // taulukoiden koko
        int N = 11;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // lopun sekaiset
        int K = 3;
        if (args.length > 1)
            K = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = 4;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // tÃ¤ytetÃ¤Ã¤n lajiteltu osa alkioilla
        ArrayList<Integer> L = new ArrayList<>();
        Random r = new Random(siemen);
        for (int i = 0; i < N; i++) {
            L.add(r.nextInt(N*3));
        }
        Collections.sort(L);

        // lisÃ¤tÃ¤Ã¤n loppuun sekaisia alkioita
        for (int i = 0; i < K; i++) {
            L.add(r.nextInt(N*2));
        }

        // tulostetaan listat jos alkioita ei ole paljoa
        System.out.println("SyÃ¶tteessÃ¤ L alkioita: " + L.size());
        if (N <= 20) System.out.println("L:" + L);

        kJarjesta(L, K);

        if (N <= 20) System.out.println("L:" + L);

        // lisÃ¤tÃ¤Ã¤n pari pientÃ¤
        L.add(-100);
        L.add(-1000);
        kJarjesta(L, 2);
        if (N <= 20) System.out.println("L:" + L);

        // lisÃ¤tÃ¤Ã¤n pari suurta
        L.add(100);
        L.add(1000);
        kJarjesta(L, 2);
        if (N <= 20) System.out.println("L:" + L);

        // lisÃ¤tÃ¤Ã¤n samat pari suurta uudestaan
        L.add(100);
        L.add(1000);
        kJarjesta(L, 2);
        if (N <= 20) System.out.println("L:" + L);

        // ei lisÃ¤tÃ¤ mitÃ¤Ã¤n
        kJarjesta(L, 0);
        if (N <= 20) System.out.println("L:" + L);


    } // main()

    /**
     * JÃ¤rjestÃ¤Ã¤ melkein jÃ¤rjestyksessÃ¤ olevan taulukon.
     * EnintÃ¤Ã¤n m viimeistÃ¤ alkiota saa olla sekaisin, n-m ensimmÃ¤istÃ¤ alkiota oltava
     * valmiiksi jÃ¤rjestyksessÃ¤.
     * @param A lajiteltava taulukko
     * @param k lopun sekaisten mÃ¤Ã¤rÃ¤
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void kJarjesta(ArrayList<E> A, int k) {

        if (k < 1)  // ei lajiteltavaa...
            return;
        PriorityQueue<E> pq = new PriorityQueue<>();
        int tmp_a = A.size();
        for(int i = A.size()-1;i >= tmp_a-k;i--) {
            pq.offer(A.remove(i));
        }
        ArrayList<E> ls = new ArrayList<>();
        E tmp;
        int i = 0;
        while(i < A.size()) {
            if(pq.peek() == null) {
                ls.add(A.get(i));
                i++;
            } else {
                E a_tmp = A.get(i);
                if (pq.peek().compareTo(a_tmp) == 0) {
                    ls.add(a_tmp);
                    ls.add(pq.poll());
                    i++;
                } else if (pq.peek().compareTo(a_tmp) < 1) {
                    ls.add(pq.poll());
                } else {
                    ls.add(a_tmp);
                    i++;
                }
            }
        }
        while(pq.peek() != null) {
            ls.add(pq.poll());
        }


        A.clear();
        A.addAll(ls);
    }

} // class
