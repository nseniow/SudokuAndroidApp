package com.example.sudokusolve;


public class Puzzle {


    private int[][] arr;

    public Puzzle(int[][] arr) {
        this.arr = arr;
    }


    boolean solve() {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(arr[i][j]==0) {
                    for(int g=1;g<=9;g++) {
                        if(check(i,j,g)) {
                            arr[i][j] = g;

                            if(solve()) {
                                return true;
                            }
                        }
                    }
                    arr[i][j] = 0;
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check(int row, int col, int guess) {
        return checkV(row, col, guess) &&
                checkH(row, col, guess) &&
                checkSqr(row, col, guess);
    }

    private boolean checkV(int row, int col, int guess) {
        for(int i=0;i<9;i++) {
            if(arr[i][col]==guess) {
                return false;
            }
        }
        return true;
    }

    private boolean checkH(int row, int col, int guess) {
        for(int j=0;j<9;j++) {
            if(arr[row][j]==guess) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSqr(int row, int col, int guess) {
        int I = row/3*3;
        int J = col/3*3;

        for(int i=I;i<I+3;i++) {
            for(int j=J;j<J+3;j++) {
                if(arr[i][j] == guess) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean verify(){ //Make sure that the array does not already break the rules of sudoku as this will cause the algorithm to hang
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (arr[i][j] != 0) {
                    int tempStore = arr[i][j];
                    arr[i][j] = 0;

                    if(tempStore<0||tempStore>9){
                        return false;
                    }

                    if(check(i,j,tempStore)){
                        arr[i][j] = tempStore;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    void display() {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(j==3||j==6) {
                    System.out.print("| ");
                }
                System.out.print(arr[i][j]==0?"  ":arr[i][j] + " ");

            }
            System.out.println();
            if(i==2||i==5) {
                System.out.println("--------------------");
            }
        }
    }

    int[][] getArray(){
        return arr;
    }
}

