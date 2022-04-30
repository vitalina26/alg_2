package com.company;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java. util. Arrays;
import java.util.Scanner;
public class Main {
    static int[] Swap(int i, int[] array)
    {
        int tmp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = tmp;

        return array;
    }



    static int[][] Swap(int row, int col, int[][] array)
    {
        int tmp = array[col][row];
        array[col][row] = array[col + 1][row];
        array[col + 1][row] = tmp;

        return array;
    }



    static int[][][] Swap(int row, int col, int dim, int[][][] array)
    {
        int tmp = array[dim][col][row];
        array[dim][col][row] = array[dim + 1][col][row];
        array[dim + 1][col][row] = tmp;

        return array;
    }

    public static int[][][] sorting1(int[][][] array)
    {
        int n = array.length;

        for (int i = 0; i < n; i++)
            for (int z = 0; z < n; z++)
                for (int y = 0; y < n; y++)
                    for (int x = 0; x < n - 1; x++)
                        if (array[z][y][x] > array[z][y][x + 1])
                            array[z][y] = Swap(x, array[z][y]);

        return array;
    }



    public static int[][][] sorting2(int[][][] array)
    {
        int n = array.length;

        for (int i = 0; i < n; i++)
            for (int z = 0; z < n; z++)
                for (int y = 0; y < n - 1; y++)
                    for (int x = 0; x < n; x++)
                        if (array[z][y][x] > array[z][y + 1][x])
                            array[z] = Swap(x, y, array[z]);
        return array;
    }



    public static int[][][] sorting3(int[][][] array)
    {
        int n = array.length;

        for (int i = 0; i < n; i++)
            for (int z = 0; z < n - 1; z++)
                for (int y = 0; y < n; y++)
                    for (int x = 0; x < n; x++)
                        if (array[z][y][x] > array[z + 1][y][x])
                            array = Swap(x, y, z, array);
        return array;
    }

    public static void PrintArr3( int[][][] matrix)
    {

        for( int[][] t : matrix)
        {
            System.out.print("\t");
            for(int[] j : t)
            {
                System.out.print("\t");
                for(int k : j)
                    System.out.print("\t" + k);
                System.out.print("\t");
            }
            System.out.print("\t");
            System.out.println();
        }
    }
    public static int[] Distinct(int[] array)
    {
        int[] quantity = new int[11];

        for (int i = 0; i < array.length; i++)
            quantity[array[i]]++;

        int n = 0;
        for (int i = 0; i < 11; i++)
            if (quantity[i] > 0)
                n++;

        int[] res = new int[n];
        int r = 0;
        for (int i = 0; i < 11; i++)
            if (quantity[i] > 0)
                res[r++] = i;

        return res;
    }
    public static int[] Disjunction(int[] a, int[] b)
    {
        int na = a.length;
        int nb = b.length;
        int[] res = new int[na + nb];

        for (int i = 0; i < na; i++)
            res[i] = a[i];

        for (int i = 0; i < nb; i++)
            res[na + i] = b[i];

        res = Distinct(res);
        return res;
    }




    public static int[] Conjunction(int[] a, int[] b)
    {
        int[] da = Distinct(a);
        int[] db = Distinct(b);

        int n = 0;
        int[] res = new int[11];

        for (int i = 0; i < da.length; i++)
            for (int j = 0; j < db.length; j++)
                if (da[i] == db[j])
                {
                    res[n++] = da[i];
                    break;
                }

        int[] result = new int[n];

        for (int i = 0; i < n; i++)
            result[i] = res[i];

        return result;
    }


    public static int[] Difference(int[] a, int[] b)
    {
        a = Distinct(a);
        b = Distinct(b);
        int n = a.length;

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j  < b.length; j++)
                if (a[i] == b[j])
                {
                    a[i] = -1;
                    n--;
                    break;
                }

        int[] res = new int[n];
        int aa = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] >= 0)
                res[aa++] = a[i];
        return res;

    }



    public static int[] SymmetricDifference(int[] a, int[] b)
    {
        int[] disjunction = Disjunction(a, b);
        int[] conjunction = Conjunction(a, b);
        return Difference(disjunction, conjunction);
    }


    public static int[] Complement(int[] a)
    {
        return Difference(unsm(), a);
    }




    static int[] unsm()
    {
        int[] res = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return res;
    }




    public static void main(String[] args) {
        System.out.println("Enter n > 5");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][][] array = new int[n][][];

        for (int z = 0; z < n; z++)
        {
            array[z] = new int[n][];

            for (int y = 0; y < n; y++)
            {
                array[z][y] = new int[n];

                for (int x = 0; x < n; x++)
                {
                    Random random = new Random();
                    array[z][y][x] = random.nextInt(200);
                }
            }
        }
         PrintArr3( array);

        int min = array[0][0][0];
        int max = min;

        for (int[][] z : array)
        for (int[] y : z)
        for (int x : y)
        {
            if (x < min)
                min = x;

            if (x > max)
                max = x;
        }
        System.out.print("\n");
        System.out.println("MIN : " + min + "  MAX : " + max);
        System.out.println();
        int[][][] s1 = sorting1(array);
        PrintArr3(s1);
        System.out.println();
        int[][][] s2 = sorting2(array);
        PrintArr3(s2);
        System.out.println();
        int[][][] s3 = sorting3(array);
        PrintArr3(s3);

        System.out.println("Enter a > 5");
        int a = s.nextInt();
        System.out.println("Enter b > 5");
        int b = s.nextInt();

        int[] aa = new int[a];
        int[] bb = new int[b];


        for (int i = 0; i < a; i++)
        {
            Random random = new Random();
            aa[i] = random.nextInt(10);
        }

        for (int i = 0; i < b; i++)
        {
            Random random = new Random();
            bb[i] = random.nextInt( 10);
        }





    }
}
