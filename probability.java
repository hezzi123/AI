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
        ArrayList<VariableElimination> variableEliArr = new ArrayList<>();
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
                        if(factorsArr.get(i).getName().equals(st))
                        {
                            parents[0] = factorsArr.get(i);

                        }
                    }
                }
                else
                {
                    parents = new Factor[countComa(st) + 1];
                    String [] splitParents = st.split(",");
                    for (int i = 0; i < factorsArr.size(); i++)
                    {
                        for (int j = 0; j < splitParents.length; j++)
                        {
                            if(factorsArr.get(i).getName().equals(splitParents[j]))
                            {
                                parents[i] = factorsArr.get(j);
                            }
                        }

                    }
                }
            }
            else
            {
                parents = new Factor[0];
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
            variableEliArr.add(v);
            System.out.println(f1.getParents().length);
            v.print();
            System.out.println("********************************************");
            cpt = new ArrayList<>();
            st = br.readLine();
        }
        st = br.readLine();
        st = br.readLine();
        st = br.readLine();
        if(!st.substring(0,2).equals("P(")){
            st = br.readLine();
        }
        else
        {
            st=st.substring(2);
            st = st.replace(")","");
            String[] splitedProb = st.split(",");
            System.out.println(splitedProb[0]);
            System.out.println(splitedProb[1]);
            System.out.println(splitedProb[2]);
            if (splitedProb[1].isEmpty())
            {
                System.out.println("hello");
            }
            else if(splitedProb[splitedProb.length - 1].contains("-"))
            {
                String[] splitedHidden = splitedProb[splitedProb.length - 1].split("-");
                ArrayList<Factor> childsOf=new ArrayList<>();
                ArrayList<VariableElimination> joinFinish = new ArrayList<>();
                VariableElimination v1;
                for (int k = 0; k < splitedHidden.length; k++)
                {
                    for (int i = 0; i < variableEliArr.size(); i++)
                    {
                        for (int j = 0; j < variableEliArr.get(i).getFactor().getParents().length; j++)
                        {

                            if (variableEliArr.get(i).getFactor().getParents()[j].getName().equals(splitedHidden[k])) {
                                joinFinish.add(variableEliArr.get(i));
                            }
//                            for (int i = 0; i < f1.getParents().length ; i++)
//                            {
//
//                                if(this.factor.getName().equals(f1.getParents()[i].getName()))
//                                {
//                                    ans = true;
//                                }
//                            }



//                            System.out.println(factorsArr.size());
//                            if(variableEliArr.get(i).isChild(factorsArr.get(j)))
//                            {
//
//                                v1 = new VariableElimination(factorsArr.get(j));
//                                joinFinish.add(v1);
//                            }

                        }
                    }
//                    joinFinish.get(0).forChilds();
                    System.out.println(joinFinish.get(0).getFactor().getName());
                    System.out.println(joinFinish.get(1).getFactor().getName());
//                    for (int i = 0; i < joinFinish.size(); i += 2)
//                    {
//                        joinFinish.get(i).joinFactors(joinFinish.get(i+1));
//                    }
                }
            }
            else
            {
                String[] splitedHidden = new String[1];
                splitedHidden[0] = splitedProb[1];
                System.out.println("hey");
            }
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

