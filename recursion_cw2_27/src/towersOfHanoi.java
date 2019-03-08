/*
    Principle:
        - 3 rods and a number of disks of different sizes, which can slide onto any rod.
        - starts with the disks in a neat stack in ascending order of size on one rod, the smallest at the top
        - objective is to move the entire stack to another rod, obeying the following rules:
            - Only one disk can be moved at a time.
            - Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.
            - No disk may be placed on top of a smaller disk.
        - The minimal number of moves required is 2n − 1, where n is the number of disks.
    Thoughts:
        - 2 types of moves:
            - move n-1 disks to other peg
                - move top disk to other peg is n-1 is odd, or to dest peg if even
            - then move the 1 bottom disk to dest
 */

public class towersOfHanoi {
    public static void displaySolution(int N) {

    }
}
