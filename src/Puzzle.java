public class Puzzle {
    private String name;
    private String description;
    private String solution;
    private int numAttempts;
    private int maxAttempts;


    public Puzzle(String name, String description, String solution, int numAttempts) {
        this.name = name;
        this.numAttempts = numAttempts;
        this.solution = solution;
        this.description = description;
        this.maxAttempts = numAttempts;
    }

    public void resetPuzzle() {
        this.numAttempts = maxAttempts;
    }
    public void displayPuzzle() {
        System.out.println(this.name + ":");
        System.out.println(this.description);
    }
    public int getNumAttempts() {
        return this.numAttempts;
    }

    public int solve(String answer) {
        if (answer.equalsIgnoreCase(solution)) return 1;
        else if (numAttempts > 1){
            this.numAttempts--;
            return 0;
        }
        else {
            this.numAttempts--;
            return -1;
        }
    }
}
