package automaton641.art;
import java.util.Random;

import automaton641.art.DirectionNode.Direction;


public class DirectionGraph {
	public DirectionNode first;
	public DirectionNode last;
	public int size;
	public Random random;
	public DirectionGraph() {
	}
	public DirectionGraph(Random random) {
		this.random = random;
	}
	public DirectionGraph(Random random, int size, int repetitions) {
		this.random = random;
		this.randomize(size, App.modulus, repetitions);
	}
	public void addDirection(Direction direction) {
		this.size++;
		DirectionNode directionNode = new DirectionNode();
		directionNode.direction = direction;
		if (first == null) {
			directionNode.next = directionNode;
			directionNode.previous = directionNode;
			first = directionNode;
			last = directionNode;
		} else if (first == last) {
			last = directionNode;
			first.next = last;
			first.previous = last;
			last.next = first;
			last.previous = first;
		} else {
			last.next = directionNode;
			first.previous = directionNode;
			directionNode.previous = last;
			directionNode.next = first;
			last = directionNode;
		}
	}
	public void randomize(int size, int modulus, int repetitions) {
		for (int i = 0; i < size; i++) {
			this.addDirection(DirectionNode.generateRandomDirection(random));
		}
		for (int j = 0; j < repetitions-1; j++) {
			DirectionNode node = first;
			for (int i = 0; i < size; i++) {
				this.addDirection(node.direction);
				node = node.next;
			}
		}
	}
}
