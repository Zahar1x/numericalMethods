package ru.mai.numericalMethods.homeTest;

import java.util.Locale;

public class Task1 {
    public static void main(String[] args) {
        float a = 0;
        float b = 1;
        float x = 0;
        float eps = 0.001f;
        int counter = 0;

        System.out.println("Выполнил студент М3О-235Б-19 Захарченко Данила.\n Вариант 8\n №1");
        System.out.println("Метод дихотомии.");

        if (function(a) * function(b) > 0) {
            System.out.println("F(a) и F(b) одного знака");
            System.exit(0);
        }
        while ((b - a) >= eps) {
            x = (a + b) / 2;
            if (function(a) * function(x) < 0) {
                b = x;
            } else {
                a = x;
            }
            System.out.print("a = " + a + " ");
            System.out.print("b = " + b + " ");
            System.out.print("x = " + x + " " + "шаг: " + counter + "\n");
            counter++;
        }
        String res = String.format(Locale.ROOT, "%.2f", x);
        System.out.println("F(x) = " + function(x));
        System.out.println("Ответ: " + res);
    }
    public static double function(double x) {
        double res = 2 * Math.log(x) - x / 2 + 1;
        return res;
    }
}
