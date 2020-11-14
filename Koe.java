package tra1;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.*;

public class Koe {
    public static void main(String[] args) {
        /*ArrayList<Integer> ls1 = new ArrayList<>();
        ArrayList<Integer> ls2 = new ArrayList<>();
        for(int i = 0;i<20;i++) {
            ls1.add(i);
            ls2.add(i);
        }
        System.out.println(onkoTuplaYhteisia(ls1, ls2));
        System.out.println(ls1);
        System.out.println(ls2);
        System.out.println();
        System.out.println();

        ls1.clear();
        ls2.clear();
        for(int i = 0;i<20;i++) {
            ls1.add(1);
            ls2.add(1);
        }
        System.out.println(onkoTuplaYhteisia(ls1, ls2));
        System.out.println(ls1);
        System.out.println(ls2);*/

        BTree<Integer> puu = new BTree<Integer>();
        puu.setRoot(new BTreeNode<>(21));
        puu.getRoot().setRightChild(new BTreeNode<>(33));
        puu.getRoot().getRightChild().setLeftChild(new BTreeNode<>(3333));
        puu.getRoot().getRightChild().setRightChild(new BTreeNode<>(321));
        puu.getRoot().getRightChild().getRightChild().setRightChild(new BTreeNode<>(22222));

        BTreeNode<Integer> s = syvinSolmu(puu);
        System.out.println(s.getElement());


    }
    public static <E> boolean onkoTuplaYhteisia(Collection<E> A, Collection<E> B) {
        HashMap<E, Integer> hsa = new HashMap<>();
        HashMap<E, Integer> hsb = new HashMap<>();
        boolean tulos = false;
        for(E a : A) {
            if(hsa.containsKey(a)) {
                hsa.put(a, hsa.get(a)+1);
            } else {
                hsa.put(a, 1);
            }
        }
        for(E b : B) {
            if(hsb.containsKey(b)) {
                hsb.put(b, hsb.get(b)+1);
            } else {
                hsb.put(b, 1);
            }
        }
        for(Map.Entry<E, Integer> entry : hsa.entrySet()) {
            if(entry.getValue() > 1) {
                if (hsb.containsKey(entry.getKey())) {
                    if(hsb.get(entry.getKey()) > 1) {
                        tulos = true;
                        return tulos;
                    }
                }
            }
        }

        return  tulos;
    }
    public static <E> BTreeNode<E> syvinSolmu(BTree<E> puu) {
        if(puu.getRoot() == null) {
            return puu.getRoot();
        }
        BTreeNode<E> vasen = syvinSolmu(new BTree<>(puu.getRoot().getLeftChild()));
        BTreeNode<E> oikea = syvinSolmu(new BTree<>(puu.getRoot().getRightChild()));
        if(vasen == null && oikea == null) {
            return puu.getRoot();
        }
        else if(vasen == null && oikea != null) {
            return oikea;
        }
        else if(vasen != null && oikea == null) {
            return vasen;
        } else {
            return oikea;
        }
    }
}
