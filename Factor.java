package Ex1;


import java.util.ArrayList;

public class Factor
{
    private String [] values;
    private Factor [] parents;
    private ArrayList<String> cpt;
    //empty constructor
    public Factor()
    {
        this.values = new String[0];
        this.parents = new Factor[0];
        this.cpt = new ArrayList<>();
    }
    public Factor(String[] values , Factor[] parents, ArrayList<String> s)
    {
        this.values = values;
        this.parents = parents;
        this.cpt = s;
    }
    //constructor if there are no parents
    public Factor(String[] values , ArrayList<String> s)
    {
        this.values = values;
        this.parents = null;
        this.cpt = s;
    }
    public ArrayList<String> getCpt()
    {
        return this.cpt;
    }
    public String[] getValues()
    {
        return this.values;
    }
    public Factor[] getParents()
    {
        return this.parents;
    }
    public void setValues(String[] values)
    {
        this.values = values;
    }
    public void setParents(Factor[] parents)
    {
        this.parents = parents;
    }
//    public String toString()
//    {
//        System.out.println(this.cpt + " " + this.getCpt());
//    }
//    public ArrayList<String []> fixTheArrayList()
//    {
//        ArrayList<String[]> newCpt = new ArrayList<>();
//        for (int i = 0; i < this.cpt.size(); i++)
//        {
//            newCpt.add(this.cpt.get(i).split(","));
//        }
//        return newCpt;
//    }
//    public String toString()
//    {
//        for (int i = 0; i < this.cpt.size(); i++)
//        {
//            System.out.println(this.cpt.get(i));
//        }
//        return "";
//    }
}