/*
* L'idée à travers ce programme est de répondre au problème posé dans le Advent of Code Day 2 (https://adventofcode.com/2023/day/2).
* Remerciements à Daniel Persson sans qui je ne serais pas parvenu à résoudre le problème.
*/

package adventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

	public static int puissancePartie(int a, int b, int c) { // Méthode qui sera utilisée en partie 2 du problème pour
																// calculer la puissance des cubes
		return a * b * c;
	}

	public static void main(String[] args) {

		try (BufferedReader lectureInput = new BufferedReader( // Lecture du fichier
				new FileReader("D:\\Programmes Pro\\ECLIPSE\\Travaux\\Ressources AdventOfCode\\InputDay2.txt"))) { // A
																													// remplacer
																													// par
																													// votre
																													// chemin

			Pattern pattern = Pattern.compile("(\\d+) (\\w+)"); // partern qui sépare en deux groupe, 1 pour le numéro
																// (le nombre de cube, 2 pour le mot ( la couleur ici )

			int totalPuissanceParties = 0; // Valeur qui sera incrémentée par la méthode puissancePartie()
			int totalPartiesValides = 0; // valeur qui sera incrémentée par chaque partie valide en fonction des
											// opérateurs logiques du problème 1

			for (String ligne : lectureInput.lines().toList()) {
				String[] arr = ligne.split(":"); // sépare chaque ligne: à gauche "GAME X" à droite " les cubes et leurs
													// couleurs "

				int nombrePartie = Integer.parseInt(arr[0].replace("Game", "").trim()); // convertit le nombre de la
																						// partie en INT puis supprime
																						// "Game"
				int blue = 0; // initialisation des valeurs
				int red = 0;
				int green = 0;

				for (String tours : arr[1].split(";")) { // on sépare chaque tours délimités par ";"

					for (String cubes : tours.split(",")) { // on sépare chaque cubes et sa couleur délimités par ","
						Matcher m = pattern.matcher(cubes); // on fait matcher les cubes avec le pattern qui sépare la
															// couleur et le nombre
						if (m.find()) {
							switch (m.group(2)) { // utilisation d'un switch qui permet de trier les cubes en fonction
													// de leurs couleurs
							case "blue":
								int newBlue = Integer.parseInt(m.group(1)); // on convertir en int la chaine du numéro
								if (newBlue > blue) { // permet d'assigner toujours la valeur la plus haute pour chaque
														// partie
									blue = newBlue;
								}
								break;
							case "red":
								int newRed = Integer.parseInt(m.group(1));
								if (newRed > red) {
									red = newRed;
								}
								break;
							case "green":
								int newGreen = Integer.parseInt(m.group(1));
								if (newGreen > green) {
									green = newGreen;
								}
								break;
							}

						}
					}

				}

				totalPuissanceParties += puissancePartie(blue, red, green);
				// En partie une il est demandé le nombre de parties valides en utilisant
				// seulement: 12 red cubes, 13 green cubes, and 14 blue cubes
				// On utilisera donc : System.out.println(totalPartiesValides); Sortie pour la
				// partie 1 du problème

				// En partie deux il est demandé de calculer la somme de la puissance des cubes
				// max par tours
				// System.out.println(totalPuissanceParties); Sortie pour la partie 2 du
				// problème

			}
		} catch (

		IOException e) { // Gestion des exceptions
			e.printStackTrace();
		}
	}
}
