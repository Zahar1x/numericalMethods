package ru.mai.numericalMethods.homeTest;

import java.util.Arrays;
import java.util.Scanner;

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

        for (int i = 0; i < numOfEquitations; i++) {
            int sumOfLine = 0;
            for (int j = 0; j < numOfEquitations; j++) {
                if (i != j) {
                   sumOfLine += Math.abs(a[i][j]);
                }
            }
            if (Math.abs(a[i][i]) >= sumOfLine) {
                System.out.println("Переходим к следующему этапу");
            } else {
                System.out.println("Преобразуйте уравнение так, чтобы " +
                        "модули диагональных коэффициентов в каждом уравнении " +
                        "системы больше суммы модулей недиагональных коэффициентов");
            }
        }
        System.out.println("Приводим уравнения к виду x = ax + b");

        for (int i = 0; i < numOfEquitations; i++) {
            for (int j = 0; j < numOfEquitations; j++) {
                if (i != j) {
                    a[i][j] /= -a[i][i];
                    System.out.println("a[" + i + "][" + j + "] = " + a[i][j]);
                }
            }
            b[i] = b[i] / a[i][i];
            a[i][i] = 0;
            System.out.println("b[" + i + "] = " + b[i]);
        }

        System.out.println("____________________________________________________________-");
        for (int i = 0; i < numOfEquitations; i++) {
            for (int j = 0; j < numOfEquitations; j++) {
                System.out.print("a[" + i + "][" + j + "] = " + a[i][j] + " ");
            }
            System.out.print("b[" + i + "] = " + b[i] + "\n");
        }

        System.out.println("*******************************************************************************************");
        int k = 1;
        double[] accuracy = new double[numOfEquitations];
        double maxAccuracy = 0;

        //Положим начальное приближение равное свобожному члену
        for (int i = 0; i < numOfEquitations; i++) {
            x[i][0] = b[i];
        }
        do {
            //
            for (int i = 0; i < numOfEquitations; i++) {
                for (int j = 0; j < numOfEquitations; j++) {
                    if (i != j) {
                        x[i][k] = a[i][j] * x[i][k - 1];
                        System.out.print("x[" + i + "][" + k + "] = " + x[i][k] + " ");
                    }
                }
                x[i][k] += b[i];
            }

            //Поиск максимальной точности для k-той итерации
            for (int l = 0; l < numOfEquitations; l++) {
                accuracy[l] = Math.abs(x[l][k] - x[l][k - 1]);
            }
            for (int l = 0; l < numOfEquitations; l++) {
                maxAccuracy = 0;
                if (accuracy[l] > maxAccuracy) {
                    maxAccuracy = accuracy[l];
                }
            }
            k++;
        } while (maxAccuracy >= eps);



    }
}
