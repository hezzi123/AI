package Ex1;

import java.util.ArrayList;

public class VariableElimination
{
    private ArrayList<ArrayList<String>> arr;
    private Factor factor;

    public VariableElimination(Factor factor)
    {
        this.factor = factor;
        this.arr = addToMat(this.factor);
    }

    public ArrayList<ArrayList<String>> addToMat(Factor factor)
    {
        //variables for the first for
        ArrayList<String> parents = new ArrayList<>();
        ArrayList<String> operatorFirstIdx = new ArrayList<>();
        ArrayList<String> numeric = new ArrayList<>();
        ArrayList<ArrayList<String>>  mat = new ArrayList<>();
        String[] splitArr;
        double sum = 0;
        String s;
        //fit the first ArrayList
        for (int i = 0; i < factor.getCpt().size(); i ++)
        {

            splitArr = factor.getCpt().get(i).split(",");
            for (int j = 0; j < factor.getParents().length; j++)
            {
                parents.add(splitArr[j]);
            }
            for (int j = factor.getParents().length; j < splitArr.length; j += 2)
            {
                operatorFirstIdx.add(splitArr[j].substring(1));
            }
            operatorFirstIdx.add(factor.getValues()[factor.getValues().length - 1]);
            for (int j = factor.getParents().length + 1; j < splitArr.length; j += 2)
            {
                numeric.add(splitArr[j]);
                sum += Double.valueOf(splitArr[j]);
            }
            sum = 1 - sum;
            s = String.valueOf(sum);
            numeric.add(s);
            for (int j = 0; j < numeric.size(); j++)
            {
                mat.add(0,parents);
                mat.get(j).add(operatorFirstIdx.get(j));
                mat.get(j).add(numeric.get(j));
            }
            parents = new ArrayList<>();
            operatorFirstIdx = new ArrayList<>();
            numeric = new ArrayList<>();
            sum = 0;
        }
        //fixing the mat (that it will only print every row 1 time instead of the amount of values that the factor has)
        ArrayList<ArrayList<String>> newMat = new ArrayList<>();
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < mat.size(); i += factor.getValues().length)
        {
            for (int j = 0; j < mat.get(0).size(); j++)
            {
                newList.add(j , mat.get(i).get(j));
            }
            newMat.add(newList);
            newList = new ArrayList<>();
        }
        //adding the parents values before every operation and the numeric number
        ArrayList<ArrayList<String>> lastMat = new ArrayList<>();
        for (int i = 0; i < newMat.size(); i++)
        {
            for (int j = factor.getParents().length; j < newMat.get(0).size(); j += 2)
            {
                for (int k = 0; k < factor.getParents().length; k++)
                {
                    newList.add(k , newMat.get(i).get(k));
                }
                newList.add(factor.getParents().length , newMat.get(i).get(j));
                newList.add(factor.getParents().length + 1 ,newMat.get(i).get(j + 1));

            }
            lastMat.add(newList);
            newList = new ArrayList<>();
        }
        //variables for the last for
        ArrayList<ArrayList<String>> returnMat = new ArrayList<>();
        int firstPlacesList = 0;
        int endLine = 0;
        int indexLine = 0;
        //cutting every row into x rows, that x=how many values the factor has
        for (int i = 0; i < factor.getCpt().size() * factor.getValues().length; i++)
        {
            for (int j = 0; j < factor.getParents().length + 2; j++)
            {
                newList.add(firstPlacesList , lastMat.get(indexLine).get(endLine));
                firstPlacesList++;
                endLine++;
            }
            returnMat.add(newList);
            newList = new ArrayList<>();
            firstPlacesList = 0;
            if (endLine == lastMat.get(0).size())
            {
                endLine = 0;
                indexLine++;
            }
        }
        reverse(returnMat);

