import java.util.Objects;
import java.lang.Math;

public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double[][] masses = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char[][] image = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    public static boolean isWordSymmetric(String[] words, int start, int end){
        if(start >= end){  //if it gets to this point, it's automatically symmetric
            return true;
        } else if(Objects.equals(words[start].toLowerCase(), words[end].toLowerCase())){
            return isWordSymmetric(words, start + 1, end - 1);  //recursively check each pair of words
        } else{
            return false;
        }
    }

    public static long arraySum(int[] data, int position){
        if(position > data.length - 1){  //out of bounds
            return 0;
        } else{
            return data[position] + arraySum(data, position + 1);
        }
    }

    public static int arrayMin(int[] data, int position){
        if(position == data.length - 1){ //last item to check
            return data[position];
        } else{
            return Math.min(data[position], arrayMin(data, position + 1));
        }
    }

    public static double computePyramidWeights(double[][] masses, int row, int column){
        try{
            double attempt = masses[row][column];
            return masses[row][column] + computePyramidWeights(masses, row - 1, column - 1) / 2 + computePyramidWeights(masses,row - 1, column) / 2;
        } catch(ArrayIndexOutOfBoundsException exception){
            return 0;
        }
    }

    public static int howManyOrganisms(char[][] image){
        int numOrganisms = 0;

        //look for the starting asterisk of an organism
        for(int row = 0; row < image.length; row++){
            for(int col = 0; col < image[row].length; col++){
                if(image[row][col] == '*'){
                    findOrganism(image, (char)('a' + numOrganisms), row, col);  //find the rest of the organism
                    numOrganisms++;
                }
            }
        }
        return numOrganisms;
    }

    public static void findOrganism(char[][] image, char label, int row, int col){
        //label each piece
        image[row][col] = label;

        //check surroundings for more parts of organism
        try{
            if(image[row - 1][col] == '*'){
                findOrganism(image, label, row - 1, col);
            }
        } catch(ArrayIndexOutOfBoundsException ignored){}

        try{
            if(image[row + 1][col] == '*'){
                findOrganism(image, label, row + 1, col);
            }
        } catch(ArrayIndexOutOfBoundsException ignored){}

        try{
            if(image[row][col - 1] == '*'){
                findOrganism(image, label, row, col - 1);
            }
        } catch(ArrayIndexOutOfBoundsException ignored){}

        try{
            if(image[row][col + 1] == '*'){
                findOrganism(image, label, row, col + 1);
            }
        } catch(ArrayIndexOutOfBoundsException ignored){}
    }

}
