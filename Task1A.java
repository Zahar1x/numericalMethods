package ru.mai.numericalMethods.homeTest;

import java.util.Locale;

public class Task1A {
    public static void main(String[] args) {
        float eps = 0.001f;
        double x = 1;
        double x_k = 0;
        System.out.println("Выполнил студент М3О-235Б-19 Захарченко Данила.\n Вариант 8\n №1А");

        simpleIterationMethod(eps, x_k, x);

        newtonsMethod(eps, x_k, x);
    }

    private static void simpleIterationMethod(float eps, double x_k, double x) {
        int count = 0;
        System.out.println("Решение методом простой итерации");
        long m = Math.round(1.01*F1(1));
        System.out.println("m = " + m);
        while (Math.abs(x_k - x) >= eps) {
            x = x_k;
            x_k = x - F(x) / m;
            count++;
            System.out.println("x = " + x + " " + "x_k = " + x_k + " " + "iteration = " + count);
        }
        String res = String.format(Locale.ROOT, "%.2f", x_k);
        System.out.println("Ответ: " + res);
    }

    private static void newtonsMethod(float eps, double x_k, double x) {
        System.out.println("Решение методом Ньютона");
        int count = 0;
        while (Math.abs(x_k - x) >= eps) {
            x = x_k;
            x_k = x - F(x) / F1(x);
            System.out.println("x = " + x + " " + "x_k = " + x_k + " " + "iteration = " + count);
            count++;
        }
        String res1 = String.format(Locale.ROOT, "%.2f", x_k);
        System.out.println("Ответ: " + res1);
    }

    public static double F(double x) {
        double res = 3 * x - Math.cos(x) - 2;
        return res;
    }

    public static double F1(double x) {
        double res = 3 + Math.sin(x);
        return res;
    }
}
