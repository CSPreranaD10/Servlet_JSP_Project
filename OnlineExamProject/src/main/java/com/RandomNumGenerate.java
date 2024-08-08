package com;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumGenerate {

	static Set<Integer> qsno = new LinkedHashSet<Integer>();
	Random random = new Random();

	public int numNotZero(int n) {
		int num = n;
		if (num == 0) {
			num = random.nextInt(10);
			num = numNotZero(num);
		}
		return num;
	}

	public int checkQNo() {
		System.out.println("Inside the checkQNo method ");

		int no = random.nextInt(10);
		no = numNotZero(no);
		System.out.println("Random number generated is " + no);
		Iterator<Integer> it = qsno.iterator();
		while (it.hasNext()) {
			int num = it.next();
			System.out.println("Value of num inside iterator" + num);
			if (no == num) {
				System.out.println("Numbers matched");
				no = numNotZero(no);
				System.out.println("new random number is " + no);
			}
		}
		System.out.println("number not found");
		qsno.add(no);
		return no;

	}
}
