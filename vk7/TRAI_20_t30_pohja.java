package tra1.vk7;// TRAI_20_t30.java SJ

import java.util.Random;

class TRAI_20_t30_pohja {

    static Random rnd = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        
        // ensimmÃ¤inen komentoriviparametri: alkioiden mÃ¤Ã¤rÃ¤
        int N = 30;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // toinen parametri: tulostetaanko vai ei, >1 = tulosta
        boolean print = true;
        if (args.length > 1 && (Integer.parseInt(args[1]) > 0))
            print = true;

        // kolmas parametri: jÃ¤rjesteyksessÃ¤ vai ei
        // -1 : laskeva jÃ¤rjestys, 1 = kasvava jÃ¤rjestys, 0 = satunnainen, 2 = satunnaisesti joku edellisistÃ¤
        int J = 2;
        if (args.length > 2)
            J = Integer.parseInt(args[2]);

        if (J == 2)
            J = satunnainenLuku(rnd, -1, 1);

        if (J == -1) System.out.println("SyÃ¶te laskevassa jÃ¤rjestyksessÃ¤");
        if (J == 0) System.out.println("SyÃ¶te satunnaisessa jÃ¤rjestyksessÃ¤");
        if (J == 1) System.out.println("SyÃ¶te kasvavassa jÃ¤rjestyksessÃ¤");


        Integer[] taulu;
        long start, end;

        if (N <= 50000) {
            // Kuplalajittelu
            taulu = satunnainenTaulu(N, J, 1);
            if (print && N < 50)
                for (int i = 0; i < N; i++)
                    System.out.print(taulu[i] + " ");
            System.out.println();
            System.out.println("Kuplalajittelu alkaa");
            start = System.nanoTime();
            bubbleSort(taulu);
            end = System.nanoTime();
            System.out.println("               " +
                               (end-start) + " ns");
            if (print && N < 50)
                for (int i = 0; i < N; i++)
                    System.out.print(taulu[i] + " ");
            System.out.println();
        }

        // Tavallinen pikalajittelu
        taulu = satunnainenTaulu(N, J, 1);
        System.out.println("Pikalajittelu  alkaa");
        start = System.nanoTime();
        quicksort(taulu, 0, N-1);
        end = System.nanoTime();
        System.out.println("               " +
                           (end-start) + " ns");
        if (print && N < 50)
            for (int i = 0; i < N; i++)
                System.out.print(taulu[i] + " ");
        System.out.println();

        // Pikalajittelu viritettynÃ¤

        taulu = satunnainenTaulu(N, J, 1);
        System.out.println("Viritetty pikalajittelu alkaa");
        start = System.nanoTime();
        quicksort30(taulu, 0, N-1);
        end = System.nanoTime();
        System.out.println("               " +
                (end-start) + " ns");
        if (print && N < 50)
            for (int i = 0; i < N; i++)
                System.out.print(taulu[i] + " ");
        System.out.println();


    } // main() 

    public static Integer[] satunnainenTaulu(int N, int J, int seed) {
        Integer[] taulu = new Integer[N];

        Random r = new Random(seed);
        for (int i = 0; i < N; i++) {
            if (J == 0)
                taulu[i] = (r.nextInt(N*2));
            else if (J > 0)
                taulu[i] = (i);
            else
                taulu[i] = (N-i);
        }

        return taulu;
    } // satunnainenTaulu()

    public static <E extends Comparable<? super E>> void bubbleSort(E[] A) {

        for (int i = 0; i < A.length-1; i++) {
            for (int j = A.length-1; j > i; j--) {
                if (A[j-1].compareTo(A[j]) > 0) {
                    E tmp = A[j-1];
                    A[j-1] = A[j];
                    A[j] = tmp;
                }
            }
        }
    } // bubbleSort()



    public static <E extends Comparable<? super E>>
    void quicksort30(E[] A, int i, int j) {
        if (i >= j)
            return;
        else {
                if(A.length < 8) {
                    bubbleSort(A);
                    return;
                }
                int k = partition30(A, i, j);
                quicksort30(A, i, k - 1);
                quicksort30(A, k + 1, j);
        }
    } // quicksort()



    public static <E extends Comparable<? super E>>
                int partition30(E[] A, int i, int j) {

        // TODO
        int jakokohta = satunnainenLuku(rnd, i+1, j);
        E jakoalkio = A[jakokohta];
        A[jakokohta] = A[i];
        A[i] = jakoalkio;
        // toistetaan kunnes i ja j tÃ¶rmÃ¤Ã¤vÃ¤t
        while (i < j) {
            // etsitÃ¤Ã¤n lopusta jakoalkiota pienempi
            while ((i < j) && (jakoalkio.compareTo(A[j]) < 0))
                j--;
            A[i] = A[j];

            // etsitÃ¤Ã¤n alusta jakoalkiota suurempi tai yhtÃ¤suuri
            while ((i < j) && (jakoalkio.compareTo(A[i]) >= 0))
                i++;
            A[j] = A[i];
        }
        A[i] = jakoalkio;
        return i;

    } // partition()



    public static <E extends Comparable<? super E>>
                    void quicksort(E[] A, int i, int j) {
        if (i < j) {
            int k = partition(A, i, j);
            quicksort(A, i, k-1);
            quicksort(A, k+1, j);
        }
    } // quicksort()

    public static <E extends Comparable<? super E>>
                    int partition(E[] A, int i, int j) {

        // jakoalkioksi ensimmÃ¤inen
        // NÃ„IN SAA TEHDÃ„ VAIN OPETUSTARKOITUKSESSA, EI KOSKAAN
        // OIKEASSA OHJELMASSA
        // HarjoitustehtÃ¤vÃ¤nÃ¤ satunnainen alkio
        E jakoalkio = A[i];

        // toistetaan kunnes i ja j tÃ¶rmÃ¤Ã¤vÃ¤t
        while (i < j) {

            // etsitÃ¤Ã¤n lopusta jakoalkiota pienempi
            while ((i < j) && (jakoalkio.compareTo(A[j]) < 0))
                j--;
            A[i] = A[j];

            // etsitÃ¤Ã¤n alusta jakoalkiota suurempi tai yhtÃ¤suuri
            while ((i < j) && (jakoalkio.compareTo(A[i]) >= 0))
                i++;
            A[j] = A[i];

        } // while

        // jakoalkio paikalleen ja palautetaan sijainti
        A[i] = jakoalkio;
        return i;

    } // partition()


    private static int satunnainenLuku(Random r, int min, int max) {
        int d = max - min + 1;
        if (d < 1)
            d = 1;
       return r.nextInt(d) + min;
    }


} // class TRAI_20_t30


