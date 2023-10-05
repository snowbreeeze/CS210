/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {

	private List<Fragment> fragments;

	public Assembler(List<Fragment> fragments) {
		// Copy the list to ensure the original list is not modified
		this.fragments = new ArrayList<>(fragments);
	}

	public List<Fragment> getFragments() {
		return this.fragments;
	}

	public boolean assembleOnce() {
		int maxOverlap = 0;
		Fragment fragment1ToMerge = null;
		Fragment fragment2ToMerge = null;
		Fragment mergedFragment = null;

		for (int i = 0; i < fragments.size(); i++) {
			for (int j = i + 1; j < fragments.size(); j++) {
				Fragment f1 = fragments.get(i);
				Fragment f2 = fragments.get(j);

				int overlap1 = f1.calculateOverlap(f2);
				int overlap2 = f2.calculateOverlap(f1); // Check the reverse overlap

				// Determine which overlap is larger and use that for merging
				int currentMaxOverlap = Math.max(overlap1, overlap2);
				Fragment currentMergedFragment = (overlap1 >= overlap2) ? f1.mergedWith(f2) : f2.mergedWith(f1);

				if (currentMaxOverlap > maxOverlap ||
						(currentMaxOverlap == maxOverlap && (mergedFragment == null
								|| currentMergedFragment.length() < mergedFragment.length()))) {
					maxOverlap = currentMaxOverlap;
					fragment1ToMerge = f1;
					fragment2ToMerge = f2;
					mergedFragment = currentMergedFragment;
				}
			}
		}

		if (fragment1ToMerge != null && fragment2ToMerge != null && maxOverlap > 0) {
			fragments.remove(fragment1ToMerge);
			fragments.remove(fragment2ToMerge);
			fragments.add(mergedFragment);
			return true;
		}

		return false;
	}

	public void assembleAll() {
		while (assembleOnce()) {
			// Keep assembling until no more assemblies can be performed
		}
	}
}
