package arbitraryarithmetic;

public class AFloat {
    public String value;

    public AFloat(){
        this.value="0.0";
    }
  
    public AFloat(String value){
        this.value=value;
    }

    @Override
    // Prints the string representation of the float number
    public String toString(){
        return this.value;
    }  


    // Check if the string is a valid float number
    public static boolean validString(String s){
        int len = s.length();
        boolean seenDot = false;
        boolean seenDigit = false;
        // Check optional sign

        for (int i=1; i < len; i++) {
            char c = s.charAt(i);

            if (c == '.') {
                if (seenDot) {
                    return false;
                }
                seenDot = true;
            } else if (c >= '0' && c <= '9') {
                seenDigit = true;
            } else {
                return false;
            }
        }

        if (!seenDigit) {
            return false;
        }
        return true;
    
    }
   

    // Takes a string and returns an AFloat object with the value of the string
    public AFloat parse(String s){
        return new AFloat(s);
        
    }


   // Takes another AFloat object and returns a new AFloat object with the same value
   public AFloat copy(AFloat other){
    return new AFloat(other.value);
   }
   

   // Gives the Integer part of the string representation of the float number
   public String intPart(){
        int dotIndex = (this.value.indexOf('.')!=-1)? this.value.indexOf('.'):this.value.length() ;
        return this.value.substring(0,dotIndex);
    }


   // Gives the decimal part of the string representation of the float number
    public String float_part(){
        int dotIndex = (this.value.indexOf('.')!=-1)? this.value.indexOf('.'):this.value.length();
        return this.value.substring(dotIndex);

    }
 

    // Remove leading and trailing zeros from the string representation of the float number
    static String removeZeros(String s1){
        if(s1.equals("")){
            return "0";
        }

        String IntPart = "";
        String decimalPart = "";
        
        int dotIndex = s1.indexOf('.');
        
        if (dotIndex != -1) {
            IntPart = s1.substring(0, dotIndex);
            decimalPart = s1.substring(dotIndex + 1);
        } else {
            IntPart = s1;
        }
        int i = 0;
        while (i < IntPart.length() && IntPart.charAt(i) == '0') i++;
        IntPart = (i == IntPart.length()) ? "0" : IntPart.substring(i);
        
        int j = decimalPart.length() - 1;
        while (j >= 0 && decimalPart.charAt(j) == '0') j--;
        decimalPart = decimalPart.substring(0, j + 1);

        if (decimalPart.isEmpty()) {
            return IntPart;
        } else {
            return IntPart + "." + decimalPart;
        }
        
    }


