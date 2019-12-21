package Ex1;

import java.io.*;
import java.util.ArrayList;

public class probability
{
    public static void main(String[] args) throws IOException
    {
        //checking if the beginning of the file is correct and fine to work with
        File file = new File("D:\\Project\\Java\\Ariel\\dataScience\\src\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine();
        if(!(st.substring(0 , 7).equals("Network")))
            throw new ArithmeticException("The first word in the file should be - Network");
        st = br.readLine();
        if(!(st.substring(0 , 11).equals("Variables: ")))
            throw new ArithmeticException("The second word in the file should be - Variables:");
        st = st.substring(11);
        //init the variables
        String[] splitedVar = st.split(",");
        br.skip(2);
        st = br.readLine();
        //init our variables
        String name = "";
        String [] values ={};
        Factor [] parents = {};
        ArrayList<String> cpt = new ArrayList<>();
        ArrayList<Factor> factorsArr = new ArrayList<>();
        VariableElimination v;
        Factor f1;
        //run on all the variables until queries
        while(!st.equals("Queries"))
        {
            //name, values
            st = st.substring(4);
            name = st;
            st = br.readLine();
            st = st.substring(8);
            values = st.split(",");
            st = br.readLine();
            st = st.substring(9);
            //checking how much parents we have
            if (!(st.equals("none")))
            {
                if (!(st.contains(",")))
                {
                    parents = new Factor[1];
                    for (int i = 0; i < factorsArr.size(); i++)
                    {
                        if(factorsArr.get(i).getName().equals(name))
                        {
                            parents[0] = factorsArr.get(i);
                        }
                    }
                }
                else
                {
                    parents = new Factor[countComa(st) + 1];
                    for (int i = 0; i < parents.length; i++)
                    {
                        for (int j = 0; j < factorsArr.size(); j++)
                        {
                            if(factorsArr.get(j).getName().equals(name))
                            {
                                parents[i] = factorsArr.get(j);
                            }
                        }
                    }
                }
            }
            st = br.readLine();
            st = br.readLine();
            //cpt
            while(st.contains(","))
            {
                cpt.add(st);
                st = br.readLine();
            }
            //init the factor to mat
            f1 = new Factor(values , parents , cpt , name);
            factorsArr.add(f1);
            v = new VariableElimination(f1);
            v.print();
            System.out.println("********************************************");
            cpt = new ArrayList<>();
            st = br.readLine();
        }
    }
    //checking how much comas there are in a string
    public static int countComa(String s)
    {
        int counter = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == ',')
                counter++;
        }
        return counter;
    }

}

