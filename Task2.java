package ru.mai.numericalMethods.homeTest;

import java.util.Scanner;

import static java.lang.System.exit;

public class Task2 {
    public static void main(String[] args) {
        final int numOfEquitations = 3;
        double[][] a = new double[numOfEquitations][numOfEquitations];
        double[] b = new double[numOfEquitations];
        double[][] x = new double[numOfEquitations][10];
        double[] r = new double[numOfEquitations];
        double[] y = new double[numOfEquitations];
        double eps = 0.0001;

        Scanner scan = new Scanner(System.in);

        scanEquitations(numOfEquitations, a, b, scan);

        System.out.println("Приводим уравнения к виду x = ax + b");

        ChangeEquits(numOfEquitations, a, b);

        System.out.println("____________________________________________________________-");
        printMatrix(numOfEquitations, a, b);

        System.out.println("*******************************************************************************************");
        
        Pogr(numOfEquitations, a, eps);


        iteration(numOfEquitations, b, eps, a);
    }

    private static void iteration(int numOfEquitations, double[] b, double eps, double[][] a) {
        double[] x = new double[numOfEquitations];
        double[] p = new double[numOfEquitations];
        double[] c = new double[numOfEquitations];
        double[] E = new double[numOfEquitations];
        double per = Pogr(numOfEquitations, a, eps), max = 0;
        for (int i = 0; i < numOfEquitations; i++)
        {
            x[i] = b[i];
            p[i] = 0;
        }
        double var = 0;
        for (int i = 0; i < numOfEquitations; i++)
        {
            var = 0;
            for (int k = 0; k < numOfEquitations; k++)
                var = a[i][k] * b[k];
            x[i] = var;
        }
        for (int i = 0; i < numOfEquitations; i++)
            p[i] = x[i] + b[i];
        int counter = 0;
        do
        {
            counter++;
            System.out.println("Итерация № " + counter);
            for (int i = 0; i < numOfEquitations; i++)
            {
                var = 0;
                for (int j = 0; j < i; j++)
                    var += (a[i][j] * p[j]);
                for (int j = i + 1; j < numOfEquitations; j++)
                    var += (a[i][j] * x[j]);
                c[i] = var;
                x[i] = b[i] + c[i];
            }
            max = 0;
            for (int i = 0; i < numOfEquitations; i++)
            {
                E[i] = Math.abs(x[i] - p[i]);
                if (max < E[i]) max = E[i];
                p[i] = x[i];
                System.out.println("x" + i + 1 + " = " + x[i] + " ");
            }
            System.out.println("\n");
            System.out.println("max = " + max);
            System.out.println("\n");
            System.out.println("\n");
        } while (max > per);
        System.out.println("Результат: \n\n");
        for (int i = 0; i < numOfEquitations; i++)
            System.out.println("x" + i + 1 + "=" + x[i] + " ");
    }

    private static double Pogr(int numOfEquitations, double[][] a, double epsilon) {
        double eps = 0;
        double sum = 0, max = 0;
        double norm1 = 0, norm2 = 0;
        for (int i = 0; i < numOfEquitations; i++)
        {
            for (int j = 0; j < i; j++)
            {
                sum += Math.abs(a[i][j]);
                if (sum > norm1) norm1 = sum;
            }
            sum = 0;
            for (int j = i + 1; j < numOfEquitations; j++)
            {
                sum += Math.abs(a[i][j]);
                if (sum > norm2) norm2 = sum;
            }
            sum = 0;
        }
        if (norm1 >= 1 || norm2 >= 1)
        {
            System.out.println("Норма матрицы больше или равна 1.");
            exit(1);
        }
        eps = ((1 - norm1) / norm2) * epsilon;
        return eps;
    }

    private static void printMatrix(int numOfEquitations, double[][] a, double[] b) {
        for (int i = 0; i < numOfEquitations; i++) {
            for (int j = 0; j < numOfEquitations; j++) {
                System.out.print("a[" + i + "][" + j + "] = " + a[i][j] + " ");
            }
            System.out.print("b[" + i + "] = " + b[i] + "\n");
        }
    }

    private static void ChangeEquits(int numOfEquitations, double[][] a, double[] b) {
        double temp = 0;
        for (int k = 0; k < numOfEquitations; k++)
        {
            for (int i = 0; i < numOfEquitations; i++)
            {
                temp = a[i][i] * (-1);
                b[i] /= temp;
                for (int j = 0; j < numOfEquitations; j++)
                {
                    a[i][j] /= temp;
                }
            }
        }
        for (int i = 0; i < numOfEquitations; i++)
        {
            b[i] *= -1;
            for (int j = 0; j < numOfEquitations; j++)
                a[i][i] = 0;
        }
    }

    private static void scanEquitations(int numOfEquitations, double[][] a, double[] b, Scanner scan) {
        for (int i = 0; i < numOfEquitations; i++) {
            for (int j = 0; j < numOfEquitations; j++) {
                System.out.println("Введите коэффицент a[" + i + "][" + j + "]");
                a[i][j] = scan.nextDouble();
            }
        }

        for (int i = 0; i < numOfEquitations; i++) {
            System.out.println("Введите свободный член для " + i + "-ого уравнения");
            b[i] = scan.nextDouble();
        }
    }
}
