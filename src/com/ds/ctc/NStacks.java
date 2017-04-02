// Refer https://www.youtube.com/watch?v=DxW7VAsdX0o for explaination
// or search "Interview Question: N Stacks" by byte By byte on yt

public class NStacks {
	
	private int[] topOfStack; // = {-1,-1, -1};
	private int[] stack; // = {0,0,0,0,0,0};
	private int[] nextOrPrevIndex; // = {1, 2, 3, 4, 5, -1}; = stackSize and points to next available index for each element initially
	private int nextAvailable = 0;
	
	public NStacks(int numOfStacks, int capacity) {
		topOfStack = new int[numOfStacks];
		for (int i = 0; i < topOfStack.length; i++) {
			topOfStack[i] = -1;
		}
		stack = new int[capacity];
		nextOrPrevIndex = new int[capacity];
		for (int i = 0; i < nextOrPrevIndex.length-1; i++) {
			 nextOrPrevIndex[i] = i+1;
		}
		nextOrPrevIndex[nextOrPrevIndex.length - 1] = -1;
	}
	
	public void push(int stackNum, int value) throws Exception {
		if (stackNum < 0 || stackNum >= topOfStack.length) {
			throw new IndexOutOfBoundsException();
		}
		if (nextAvailable < 0) return; //stack is full

		int currentIndex = nextAvailable;
		stack[currentIndex] = value;
		nextAvailable = nextOrPrevIndex[currentIndex];
		nextOrPrevIndex[currentIndex] = topOfStack[stackNum];
		topOfStack[stackNum] = currentIndex;
	}

	public int pop(int stackNum) throws Exception {
		if (stackNum < 0 || stackNum >= topOfStack.length) {
			throw new IndexOutOfBoundsException();
		}
		
		if (topOfStack[stackNum] < 0) return -1; //stack empty;
	
		int currentIndex = topOfStack[stackNum];
		int value = stack[currentIndex];
		topOfStack[stackNum] = nextOrPrevIndex[currentIndex];
		nextOrPrevIndex[currentIndex] = nextAvailable;
		nextAvailable = currentIndex;
		return value;
	} 
	
	public static void main(String[] args) throws Exception {
		NStacks stacks = new NStacks(3, 10);
		stacks.push(0, 13);
		stacks.push(1, 22);
		stacks.push(2, 33);
		stacks.push(0, 12);
		stacks.push(0, 14);
		stacks.push(1, 23);
		stacks.push(2, 32);
		
		System.out.println(stacks.pop(1));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(2));
	}
}
