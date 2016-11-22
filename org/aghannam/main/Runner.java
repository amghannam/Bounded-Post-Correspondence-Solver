/*
 * CS 575: Project #3
 * File: Runner.java
 */
package org.aghannam.main;

import org.aghannam.bpcp.Pair;
import org.aghannam.bpcp.Solver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main driver through which to run the BPCP solver.
 * 
 * @author Ahmed Ghannam (amalghannam@crimson.ua.edu) <br>
 *         CS-575: Project #3
 */
public class Runner {
	private static final double NANOS_IN_SECOND = 1000000000.0;

	private static ArrayList<Pair> instance;
	private static int k;

	/**
	 * Main method through which to run the solver.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		instructions();
		input();
		solve();
	}

	/**
	 * Reads a BPCP instance from the user.
	 * <p>
	 * The input is assumed to be two non-empty lists of strings that
	 * collectively represent the top and bottom parts of the given instance.
	 * Within each set, individual strings are separated by whitespace. In
	 * addition, an integer value k, which is assumed to be larger than zero, is
	 * read.
	 */
	private static void input() {
		Scanner scan = new Scanner(System.in);
		String first;
		String second;

		System.out.print("- Type the first set (top list): ");
		first = scan.nextLine();
		System.out.print("- Type the second set (bottom list): ");
		second = scan.nextLine();
		System.out.print("- Type an integer value for k: ");
		while (!scan.hasNextInt()) {
			scan.next();
		}
		k = scan.nextInt();

		scan.close();

		if (first.isEmpty() || second.isEmpty()) {
			System.err.println("\nInvalid input: One of the given sets is empty. Process terminated...");
			System.exit(1);
		}

		constructInstance(first, second);
	}

	/**
	 * Builds a BPCP instance from two sets of strings.
	 * 
	 * @param first
	 *            the first (top) set of the instance
	 * @param second
	 *            the second (bottom) set of the instance
	 */
	private static void constructInstance(String first, String second) {
		instance = new ArrayList<Pair>();

		String[] topWords = first.split("\\s+");
		String[] bottomWords = second.split("\\s+");

		if (topWords.length != bottomWords.length) {
			System.err.println("\nInvalid input: The two sets must be of equal length. Process terminated...");
			System.exit(1);
		}

		for (int i = 0; i < topWords.length; i++) {
			instance.add(new Pair(topWords[i], bottomWords[i]));
		}
	}

	/**
	 * Wrapper method to solve the BPCP on a specified instance. Also computes
	 * the search time in seconds.
	 */
	private static void solve() {
		DecimalFormat df = new DecimalFormat("0.00");
		Solver solver = new Solver(instance);
		long startTime = System.nanoTime();
		solver.solveBpcp(instance, k);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("\nTime taken to search: " + df.format(toSeconds(elapsedTime)) + " seconds");
	}

	/**
	 * Converts a given value from nanoseconds to seconds. Used for benchmarking
	 * purposes.
	 * 
	 * @param elapsedNanos
	 *            the elapsed time in nanoseconds
	 * @return the elapsed time in seconds
	 */
	private static double toSeconds(long elapsedNanos) {
		return elapsedNanos / NANOS_IN_SECOND;
	}

	/**
	 * Displays usage instructions to the user.
	 */
	private static void instructions() {
		System.out.print("*** Welcome to CS 575 Project #3: ");
		System.out.println("A Solver for the Bounded Post's Correspondence Problem ***\n");
		System.out.println("\t\t\t---------------USAGE TIPS---------------\n");
		System.out.println(
				"* Usage Tip 1: You are to enter two non-empty sets of strings and a positive integer k as 
			input.");
		System.out.println("* Usage Tip 2: The first set represents the top portion of a BPCP instance, "
				+ "and the second the bottom one.");
		System.out.println("* Usage Tip 3: Individual strings within a set are separated by whitespace. ");
		System.out.println(
				"* Usage Tip 4: If there is a solution to the given instance, this program is guaranteed to find 
			it.");
		System.out.println("\t\tHowever, depending on the length of a solution sequence, it may take an extended "
				+ "amount of time to find.\n\t\tEither way, the program will eventually halt **, as the search is 
				   bounded by k. "
				+ "\n\n** This excludes any memory defaults set by your Eclipse Java garbage collector, 
				   which can be modified at will.\n");
		System.out.println("\t\t\t-----------------------------------------\n");
	}
}
