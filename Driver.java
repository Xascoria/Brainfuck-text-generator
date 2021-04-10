import java.util.*;

public class Driver {
    public static void main(String[] args){
        System.out.println(get_bf_program("Example"));

    }

    public static String get_bf_program(String original_str){
        int[] stack = new int[]{70, 70, 70, 70, 105, 105, 105, 105, 35};
        int pointer = 8;

        //Create the above stack
        StringBuilder program = new StringBuilder(
                ">>>>>>+++++++[->+++++<]>[->+>+++>++<<<]>>[-<<+<+<+<+>>>>>]>[-<<<<<<<+<+<+<+>>>>>>>>>>]<<"
        );

        for (int i = 0; i < original_str.length(); i++){
            ArrayList this_move = get_next_move(stack, pointer, original_str.charAt(i));
            stack = (int[]) this_move.get(0);
            pointer = (int) this_move.get(1);
            program.append(this_move.get(2));
        }

        return program.toString();
    }

    public static ArrayList get_next_move(int[] stack, int pointer, int target_value){
        int[] working_stack = stack.clone();
        int current_diff = 255;
        int closest_index = 0;

        for (int i = 0; i < stack.length; i++){
            //Find the least difference value
            if (Math.abs(target_value - stack[i]) <= Math.abs(current_diff)){
                //If it's further away and with the same difference, skip it
                if (Math.abs(target_value - stack[i]) == Math.abs(current_diff)
                        && Math.abs(i-pointer) > Math.abs(closest_index-pointer) ){
                    continue;
                }
                closest_index = i;
                current_diff = target_value - stack[i];
            }
        }

        int working_index = closest_index;
        StringBuilder new_line = new StringBuilder();
        while (closest_index != pointer){
            if (closest_index < pointer){
                new_line.append('<');
                closest_index += 1;
            } else if (closest_index > pointer){
                new_line.append('>');
                closest_index -= 1;
            }
        }

        while (current_diff != 0) {
            if (current_diff > 0) {
                new_line.append('+');
                working_stack[working_index]++;
                current_diff--;
            } else if (current_diff < 0) {
                new_line.append('-');
                working_stack[working_index]--;
                current_diff++;
            }

        }

        new_line.append('.');

        //Format: New stack(int[]), new pointer(int), new command(String)
        ArrayList output = new ArrayList();
        output.add(working_stack);
        output.add(working_index);
        output.add(new_line.toString());

        return output;
    }






}