        return returnMat;
    }
    //reversing our matrix
    public ArrayList<ArrayList<String>> reverse(ArrayList<ArrayList<String>> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }


    public void print()
    {
        for (int i = 0; i < this.arr.size(); i++)
        {
            for (int j = 0; j < this.arr.get(0).size(); j++)
            {
                System.out.print(this.arr.get(i).get(j) + "   ");
            }
            System.out.println();
        }
    }
    private boolean isNumeric(String s)
    {
        try
        {
            double d = Double.parseDouble(s);
        }
        catch (NumberFormatException | NullPointerException nfe)
        {
            return false;
        }
        return true;
    }
    //get the matriza
    public ArrayList<ArrayList<String>> getArr()
    {
        return this.arr;
    }
    //get the factor
    public Factor getFactor()
    {
        return this.factor;
    }

    //making a new matrix where we only take the given value of the child
    public void forChilds(String s)
    {

        String[] temp;
        int counter = 0;
        ArrayList<ArrayList<String>> newChildMat = new ArrayList<>();
        temp = s.split("=");
        for (int i = 0; i < this.arr.size(); i++)
        {
            if (this.arr.get(i).get(this.factor.getParents().length).equals(temp[1]))
            {
                newChildMat.add(counter , this.arr.get(i));
                counter++;
            }
        }
        this.arr = newChildMat;
    }

    //we join the factors
    public void joinFactors(VariableElimination v)
    {
        double sum;
        int run = this.factor.getParents().length + 1;
        int runParent = v.getFactor().getParents().length + 1;
        int counter = 0;
        for (int i = 0; i < v.getArr().size(); i++)
        {
            sum = Double.valueOf(this.arr.get(counter).get(run)) * Double.valueOf(v.getArr().get(i).get(runParent));
            v.getArr().get(i).remove((runParent));
            v.getArr().get(i).add(runParent , (String.valueOf(sum)));
            counter++;
            if (counter == this.factor.getValues().length)
                counter = 0;
            sum = 0;
        }
        this.arr = v.getArr();
    }

    //we eliminate the factor
    public void eliminateFactors()
    {
        ArrayList<ArrayList<String>> newEliminateMat = new ArrayList<>();
        int run = this.arr.get(0).size() - 1;
        double sum = 0;
        int counter = 0;
        for (int i = 0; i < this.arr.size(); i += this.factor.getValues().length)
        {
            newEliminateMat.add(this.arr.get(i));
            for (int j = 0; j < this.factor.getValues().length; j++)
            {
                sum += Double.valueOf(this.arr.get(i + j).get(run));
            }
            newEliminateMat.get(counter).remove(run);
            newEliminateMat.get(counter).add((String.valueOf(sum)));
            counter++;
            sum = 0;
        }
        this.arr = newEliminateMat;
    }
    //we normalize the matrix
    public void normalize()
    {
        ArrayList<ArrayList<String>> newNormalizeMat = new ArrayList<>();
        int run = this.arr.get(0).size() - 1;
        double sum = 0;
        double end = 0;
        int counter = 0;
        for (int j = 0; j < this.arr.size(); j++)
        {
            sum += Double.valueOf(this.arr.get(j).get(run));
        }
        for (int i = 0; i < this.arr.size(); i++)
        {
            newNormalizeMat.add(this.arr.get(i));
            end = Double.valueOf(this.arr.get(i).get(run)) / sum;
            newNormalizeMat.get(counter).remove(run);
            newNormalizeMat.get(counter).add((String.valueOf(end)));
            counter++;
        }
    }
    public boolean isChild(Factor f1)
    {
        boolean ans = false;
        if (f1.getParents().length < 1)
            return false;
//        System.out.println(f1.getParents()[0]);
        for (int i = 0; i < f1.getParents().length ; i++)
        {
//            System.out.println(f1.getParents()[i].getName());
            if(this.factor.getName().equals(f1.getParents()[i].getName()))
            {
                ans = true;
            }
        }
        return ans;
    }
}













