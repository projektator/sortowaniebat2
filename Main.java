//package com.company;
package sortowaniebatq;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner odczyt = new Scanner(System.in);    //sluzy do wczytywaniea danych od uzytkownika

    public static void main(String[] args) {
        String liczba;
        int wersja, wielkosc, l;
        int[] tablica;

        wersja = pobierzWersjęWybranegoAlgorytmu();
        wielkosc = pobierzIloscLiczb();
        tablica = utworzLosowoUzupelnionaTablicę(wielkosc);

        //posortuj elementy tablicy
        switch (wersja) {
            case 1:
                wypiszPosortowanaZawartosc(sortujPrzezWstawianie(tablica));
                break;
            case 2:
                wypiszPosortowanaZawartosc(sortujBabelkowo(tablica));
                break;
            case 3:
                wypiszPosortowanaZawartosc(sortujMetodaSzybka(tablica, 0, tablica.length - 1));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + wersja);
        }
    }

    private static int pobierzWersjęWybranegoAlgorytmu() {
        //wyswietl tytul i menu wyboru
        System.out.println("\n\nSortowanie");
        System.out.println("#1 Sortowanie przez wstawianie\n");
        System.out.println("#3 Sortowanie babelkowe\n");
        System.out.println("#6 Sortowanie szybkie\n");

        System.out.println("Ktoym algorytmem chcesz sortowac ?:");
        String wersja = odczyt.nextLine();
        System.out.println("\n");

        return Integer.parseInt(wersja);
    }

    private static int pobierzIloscLiczb() {
        //pobierz od uzytkownika liczbe elementow do sortowania
        System.out.println("Jak duzo liczb chcesz posortowac ?:");
        String ileLiczb = odczyt.nextLine();
        System.out.println("\n");

        return Integer.parseInt(ileLiczb);
    }

    private static int[] utworzLosowoUzupelnionaTablicę(int wielkosc) {
        int[] tablica = new int[wielkosc];
        Random generator = new Random();

        for (int i = 0; i < wielkosc; i++) {
            tablica[i] = generator.nextInt(100);
        }

        return tablica;
    }

    private static int[] sortujPrzezWstawianie(int[] tablica) {
        int klucz, j;

        //dla kazdego elementu tablicy do posortowania, poczawszy od drugiego
        for (int i = 1; i < tablica.length; i++) {
            j = i;
            klucz = tablica[i];
            //poszukaj miejsca dla aktualnego elementu
            //szukaj tylko w posortowanej juz częsci tablicy (czyli wsrod elementow o indeksach mniejszych od aktualnego)
            //przesuwaj element w kiedunku poczatku tablicy tak dlugo, az przed nim jest element większy i nie znajduje sie na poczatku tablicy
            while (j > 0 && tablica[j - 1] > klucz) {
                tablica[j] = tablica[j - 1];
                j--;
            }

            tablica[j] = klucz;
        }

        return tablica;
    }

    //private static int[] sortujPrzezWybieranie(int[] tablica) {

    private static int[] sortujBabelkowo(int[] tablica) {
        int temp, i, zmiana;

        do {
            zmiana = 0;
            i = tablica.length - 1;

            do {
                i--;
                if (tablica[i + 1] < tablica[i]) {
                    temp = tablica[i];
                    tablica[i] = tablica[i + 1];
                    tablica[i + 1] = temp;
                    zmiana = 1;
                }
            } while (i != 0);
        } while (zmiana != 0);

        return tablica;
    }

    private static int[] sortujMetodaShella(int[] tablica) {
        int inner, outer, temp;
        int h = 1;

        while (h <= tablica.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (outer = h; outer < tablica.length; outer++) {
                temp = tablica[outer];
                inner = outer;

                while (inner > h - 1 && tablica[inner - h] >= temp) {
                    tablica[inner] = tablica[inner - h];
                    inner -= h;
                }

                tablica[inner] = temp;
            }

            h = (h - 1) / 3;
        }

        return tablica;
    }

    private static int[] sortujMetodaSzybka(int[] tablica, int x, int y) {
        int i, j, v, temp;

        i = x;
        j = y;
        v = tablica[(x + y) / 2];

        do {
            while (tablica[i] < v)
                i++;

            while (v < tablica[j])
                j--;

            if (i <= j) {
                temp = tablica[i];
                tablica[i] = tablica[j];
                tablica[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (x < j)
            sortujMetodaSzybka(tablica, x, j);
        if (i < y)
            sortujMetodaSzybka(tablica, i, y);

        return tablica;
    }

    // private static int[] sortujPrzezKopcowanie(int[] tablica) {

    public static void zamień(int[] tablica, int i, int j) {
        int t = tablica[i];
        tablica[i] = tablica[j];
        tablica[j] = t;
    }

    private static void wypiszPosortowanaZawartosc(int[] tablica) {
        for (int i = 0; i < tablica.length; i++) {
            System.out.println("#" + (i + 1) + " :\t" + tablica[i]);
        }

        System.out.println();
    }
}