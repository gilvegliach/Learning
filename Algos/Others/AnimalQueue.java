public class AnimalQueue {
	static class Node<T extends Animal> {
		T animal;
		Node<? extends Animal> prevOldest;
		Node<? extends Animal> nextOldest;
		Node<T> nextSameKind;
		
		Node(T animal) {
			this.animal = animal;
		}
	}

	Node<Dog> headDogs;
	Node<Dog> tailDogs;
	Node<Cat> headCats;
	Node<Cat> tailCats;
	Node<? extends Animal> headOldest;
	Node<? extends Animal> tailOldest;
	
	public static void main(String[] args) {
		AnimalQueue queue = new AnimalQueue();
		queue.enqueueAny(new Cat("1"));
		queue.enqueueAny(new Dog("2"));
		queue.enqueueAny(new Cat("3"));
		queue.enqueueAny(new Dog("4"));
		queue.enqueueAny(new Cat("5"));
		
		Animal curr = queue.dequeueDog();
		while (curr != null) {
			System.out.println(curr.toString());
			curr = queue.dequeueDog();
		}
		
		curr = queue.dequeueCat();
		while (curr != null) {
			System.out.println(curr.toString());
			curr = queue.dequeueCat();
		}
	}
	
	Dog dequeueDog() {
		if (headDogs == null) {
			return null;
		}
		Dog dog = headDogs.animal;
		if (headDogs == headOldest) {
			advanceHeadOldest();
		} else {
			removeFromOldest(headDogs);
		}
		advanceHeadDogs();
		return dog;
	}
	
	Cat dequeueCat() {
		if (headCats == null) {
			return null;
		}
		Cat cat = headCats.animal;
		if (headCats == headOldest) {
			advanceHeadOldest();
		} else {
			removeFromOldest(headCats);
		}
		advanceHeadCats();
		return cat;
	}
	
	Animal dequeueAny() {
		if (headOldest == null) {
			return null;
		}
		Animal animal = headOldest.animal;
		advanceHeadOldest();
		if (animal instanceof Cat) {
			advanceHeadCats();
		} else {
			advanceHeadDogs();
		}
		return animal;
	}
	
	void enqueueAny(Animal animal) {
		if (tailOldest == null) {
			if (animal instanceof Cat) {
				headCats = new Node<Cat>((Cat) animal);
				tailCats = headCats;
				headOldest = headCats;
				tailOldest = headCats;
			} else {
				headDogs = new Node<Dog>((Dog) animal);
				tailDogs = headDogs;
				headOldest = headDogs;
				tailOldest = headDogs;
			}
			return;
		} 
		
		Node<? extends Animal> newNode;
		if (animal instanceof Cat) {
			newNode = new Node<Cat>((Cat)animal);
			if (tailCats == null) {
				headCats = (Node<Cat>)newNode;
				tailCats = (Node<Cat>)newNode;
			} else {
				tailCats.nextSameKind = (Node<Cat>)newNode;
				tailCats = (Node<Cat>)newNode;
			}
		} else {
			newNode = new Node<Dog>((Dog)animal);
			if (tailDogs == null) {
				headDogs = (Node<Dog>)newNode;
				tailDogs = (Node<Dog>)newNode;
			} else {
				tailDogs.nextSameKind = (Node<Dog>)newNode;
				tailDogs = (Node<Dog>)newNode;
			}
		}
		tailOldest.nextOldest = newNode;
		newNode.prevOldest = tailOldest;
		tailOldest = newNode;
	}
	
	private void advanceHeadOldest() {
		headOldest = headOldest.nextOldest;
		if (headOldest != null) {
			headOldest.prevOldest = null;
		} else {
			tailOldest = null;
		}
	}
	
	private void advanceHeadDogs() {
		headDogs = headDogs.nextSameKind;
		if (headDogs == null) {
			tailDogs = null;
		}
	}
	
	private void advanceHeadCats() {
		headCats = headCats.nextSameKind;
		if (headCats == null) {
			tailCats = null;
		}
	}
	
	private void removeFromOldest(Node<? extends Animal> node) {
		Node<? extends Animal> prevOldest = node.prevOldest;
		Node<? extends Animal> nextOldest = node.nextOldest;
		prevOldest.nextOldest = nextOldest;
		if (nextOldest != null) {
			nextOldest.prevOldest = prevOldest;
		} else {
			tailOldest = prevOldest;
		}
	}
}

abstract class Animal {
	final String name;
	
	Animal(String name) {
		this.name = name;
	}
	
	final String getName() {
		return name;
	}
	
	abstract String getTypeName();
	
	@Override
	public String toString() {
		return getTypeName() + "{name=" + name + "}";
	}
}

class Cat extends Animal {
	Cat(String name) {
		super(name);
	}
	
	String getTypeName() {
		return "Cat";
	}
}

class Dog extends Animal {	
	Dog(String name) {
		super(name);
	}
	
	String getTypeName() {
		return "Dog";
	}
}