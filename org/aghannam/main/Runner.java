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
	private static final double NANOS_PER_SECOND = 1000000000.0;

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
			System.err.println("\nInvalid input: Both sets must not be empty. Process terminated...");
			System.exit(1);
		}

		constructInstance(first, second);
	}

	/**
	 * Builds a BPCP instance from two equal-length sets of strings, where the
	 * strings in a set are separated by whitespace.
	 * 
	 * @param first
	 *            the first (top) set of the input instance
	 * @param second
	 *            the second (bottom) set of the input instance
	 */
	private static void constructInstance(String first, String second) {
		instance = new ArrayList<Pair>();

		// For each set, discard all whitespace and add the individual words
		// to the corresponding array, forming two lists of strings.
		String[] topWords = first.split("\\s+");
		String[] bottomWords = second.split("\\s+");

		if (topWords.length != bottomWords.length) {
			System.err.println("\nInvalid input: The two sets must be of equal length. Process terminated...");
			System.exit(1);
		}

		// Now use both lists to make the actual instance
		for (int i = 0; i < topWords.length; i++) {
			instance.add(new Pair(topWords[i], bottomWords[i]));
		}
	}

	/**
	 * [Wrapper method] Tries to solve the BPCP on a specified instance. Also
	 * computes the search time in seconds or minutes, depending on the search
	 * duration.
	 */
	private static void solve() {
		DecimalFormat df = new DecimalFormat("0.00");
		Solver solver = new Solver(instance);

		// Before starting the search, let's see if the instance has a solution
		// in the first place (which is tentative!)
		if (solver.isUnsolvable(instance)) {
			System.out.println("\nThe solver has determined that the specified instance has no solution, "
					+ "irrespective of any k.");
			return; // stop if it doesn't have one
		}

		// If it does or if the above rule does not apply, try and find a
		// solution.
		long startTimestamp = System.nanoTime();
		solver.solveBpcp(instance, k);
		long elapsedTime = System.nanoTime() - startTimestamp;

		if (elapsedTime > 60 * NANOS_PER_SECOND) {
			System.out.println("\nTime taken to search: " + df.format(toSeconds(elapsedTime) / 60.0) + " minutes");
		} else {
			System.out.println("\nTime taken to search: " + df.format(toSeconds(elapsedTime)) + " seconds");
		}
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
		return elapsedNanos / NANOS_PER_SECOND;
	}

	/**
	 * Displays usage instructions to the user.
	 */
	private static void instructions() {
		System.out.print("*** Welcome to CS 575 Project #3: ");
		System.out.println("A Solver for the Bounded Post's Correspondence Problem ***\n");
		System.out.println("\t\t\t---------------USAGE TIPS---------------\n");
		System.out.println(
				"* Usage Tip 1: You are to enter two non-empty sets of strings and a positive integer k as input.");
		System.out.println("* Usage Tip 2: The first set represents the top portion of a BPCP instance, "
				+ "and the second the bottom one.");
		System.out.println("* Usage Tip 3: Individual strings within a set are separated by whitespace. ");
		System.out.println(
				"* Usage Tip 4: If there is a solution to the given instance, this program is guaranteed to find it *.");
		System.out.println("\t\tHowever, depending on the length of a solution sequence, it may take an extended "
				+ "amount of time to find.\n\t\tEither way, the program will eventually halt **, as the search is bounded by k. "
				+ "\n\n*  This solver will only return the shortest possible solution (i.e. the first one it finds)."
				+ "\n** This excludes any memory defaults or constraints set by your Java garbage collector.\n");
		System.out.println("\t\t\t-----------------------------------------\n");
	}
}