    // compare two string integers and return 1 if s1>s2, -1 if s1<s2 and 0 if they are equal
    public int compareStringIntegers(String s1,String s2){
        s1 = removeZeros(s1);
        s2 = removeZeros(s2);

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
   

    // Shift the decimal point of a float string to the right by a specified number of places
    public static String shiftDecimalPointRight(String floatStr, int shiftRightBy) {
        if (floatStr == null || floatStr.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        int decimalIndex = floatStr.indexOf('.');
        if (decimalIndex == -1) {
            return floatStr; 
        }

        StringBuilder sb = new StringBuilder(floatStr.replace(".", "")); 
        int newDecimalPosition = decimalIndex + shiftRightBy;

        if (newDecimalPosition < 0) {
            int zerosToAdd = -newDecimalPosition;
            for (int i = 0; i < zerosToAdd; i++) {
                sb.insert(0, '0');
            }
            sb.insert(0, '.');
        } else if (newDecimalPosition > sb.length()) {
            
            int zerosToAdd = newDecimalPosition - sb.length();
            for (int i = 0; i < zerosToAdd; i++) {
                sb.append('0');
            }
            sb.append('.');
        } else if (newDecimalPosition == sb.length()) {
            sb.append('.');
        } else {
            sb.insert(newDecimalPosition, '.');
        }

        return sb.toString();
    }


    // Add two string integers and return the result as a string
    public String addStringIntegers(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int len1 = s1.length();
        int len2 = s2.length();
    
        
        if (len1 >= len2) {
            StringBuilder paddedNum2 = new StringBuilder(s2);
            for (int i = 0; i < len1 - len2; i++) {
                paddedNum2.insert(0, '0');
            }
            s2 = paddedNum2.toString();
        } else {
            StringBuilder paddedNum1 = new StringBuilder(s1);
            for (int i = 0; i < len2 - len1; i++) {
                paddedNum1.insert(0, '0');
            }
            s1 = paddedNum1.toString();
        }
    
        int commonLength = s1.length();
        int carry = 0;
    
        for (int i = commonLength - 1; i >= 0; i--) {
            int digitSum = (s1.charAt(i) - '0') + (s2.charAt(i) - '0') + carry;
            result.insert(0, digitSum % 10);
            carry = digitSum / 10;
        }
    
        if (carry > 0) {
            result.insert(0, carry);
        }
    
        return result.toString();
    }
    

    // Subtract two string integers and return the result as a string
    public String subtractStringIntegers(String s1, String s2) {
        StringBuilder result = new StringBuilder();
    
        // Pad the shorter string with leading zeros
        if (s1.length() >= s2.length()) {
            StringBuilder paddedNum2 = new StringBuilder(s2);
            for (int i = 0; i < s1.length() - s2.length(); i++) {
                paddedNum2.insert(0, '0');
            }
            s2 = paddedNum2.toString();
        } else {
            StringBuilder paddedNum1 = new StringBuilder(s1);
            for (int i = 0; i < s2.length() - s1.length(); i++) {
                paddedNum1.insert(0, '0');
            }
            s1 = paddedNum1.toString();
        }
    
        int commonLength = s1.length();
        boolean borrow = false;
    
        for (int i = commonLength - 1; i >= 0; i--) {
            int diff = (s1.charAt(i) - '0') - (s2.charAt(i) - '0');
            if (borrow) {
                diff -= 1;
                borrow = false;
            }
    
            if (diff < 0) {
                borrow = true;
                diff += 10;
            }
    
            result.insert(0, diff);
        }
    
        return result.toString();
    }
    

    // Multiply two string integers and return the result as a string
    public String multiplyStringIntegers(String s1, String s2) {
        String result = "0";
    
        for (int i = s2.length() - 1; i >= 0; i--) {
            StringBuilder currProduct = new StringBuilder();
            int carry = 0;
    
            for (int j = s1.length() - 1; j >= 0; j--) {
                int prod = carry + ((s1.charAt(j) - '0') * (s2.charAt(i) - '0'));
                currProduct.insert(0, prod % 10);
                carry = prod / 10;
            }
    
            if (carry > 0) {
                currProduct.insert(0, carry);
            }
    
            // Add trailing zeros corresponding to the position of the digit in s2
            for (int j = 0; j < s2.length() - 1 - i; j++) {
                currProduct.append('0');
            }
    
            result = addStringIntegers(result, currProduct.toString());
        }
    
        return result;
    }
    

    // Divide two string integers and return the result as a float string
    public String divideStringIntegers(String dividend, String divisor) {
        dividend = AInteger.removeLeadingZeros(dividend);
        divisor = AInteger.removeLeadingZeros(divisor);

        
    
        StringBuilder quotient = new StringBuilder();
        StringBuilder remainder = new StringBuilder();
    
        for (int i = 0; i < dividend.length(); i++) {
            remainder.append(dividend.charAt(i));
            String currentStr = AFloat.removeZeros(remainder.toString());
            remainder.setLength(0);
            remainder.append(currentStr);
    
            if (compareStringIntegers(remainder.toString(), divisor) < 0) {
                if (quotient.length() > 0) quotient.append('0');
            } else {
                for (int j = 9; j >= 0; j--) {
                    String product = multiplyStringIntegers(divisor, String.valueOf(j));
                    product = AFloat.removeZeros(product);
    
                    if (compareStringIntegers(product, remainder.toString()) <= 0) {
                        quotient.append(j);
                        String newCurrent = subtractStringIntegers(remainder.toString(), product);
                        newCurrent = AFloat.removeZeros(newCurrent);
                        remainder.setLength(0);
                        remainder.append(newCurrent);
                        break;
                    }
                }
            }
        }
    
        // System.out.println(result.toString());
        // System.out.println(current.toString());
        
        quotient.append('.');
        // System.out.println(quotient);
    
        if (!remainder.toString().equals(".")) {
            int precisionLimit = 30;
            int decimalDigits = 0;
            while (decimalDigits < precisionLimit && !remainder.toString().equals("0")) {
                // System.out.println(remainder);
                remainder.append('0');
    
                if (compareStringIntegers(remainder.toString(), divisor) < 0) {
                    quotient.append('0');
                    decimalDigits++;
                } else {
                    // System.out.println("remainder: "+re);
                    for (int j = 9; j >= 0; j--) {
                        String product = multiplyStringIntegers(divisor, String.valueOf(j));
                        // System.out.println(product);
                        product = AInteger.removeLeadingZeros(product);
                        String trimmedRemainder = AInteger.removeLeadingZeros(remainder.toString());
                        // System.out.println(string_compare_int(product, current.toString()));
                        if (compareStringIntegers(product, trimmedRemainder) <= 0) {
                            quotient.append(j);
                            // System.out.println(j);
                            decimalDigits++;
                            // System.out.println("trimmedRemainder : "+trimmedRemainder +" product: "+product);
                            String newCurrent = subtractStringIntegers(trimmedRemainder, product);
                            // System.out.println("new current: "+newCurrent);
                            newCurrent = AInteger.removeLeadingZeros(newCurrent);
                            remainder.setLength(0);
                            // System.out.println(current.toString());
                            remainder.append(newCurrent);
                            // System.out.println(remainder);
                            break;
                        }
                    }
                }
            }
        }
    
        if (quotient.charAt(0) == '.') {
            quotient.insert(0, '0');
        }
    
        return quotient.length() == 0 ? "0" : AInteger.removeLeadingZeros(quotient.toString());
    }
    

    // Add two string float numbers and return the result as a string
    public String addStringFloat(String s1,String s2){
        // System.out.println("num1 : " + s1);
        // System.out.println("num2 : " + s2);
        
        int decimalIndex1 = (s1.indexOf('.')==-1)? s1.length(): s1.indexOf('.') ;
        int decimalIndex2 = (s2.indexOf('.') ==-1)? s2.length() : s2.indexOf('.');
        int decimalPlaces1 = s1.length() - decimalIndex1;
        int decimalPlaces2 = s2.length() - decimalIndex2;
        // System.out.println(decimalIndex1+" "+decimalIndex2);
        if(decimalPlaces1>=decimalPlaces2){
            // System.out.println("hi");
            for(int i=0;i<decimalPlaces1-decimalPlaces2;i++){
                s2+='0';
            }
            // System.out.println(s2);
        }
        else{
            for(int i=0;i<decimalPlaces2-decimalPlaces1;i++){
                s1+='0';
            }
        }
        if(decimalIndex1 >= decimalIndex2){
            for (int i = 0; i < decimalIndex1-decimalIndex2; i++) {
                s2 = '0'+s2;
            }
        }
        else{
            for (int i=0;i<decimalIndex2-decimalIndex1;i++){
                s1='0'+s1;
            }
        }
        // System.out.println(s1 +" "+s2);
        
        String result="";
        
        int commonLength= s1.length();
        int carry =0;
        for(int i=commonLength-1;i>=0;i--){
            if(s1.charAt(i)!='.'&&s2.charAt(i)!='.'){
            int digitSum=0;
            digitSum += s1.charAt(i)-'0' + s2.charAt(i)-'0' + carry;
            result = Integer.toString(digitSum%10) +result;
            carry = digitSum/10;
        }
        else{
            result = '.' +result;
        }
        }
        if(carry>0){
            result = Integer.toString(carry)+result;
        }
        return result;   
        
    }


   // Subtract two string float numbers and return the result as a string
    public String subtractStringFloat(String s1,String s2){
        int decimalIndex1 = (s1.indexOf('.')==-1)? s1.length(): s1.indexOf('.') ;
        int decimalIndex2 =(s2.indexOf('.') ==-1)? s2.length() : s2.indexOf('.');
        int decimalPlaces1 = s1.length() - decimalIndex1;
        int decimalPlaces2 = s2.length() - decimalIndex2;
        if(decimalPlaces1>=decimalPlaces2){
            for(int i=0;i<decimalPlaces1-decimalPlaces2;i++){
                s2+='0';
            }
        }
        else{
            for(int i=0;i<decimalPlaces2-decimalPlaces1;i++){
                s1+='0';
            }
        }
        if(decimalIndex1 >= decimalIndex2){
            for (int i = 0; i < decimalIndex1-decimalIndex2; i++) {
                s2 = '0'+s2;
            }
        }
        else{
            for (int i=0;i<decimalIndex2-decimalIndex1;i++){
                s1='0'+s1;
            }
        }
        // System.out.println(s1+ " "+s2);
        String result="";
        int commonLength= s1.length();
        boolean borrow = false;
        for(int i=commonLength-1;i>=0;i--){
            if(s1.charAt(i)!='.'&&s2.charAt(i)!='.'){
            int diff=0;
            diff += (s1.charAt(i)-'0') - (s2.charAt(i)-'0');
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
            else{
                result ='.' +result;
            }

        }
        return result;


    }
   

    // Multiply two string float numbers and return the result as a string
    public String multiplyStringFloat(String s1,String s2){
        String result="";
        int decimalIndex1=s1.indexOf('.');
        int decimalIndex2=s2.indexOf('.');
        String num1;
        String num2;

        if(decimalIndex1!=-1){
            num1 = s1.substring(0,decimalIndex1)+ s1.substring(decimalIndex1+1);

        }
        else{
            decimalIndex1=s1.length();
            num1=s1;
        }
        if(decimalIndex2!=-1){
            num2 = s2.substring(0,decimalIndex2)+s2.substring(decimalIndex2+1);
        }
        else{
            decimalIndex2=s2.length();
            num2=s2;
        }
        int decimalPlaces1 = s1.length() - decimalIndex1;
        int decimalPlaces2 = s2.length() - decimalIndex2;
        // String num1 = s1.substring(0,first_decimal)+ s1.substring(first_decimal+1);
        // String num2 = s2.substring(0,second_decimal)+s2.substring(second_decimal+1);
        // System.out.println("num1: "+num1+"num2: "+num2);

        
        for(int i=num2.length()-1;i>=0;i--){
            String currProduct ="";
            int carry=0;
            for(int j=num1.length()-1;j>=0;j--){
                int temp = carry + ((num1.charAt(j)-'0')*(num2.charAt(i)-'0'));
                currProduct= Integer.toString(temp%10) + currProduct;
                carry = temp/10;
                // System.out.println("carry on: "+carry_on);
            }
            currProduct = Integer.toString(carry)+currProduct;
            for(int j=0;j<s2.length()-i-1;j++){
                currProduct +='0';
            }
            result= addStringIntegers(result, currProduct);
            
            // System.out.println("curr_product : " + curr_product);
        }
        // System.out.println("result : " + result);
        int resultLen = result.length();
        StringBuilder sb = new StringBuilder(result);
        int newDotPos = resultLen- (decimalPlaces1+decimalPlaces2) +1;
        if(newDotPos>resultLen){
            newDotPos= newDotPos-1;
        }
        sb.insert(newDotPos, '.');

        result = sb.toString();
        


        return  AFloat.removeZeros(result);
        
    }
   

    // Divide two string float numbers and return the result as a string
    public String divideStringFloat(String s1, String s2) {
    String result;
    if (s2.equals("")||s2.equals("0")) {
        return "Division by zero error";
    }
    int decimalIndex1 = s1.indexOf('.');
    int decimalIndex2 = s2.indexOf('.');

    String num1;
    String num2;

    // Remove decimal points from the numbers
    if(decimalIndex1!=-1){
        num1 = s1.substring(0,decimalIndex1)+ s1.substring(decimalIndex1+1);

    }
    else{
        decimalIndex1=s1.length()-1;
        num1=s1;
    }
    if(decimalIndex2!=-1){
        num2 = s2.substring(0,decimalIndex2)+s2.substring(decimalIndex2+1);
    }
    else{
        decimalIndex2=s2.length()-1;
        num2=s2;
    }
    

    // System.out.println("num1:" + num1 + " num2: " + num2);

    int decimalPlaces1 = s1.length() - decimalIndex1;
    int decimalPlaces2 = s2.length() - decimalIndex2;

    // Perform integer division (without decimals)
    result = divideStringIntegers(num1, num2);
    // System.out.println(result);

    // Shift the decimal point based on the difference in decimal places
    if(result.equals("Division by zero error")){
        return result;
    }
    result = shiftDecimalPointRight(result, decimalPlaces2 - decimalPlaces1);

    return removeZeros(result);
}
 

    // Takes another AFloat object apply addStringFloat or subtractStringFloat on their values  for addition or subtraction
    public AFloat add(AFloat other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AFloat.validString(this.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(!AFloat.validString(other.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                result = addStringFloat(this.value, other.value);
                
            
            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String largerValue;
                String smallerValue;
                boolean isNegative=false;
                if(compareStringIntegers(this.intPart(), other.intPart())>0){
                    largerValue=this.value;
                    smallerValue=other.value;
                    
                }
                else if(compareStringIntegers(this.intPart(),other.intPart())==0){
                    if(compareStringIntegers(this.float_part(), other.float_part())>=0){
                        largerValue=this.value;
                        smallerValue=other.value;
                    }
                    else{
                        largerValue=other.value;
                        smallerValue=this.value;
                        isNegative=true;
                    }
                }
                else{
                    largerValue=other.value;
                    smallerValue=this.value;
                    isNegative=true;
                }
                result=subtractStringFloat(largerValue, smallerValue);
                
                if(isNegative){
                    result = '-'+result;
                }
                
            }
            
            
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                result = addStringFloat(this.value, other.value);
                result = '-'+result;
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value=other.value.substring(1);
                if(compareStringIntegers(this.value, other.value)>0){
                    result = subtractStringFloat(this.value, other.value);
                    result = '-'+result;
                }
                else{
                    result = subtractStringFloat(other.value, this.value);
                }

            }
        }
        result = removeZeros(result);
        if (result.equals("")||result.equals("0.")||result.equals("0")||result.equals("-0")||result.equals("-0.")||result.equals("-0.0")) {
            result = "0.0";
        }
        return new AFloat(result);

    }


   // Takes another AFloat object apply addStringFloat or subtractStringFloat on their values  for addition or subtraction
    public AFloat subtract(AFloat other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AFloat.validString(this.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(!AFloat.validString(other.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String largerValue;
                String smallerValue;
                boolean isNegative=false;
                if(compareStringIntegers(this.intPart(), other.intPart())>0){
                    largerValue=this.value;
                    smallerValue=other.value;
                    
                }
                else if(compareStringIntegers(this.intPart(),other.intPart())==0){
                    if(compareStringIntegers(this.float_part(), other.float_part())>=0){
                        largerValue=this.value;
                        smallerValue=other.value;
                    }
                    else{
                        largerValue=other.value;
                        smallerValue=this.value;
                        isNegative=true;
                    }
                }
                else{
                    largerValue=other.value;
                    smallerValue=this.value;
                    isNegative=true;
                }
                result=subtractStringFloat(largerValue, smallerValue);
                // System.out.println("diff: "+result);
                if(isNegative){
                    result = '-'+result;
                }

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                result = addStringFloat(this.value, other.value);
                
            
            }
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String largerValue;
                String smallerValue;
                boolean isNegative=false;
                if(compareStringIntegers(this.intPart(), other.intPart())>0){
                    largerValue=this.value;
                    smallerValue=other.value;
                    isNegative=true;
                    
                }
                else if(compareStringIntegers(this.intPart(),other.intPart())==0){
                    if(compareStringIntegers(this.float_part(), other.float_part())>0){
                        largerValue=this.value;
                        smallerValue=other.value;
                        isNegative=true;
                    }
                    else{
                        largerValue=other.value;
                        smallerValue=this.value;
                    }
                }
                else{
                    largerValue=other.value;
                    smallerValue=this.value;
                }
                result=subtractStringFloat(largerValue, smallerValue);
                if(isNegative){
                    result = '-'+result;
                }
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                result = addStringFloat(this.value, other.value);
                // System.out.println("FLoat diff");
                result='-'+result;

            }
        }
        result = removeZeros(result);
        if (result.equals("")||result.equals("0.")||result.equals("0")||result.equals("-0")||result.equals("-0.")||result.equals("-0.0")) {
            result = "0.0";
        }
    
        return new AFloat(result);
    }


    // Takes another AFloat object apply multiplyStringFloat on their values  for multiplication  
    public AFloat multiply(AFloat other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
        }
        if(!AFloat.validString(this.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(!AFloat.validString(other.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            result = multiplyStringFloat(this.value, other.value);

            result = '-'+result;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            result = multiplyStringFloat(this.value, other.value);
            
        }
        result = removeZeros(result);
        if (result.equals("")||result.equals("0.")||result.equals("0")||result.equals("-0")||result.equals("-0.")||result.equals("-0.0")) {
            result = "0.0";
        }

  
        return new AFloat(result);

    }


    // Takes another AFloat object apply divideStringFloat on their values  for division
    // Division by zero is handled by returning an error message
    public AFloat divide(AFloat other){
        String result="";
        if(other.value.equals("")){
            result="Division by zero error";
            return new AFloat(result);
        }
        
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
            // System.out.println("hleoo");
        }
        if(!AFloat.validString(this.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if(!AFloat.validString(other.value)){
            System.out.println("Please enter a vlaid float value.");
            return new AFloat("Exiting....");

        }
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            this.value = AFloat.removeZeros(this.value);
            other.value = AFloat.removeZeros(other.value);
            // System.out.println(this.value+""+other.value);
            result =divideStringFloat(this.value, other.value);

            result= '-'+result;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            this.value = AFloat.removeZeros(this.value);
            other.value = AFloat.removeZeros(other.value);
            // System.out.println("given values are "+this.value+""+other.value);

            result = divideStringFloat(this.value, other.value);
            
            
        }
        
        result = removeZeros(result);
        if (result.equals("")||result.equals("0.")||result.equals("0")||result.equals("-0")||result.equals("-0.")||result.equals("-0.0")) {
            result = "0.0";
        }
        return new AFloat(result);
    }

    
}
