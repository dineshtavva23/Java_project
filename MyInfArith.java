import arbitraryarithmetic.AFloat;
import arbitraryarithmetic.AInteger;

class MyInfArith {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid Input");
            return;
        }

        String type = args[0].toLowerCase();
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];

        switch (type) {
            case "int":
                AInteger int1 = new AInteger(num1);
                AInteger int2 = new AInteger(num2);
                switch (operation) {
                    case "add":
                        System.out.println(int1.add(int2).value);
                        break;
                    case "sub":
                        System.out.println(int1.subtract(int2).value);
                        break;
                    case "mul":
                        System.out.println(int1.multiply(int2).value);
                        break;
                    case "div":
                        System.out.println(int1.divide(int2).value);
                        break;
                    default:
                        System.out.println("Invalid Operation. Try again");
                }
                break;

            case "float":
                AFloat float1 = new AFloat(num1);
                AFloat float2 = new AFloat(num2);
                switch (operation) {
                    case "add":
                        System.out.println(float1.add(float2).value);
                        break;
                    case "sub":
                        System.out.println(float1.subtract(float2).value);
                        break;
                    case "mul":
                        System.out.println(float1.multiply(float2).value);
                        break;
                    case "div":
                        System.out.println(float1.divide(float2).value);
                        break;
                    default:
                        System.out.println("Invalid Operation. Try again");
                }
                break;

            default:
                System.out.println("Invalid data type. Use either [int] or [float]");
        }
    }
}
