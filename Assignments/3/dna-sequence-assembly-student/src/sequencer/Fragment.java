/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {

	private String nucleotides;

	public Fragment(String nucleotides) throws IllegalArgumentException {
		if (!nucleotides.matches("[GCAT]+")) {
			throw new IllegalArgumentException("Invalid nucleotide sequence");
		}
		this.nucleotides = nucleotides;
	}

	public int length() {
		return nucleotides.length();
	}

	@Override
	public String toString() {
		return nucleotides;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Fragment)) {
			return false;
		}
		Fragment f = (Fragment) o;
		return nucleotides.equals(f.nucleotides);
	}

	public int calculateOverlap(Fragment f) {
		int overlap = 0;
		for (int i = 1; i <= Math.min(this.length(), f.length()); i++) {
			if (this.nucleotides.endsWith(f.nucleotides.substring(0, i))) {
				overlap = i;
			}
		}
		return overlap;
	}

	public Fragment mergedWith(Fragment f) {
		int overlap = this.calculateOverlap(f);
		return new Fragment(this.nucleotides + f.nucleotides.substring(overlap));
	}
}
