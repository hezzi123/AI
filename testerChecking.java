package Ex1;

import java.util.ArrayList;

public class testerChecking
{
    public static void main(String[] args)
    {
        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        String [] values = {"true" , "false"};
        String [] sds = new String[4];
        sds[0] = "true,true,=true,0.95";
        sds[1] = "true,false,=true,0.94";
        sds[2] = "false,true,=true,0.29";
        sds[3] = "false,false,=true,0.001";
//        for (int i = 0; i < sds.length; i++) {
////            System.out.println(sds[i]);
//
//        }
        ArrayList<String> s = new ArrayList<String>();
        s.add(sds[0]);
        s.add(sds[1]);
        s.add(sds[2]);
        s.add(sds[3]);
//        System.out.println(s);
        Factor f1 = new Factor();
        Factor f2 = new Factor();
        Factor [] f = {f1 , f2};
        Factor f3 = new Factor(values , f , s);
        VariableElimination v = new VariableElimination(f3);
//        v.print();
//        System.out.println();
//        System.out.println();
//        System.out.println("************************************************************");
//        System.out.println();
//        System.out.println();
        String [] values1 = {"true" , "false"};
        String [] sds1 = new String[2];
        ArrayList<String> s1 = new ArrayList<String>();
        sds1[0] = "true,=true,0.9";
        sds1[1] = "false,=true,0.05";


        s1.add(sds1[0]);
        s1.add(sds1[1]);
        Factor [] factors = {f3};
        Factor f4 = new Factor(values1 , factors , s1);

        VariableElimination v1 = new VariableElimination(f4);

        v1.forChilds("f1=true");
        v1.print();


        String [] values2 = {"true" , "false"};
        String [] sds2 = new String[2];
        ArrayList<String> s2 = new ArrayList<String>();
        sds2[0] = "true,=true,0.7";
        sds2[1] = "false,=true,0.01";


        s2.add(sds2[0]);
        s2.add(sds2[1]);
        Factor [] factors4 = {f3};
        Factor f5 = new Factor(values2 , factors4 , s2);

        VariableElimination v6 = new VariableElimination(f5);
//        v1.print();
        v6.forChilds("f1=true");








//

        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();
//        VariableElimination v2 = new VariableElimination(f4);
//        v2.forChilds("f1=false");
//        v2.print();
        v6.print();
        System.out.println();
        System.out.println("normalize");
        v6.normalize();
        v6.print();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();
        v1.joinFactors(v6);
        v1.print();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();
        v1.joinFactors(v);
        v1.print();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();
        v1.eliminateFactors();
        v1.print();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();
        v1.normalize();
        v1.print();





//        ArrayList<ArrayList<String>> arr = new ArrayList<>();
//        String [] values = {"go","stay","run"};
//        String [] sds = new String[6];
//        sds[0] = "true,set,=go,0.25,=stay,0.7";
//        sds[1] = "true,noset,=go,0.2,=stay,0.6";
//        sds[2] = "true,maybe,=go,0.3,=stay,0.2";
//        sds[3] = "false,set,=go,0.55,=stay,0.15";
//        sds[4] = "false,noset,=go,0.28,=stay,0.3";
//        sds[5] = "false,maybe,=go,0.45,=stay,0.25";
////        for (int i = 0; i < sds.length; i++) {
//////            System.out.println(sds[i]);
////
////        }
//        ArrayList<String> s = new ArrayList<String>();
//        s.add(sds[0]);
//        s.add(sds[1]);
//        s.add(sds[2]);
//        s.add(sds[3]);
//        s.add(sds[4]);
//        s.add(sds[5]);
////        System.out.println(s);
//        Factor f1 = new Factor();
//        Factor f2 = new Factor();
//        Factor [] f = {f1 , f2};
//        Factor f3 = new Factor(values , f , s);
//        VariableElimination v = new VariableElimination(f3);
////        v.print();
    }
}
























//    {
//        // Java code to demonstrate the concept of
//// array of ArrayList
//
//
//        int n = 5;
//
//        // Here al is an array of arraylist having
//        // n number of rows.The number of columns on
//        // each row depends on the user.
//        // al[i].size() will give the size of the
//        // i'th row
//        ArrayList<Integer>[] al = new ArrayList[n];
//
//        // initializing
//        for (int i = 0; i < n; i++) {
//            al[i] = new ArrayList<Integer>();
//        }
//
//        // We can add any number of columns to each
//        // rows as per our wish
//        al[0].add(1);
//        al[0].add(2);
//        al[0].add(3);
//        al[1].add(5);
//        al[1].add(6);
//        al[1].add(7);
//        al[2].add(10);
//        al[2].add(20);
//        al[2].add(30);
//        al[3].add(56);
//        al[3].add(35);
//        al[3].add(37);
//        al[4].add(67);
//        al[4].add(89);
//        al[4].add(12);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < al[i].size(); j++) {
//                System.out.print(al[i].get(j) + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("******************************************");
//        for (int i = 0; i < n; i++) {
//            al[i].remove(1);
//        }
//        int a = al[0].size();
////        for (int i = 0; i < a; i++) {
////            al[0].remove(i);
////            System.out.println(a);
////        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < al[i].size(); j++) {
//                System.out.print(al[i].get(j) + " ");
//            }
//            System.out.println();
//        }
//    }
//}
////        String [][] arr = new String[3][4];
////        int [] brr = new int[3];
////        ArrayList<String> s = new ArrayList<>();
////        for (int i = 0; i < arr.length; i++)
////        {
////            for (int j = 0; j < arr[0].length; j++) {
////
////
////                arr[i][j] = "j," + i;
////            }
////        }
////        for (int i = 0; i < arr.length; i++)
////        {
////            for (int j = 0; j < arr[0].length; j++) {
////
////
////                System.out.print(arr[i][j] + "  ");
////            }
////            System.out.println();
////        }
////        brr = arr;
////        for (int i = 0; i < brr.length; i++) {
////            System.out.println(arr[i]);
////        }
//
////        Factor a = new Factor();
////        Factor [] factorArr = new Factor[1];
////        factorArr[0] = a;
////        Factor b = new Factor(arr , factorArr , s);
////        b.fixTheArrayList();
//////        b.toString();
////    }
////}
