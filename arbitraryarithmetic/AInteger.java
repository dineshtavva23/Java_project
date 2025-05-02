package arbitraryarithmetic;


public class AInteger{
    public String value;

    // Constructor to initialize the AInteger object with a default value of "0"
    public AInteger() {
        this.value="0";
        }
        
    @Override
    // Prints the value of the AInteger object when it is printed
    public String toString() {
        return value;
    }


    // Contrustor to intialize the AInteger object with a given string value  
    public AInteger(String value){
        this.value = value;

    }


    // Method to check if the string representation of an integer is valid
    public static boolean validString(String s){
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("Invalid Integer string. Enter correct type."); 
                return false;            
                
            }
        }
        return true;
        
    }


    // Parse method to convert a string representation of an integer to an AInteger object
    public static AInteger parse(String s){
        return new AInteger(s);

    }
    

    // Constructor which takes an AInteger object and copies its value to the new AInteger object
    public AInteger copy(AInteger other){
        return new AInteger(other.value);

    }


    // Method to compare two string representations of integers
    public int compareString(String s1, String s2){
        s1= AInteger.removeLeadingZeros(s1);
        s2=AInteger.removeLeadingZeros(s2);
        if(s1.equals("")&&s2.equals("")){
            return 0;
        }
        if(s1.equals("")){
            return -1;
        }
        if(s2.equals("")){
            return 1;
        }


        if(s1.length()>s2.length()){
            return 1;
        }
        else if(s1.length()<s2.length()){
            return -1;
        }
        else{
            return s1.compareTo(s2);
        }
    }


    // Method to remove leading zeros from a string representation of an integer
    static String removeLeadingZeros(String s1){
        if(s1.equals("")){
            return "0";
        }
        String result="";
        boolean foundNonZero=false;
        for(int i =0;i<s1.length();i++){
        if(foundNonZero){
            result+=s1.charAt(i);
        }
        if(!foundNonZero&&(s1.charAt(i)!='0')){
            foundNonZero=true;
            result+=s1.charAt(i);
        }
        }
        if(result.equals("")){
            result = "0";
        }
        return result;
    }


   // Method to add two string representations of integers
   // Iteratively adds two strings representing integers and returns the result as a string
    public String stringAdd(String s1,String s2){
 
        String result="";
        int len1= s1.length();
        int len2=s2.length();
        if(len1>= len2){
            for(int i =0;i<len1-len2;i++){
                s2 = '0'+ s2; 
            }
        }
        else{
            for(int i =0;i<len2-len1;i++){
            s1 = '0' + s1; 
            }
        }
        // System.out.println("num1 : " + s1);
        // System.out.println("num2 : " + s2);
        int commonLength= s1.length();
        int carry =0;
        for(int i=commonLength-1;i>=0;i--){
            int sum=0;
            sum += s1.charAt(i)-'0' + s2.charAt(i)-'0' + carry;
            result = Integer.toString(sum%10) +result;
            carry = sum/10;
        }
        if(carry>0){
            result = Integer.toString(carry)+result;
        }
    return result;

    }


    // Method to subtract two string representations of integers
    // Takes Larger number as first argument and smaller number as second argument and iteratively subtracts the two strings representing integers and returns the result as a string
    public String stringSubtract(String s1,String s2){
        String result="";
        if(s1.length()>= s2.length()){
            for(int i =0;i<s1.length()-s2.length();i++){
                s2 = '0'+ s2; 
            }
        }
        else{
            for(int i =0;i<s2.length()-s1.length();i++){
                s1= '0'+ s1; 
            }
        }
        int commonLength= s1.length();
        boolean borrow = false;
        for(int i=commonLength-1;i>=0;i--){
            int diff=(s1.charAt(i)-'0') - (s2.charAt(i)-'0');
            if(borrow){
                diff-=1;
                borrow=false;
            }

            if(diff<0){
                borrow=true;
                diff+=10;
            }
            
            result= Integer.toString(diff) + result;
            

        }
        return result;
        


    }


   // Method to multiply two string representations of integers
   // Iteratively multiplies two strings representing integers and returns the result as a string
    public String stringMultiply(String s1, String s2){
        String result ="";
        for(int i=s2.length()-1;i>=0;i--){
            String currProduct ="";
            int carry=0;
            for(int j=s1.length()-1;j>=0;j--){
                int prod = carry + ((s1.charAt(j)-'0')*(s2.charAt(i)-'0'));
                currProduct= Integer.toString(prod%10) + currProduct;
                carry = prod/10;
                // System.out.println("carry on: "+carry_on);
            }
            currProduct = Integer.toString(carry)+currProduct;
            for(int k=0;k<s2.length()-i-1;k++){
                currProduct +='0';
            }
            result= stringAdd(result, currProduct);
            
            // System.out.println("curr_product : " + curr_product);
        }
        // System.out.println("result : " + result);



        return result;

    }


    // Method to divide two string representations of integers
    // Performs long division on two strings representing integers and returns the result as a string
    // Takes dividend, divisor and quotient as arguments and returns the result as a string
    public String stringDivide(String dividend, String divisor, String quotient) {
        dividend = AInteger.removeLeadingZeros(dividend);
        divisor = AInteger.removeLeadingZeros(divisor);
    
        if (divisor.equals("")|| divisor.equals("0")){
            return "Division by zero error";
        }
    
        String result = "";
        String current = "";
        // System.out.println(s1 + s2);
    
        for (int i = 0; i < dividend.length(); i++) {
            current += dividend.charAt(i);
            current = AInteger.removeLeadingZeros(current);
    
            if (compareString(current, divisor) < 0) {
                if (result.length() > 0) result+="0";
            } else {
                for (int j = 9; j >= 0; j--) {
                    String product = stringMultiply(divisor, String.valueOf(j));
                    if (compareString(product, current) <= 0) {
                        result+=j;
                        current = stringSubtract(current, product);
                        current = AInteger.removeLeadingZeros(current);
                        break;
                    }
                }
            }
        }

        return result.isEmpty() ? "0" : AInteger.removeLeadingZeros(result);
    }
    

    // Takes another AInteger object and passes its value to the stringAdd or stringSubtract method based on the sign of the value
    public AInteger add(AInteger other){
        String result="";

        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AInteger.validString(this.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        if(!AInteger.validString(other.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        

        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                result = stringAdd(this.value, other.value);
                
            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String largerValue;
                String smallerValue;
                boolean isNegative=false;
                if(compareString(this.value, other.value)>0){
                    largerValue=this.value;
                    smallerValue=other.value;
                    
                }
                else{
                    largerValue=other.value;
                    smallerValue=this.value;
                    isNegative=true;
                }
                result=stringSubtract(largerValue, smallerValue);
                if(isNegative){
                    result = '-'+result;
                }    
            }
            
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                result = stringAdd(this.value, other.value);
                result = '-'+result;
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value=other.value.substring(1);
                if(compareString(this.value, other.value)>0){
                    result = stringSubtract(this.value, other.value);
                    result = '-'+result;
                }
                else{
                    result = stringSubtract(other.value, this.value);
                }

            }
        }
        return new AInteger(result);

    }
 

    // Takes another AInteger object and passes its value to the stringAdd or StringSubtract method to add or subtract the two AInteger objects.
    public AInteger subtract(AInteger other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AInteger.validString(this.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        if(!AInteger.validString(other.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value= this.value.substring(1);
                other.value = other.value.substring(1);
                if(compareString(this.value, other.value)>0){
                    result = stringSubtract(this.value, other.value);
                }
                else{
                    result = stringSubtract(other.value, this.value);
                    result = '-' + result;
                }
            }
            else if(other.value.charAt(0)=='-'){
                this.value=this.value.substring(1);
                other.value = other.value.substring(1);
                result = stringAdd(this.value, other.value);
            }
        }
        else if(this.value.charAt(0)=='-'){
            if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value = other.value.substring(1);
                result=stringAdd(this.value, other.value);
                result = '-'+result;

            }
            else if (other.value.charAt(0)=='-'){
            this.value=this.value.substring(1);
            other.value = other.value.substring(1);
            if(compareString(this.value, other.value)>0){
                result = stringSubtract(this.value, other.value);
                result = '-' + result;
            }
            else{
                result = stringSubtract(other.value, this.value);
                
            }
            

        }
    }
        return new AInteger(result);
        

    }


    // Takes another AInteger object and passes its value to the stringMultiply method to multiply the two AInteger objects.
    public AInteger multiply(AInteger other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }

        if(!AInteger.validString(this.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        if(!AInteger.validString(other.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        
        
        
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            result = stringMultiply(this.value, other.value);

            result = '-'+result;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            result = stringMultiply(this.value, other.value);
            
        }
        return new AInteger(result);
    }

    
    // Takes another AInteger object and passes its value to the stringDivide method to divide the two AInteger objects.
    public AInteger divide(AInteger other){
        String result="";
        if(other.value.equals("")){
            return new AInteger("Division by zero error");
        }
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AInteger.validString(this.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        if(!AInteger.validString(other.value)){
            return new AInteger("Invalid type. Please Enter correct format");
        }
        
        
        
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            result = stringDivide(this.value, other.value,"");

            result= '-'+result;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            this.value = AInteger.removeLeadingZeros(this.value);
            other.value = AInteger.removeLeadingZeros(other.value);
            // System.out.println("given values are "+this.value+other.value);

            result = stringDivide(this.value, other.value,"");
            
            
        }
        // if(result.equals("Division by zero"))
        return new AInteger(result);

    }


}