//    reverse(mat);
//        int counter = factor.getParents().length;
//        String s1;
//        String s2;
//        int a;
//        ArrayList<ArrayList<String>> newMat = mat;
//        for (int i = 0; i < newMat.size(); i = i + 2)
//        {
////            for (int j = factor.getParents().length; j < mat.get(0).size(); j += 2)
////            {
////                if (i % 2 == 0)
////                {
//                    newMat.get(i).set(2 , "fck u");
//                    newMat.get(i).set(3, "1.5");
////                }
////                System.out.print(mat.get(i).get(j) + "  ");
////            }
////            System.out.println();
//        }
//        for (int i = 0; i < newMat.size(); i ++)
//        {
//            for (int j = factor.getParents().length; j < newMat.get(0).size(); j ++)
//            {
////                if (((i % 2) == 0))
////                {
////                    mat.get(i).set(j, "1.5");
////                    mat.get(i).set(j + 1, "1.5");
////                }
//                System.out.print(newMat.get(i).get(j) + "  ");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < mat.size(); i ++)
//        {
////            System.out.println(i % 2 == 0);
//            for (int j = factor.getParents().length; j < mat.get(0).size(); j += 2)
//            {
////                for (int k = 0; k < factor.getValues().length; k++)
////                {
////                    if (i % j == k)
////                    {
////                        continue;
////                    }
////                    else
////                    {
//                if (counter == j || i % 2 == 0)
//                {
//                    continue;
//                }
//                else
//                {
//                    mat.get(i).set(j, "1.5");
//                    mat.get(i).set(j + 1, "1.5");
//                }
////                }
//            }
//            if (i % 2 == 0)
//                counter = 0;
//            counter++;
//        }

//            counter++;
//            if (i % factor.getValues().length == 0)
//            {
//                System.out.println("sd");
////                System.out.println("sda");
//                counter = factor.getParents().length;
//            }


//        for (int i = 0; i < factor.getCpt().size(); i++)
//        {
//            String[] splitArr2;
//            splitArr2 = factor.getCpt().get(i).split(",");
//
//
//            for (int j = 0; j < numeric.size(); j++)
//            {
//                if (i % 2 == 0)
//                    mat.get(j).add(splitArr2[j]);
//                else
//                    mat.get(j).add(splitArr2[j]);
//            }
//
//        }









//        for (int i = 0; i < mat.length; i++)
//        {
//            arr = factor.getCpt().get(i).split(",");
//            if(arr.length == mat[0].length) {
//                for (int j = 0; j < mat[0].length; j++) {
//
//                    if (arr[j].charAt(0) != '=' && (isNumeric(arr[j]) == false)) {
//                        mat[i][j] = arr[j];
//                    } else if (arr[j].charAt(0) == '=') {
//                        mat[i][j] = arr[j].substring(1);
//                    } else if (isNumeric(arr[j])) {
//                        mat[i][j] = arr[j];
//                    }
//
//                }
//            }
//            else
//            {
//                for (int j = 0; j < mat[0].length; j++) {
//                    if (arr[j].charAt(0) != '=' && (isNumeric(arr[j]) == false)) {
//                        for (int k = 0; k < factor.getValues().length - 1; k++) {
//                            mat[i + k][j] = arr[j];
//                        }
//                    }
//                    mat[i][j] = arr[j];
//                    mat[i+1][j] = arr[j];
//                }
//            }
//        }








// for (int i = 0; i < newMat.size(); i++)
//        {
//            for (int j = 0; j < 4; j ++)
//            {
//                lastList.add(counter , newMat.get(i).get(j + counter1));
//                counter++;
//                counter1++;
//            }
////            lastList.add(0 , newMat.get(i).get(4));
////            lastList.add(1 , newMat.get(i).get(5));
////            lastList.add(2 , newMat.get(i).get(6));
////            lastList.add(3 , newMat.get(i).get(7));
//            returnMat.add(lastList);
//            lastList = new ArrayList<>();
//            counter = 0;
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();





//        ArrayList<ArrayList<String>> mat = new ArrayList<>();
//        System.out.println(factor.getCpt().size());
//        String [] splitArr;
//        splitArr = factor.getCpt().get(0).split(",");
//        System.out.println(splitArr.length);
//        for (int i = 0; i < factor.getCpt().size(); i++)
//        {
//            for (int j = 0; j < splitArr.length * 2; j++)
//            {
//                    mat.get(j).add("0");
//            }
//
//        }
//        System.out.println(mat);
//        return mat;
//    }