package com.es.eoi.ahorcado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Ahorcado {

	public static Scanner scan;

	public static final String ARCHIVO = "ejercicioAhoracado.txt";

	public static void main(String[] args) {

		String palabra = extraerPalabra();

		scan = new Scanner(System.in);

		juegoAhorcado(palabra);

		scan.close();
	}

	public static void juegoAhorcado(String palabra) {

		int vidas = 5;
		boolean victoria = false;
		Set<Character> letrasUsadas = new HashSet<Character>();
		Character siguienteLetra;

		while (vidas > 0 && victoria != true) {

			System.out.println("VIDAS: " + vidas);
			victoria = mostrarComprobarPalabra(letrasUsadas, palabra);
			imprimirLetrasUsadas(letrasUsadas);
			if (!victoria) {
				System.out.println("Introduce una nueva letra:");
				siguienteLetra = scan.next().charAt(0);
				if (!comprobarLetra(siguienteLetra, letrasUsadas, palabra)) {
					vidas--;
				}
			}
			limpiarPantalla();
		}

		if (victoria) {
			System.out.println("Has ganado!");
		} else {
			System.out.println("Has perdido! La palabra era "+palabra.toUpperCase());
		}

	}

	public static boolean mostrarComprobarPalabra(Set<Character> letrasUsadas, String palabra) {

		boolean victoria = true;
		Object[] letras = letrasUsadas.toArray();
		boolean imprimirLetra = false;

		for (int i = 0; i < palabra.length(); i++) {
			if (letras.length == 0) {
				System.out.print("_ ");
				victoria = false;
			} else {
				for (int j = 0; j < letras.length; j++) {
					if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase(String.valueOf((Character) letras[j]))) {
						imprimirLetra = true;
						break;
					}
				}
				if (imprimirLetra) {
					System.out.print(String.valueOf(palabra.charAt(i)).toUpperCase() + " ");
					imprimirLetra = false;
				} else {
					System.out.print("_ ");
					victoria = false;
				}
			}

		}
		System.out.println();

		return victoria;
	}

	public static boolean comprobarLetra(Character c, Set<Character> letrasUsadas, String palabra) {

		boolean contieneLetra = false;


		for (int i = 0; i < palabra.length(); i++) {
			if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase(String.valueOf(c))) {
				contieneLetra = true;
				break;
			}
		}
		letrasUsadas.add(Character.toUpperCase(c));

		return contieneLetra;
	}

	public static void imprimirLetrasUsadas(Set<Character> letrasUsadas) {
		System.out.print("Letras utilizadas: (");
		Object[] letras = letrasUsadas.toArray();
		for (int i = 0; i < letras.length; i++) {
			if (i != letras.length - 1) {
				System.out.print(letras[i] + ",");
			} else {
				System.out.print(letras[i]);
			}
		}
		System.out.println(")");
	}

	public static String extraerPalabra() {

		String palabra;
		FileReader fr;
		BufferedReader br;
		String nuevaPalabra;
		List<String> palabras = new ArrayList<String>();

		try {
			fr = new FileReader(ARCHIVO);
			br = new BufferedReader(fr);
			while ((nuevaPalabra = br.readLine()) != null) {
				palabras.add(nuevaPalabra);
			}
		} catch (Exception e) {
			System.err.println("No existe el archivo");
		}

		if (palabras.size() > 0) {

			palabra = palabras.get(ThreadLocalRandom.current().nextInt(0, palabras.size()));

		} else {

			palabra = "Pato";

		}

		return palabra;
	}

	public static void limpiarPantalla() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

}
