/*
 * CS 575: Project #3
 * File: Pair.java
 */
package org.aghannam.bpcp;

/**
 * This class represents a corresponding pair of a BPCP instance.
 * <p>
 * Intuitively, a corresponding pair resembles a numerical fraction, with one
 * string at the top and another at the bottom. A pair is also known as a domino
 * or a tile in some textbooks. A typical input instance is a list of one or
 * more such pairs.
 * 
 * @author Ahmed Ghannam (amalghannam@crimson.ua.edu)
 */
public class Pair {
	protected String top;
	protected String bottom;

	/**
	 * Constructs a new corresponding pair of a BPCP instance, given two
	 * strings.
	 * 
	 * @param top
	 *            the top string of the pair
	 * @param bottom
	 *            the bottom string of the pair
	 */
	public Pair(String top, String bottom) {
		this.top = top;
		this.bottom = bottom;
	}
}
