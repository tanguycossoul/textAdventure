/*
    Principle:
        - 3 rods and a number of disks of different sizes, which can slide onto any rod.
        - starts with the disks in a neat stack in ascending order of size on one rod, the smallest at the top
        - objective is to move the entire stack to another rod, obeying the following rules:
            - Only one disk can be moved at a time.
            - Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.
            - No disk may be placed on top of a smaller disk.
        - The minimal number of moves required is 2n − 1, where n is the number of disks.
 */

//public class towersOfHanoi {
//    public static void displaySolution( int N) {
//        if (N is too big or too small then ask again){
//          check if there is a move you can make where you move a disk on top of another one
//          else{
//         randomly choose a valid move ( ontop of the smallest disk that is still larger than the one you are trying to move
//        }
//    }
//}
