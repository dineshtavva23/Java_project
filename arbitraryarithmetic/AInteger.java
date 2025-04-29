package arbitraryarithmetic;

public class AInteger{
    public String value;

    public AInteger() {
        this.value="0";
        }


    public AInteger(String value){
        this.value = value;

    }


    public static AInteger parse(String s){
        return new AInteger(s);

    }
    
    
    public AInteger copy(AInteger other){
        return new AInteger(other.value);

    }


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
            s1 = '0'+ s1; 
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
    

    public AInteger add(AInteger other){
        String result="";

        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
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
 
    
    public AInteger subtract(AInteger other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
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


    public AInteger multiply(AInteger other){
        String result="";
        if (this.value.charAt(0) != '+' && this.value.charAt(0) != '-') {
            this.value = '+' + this.value;
        }
        if (other.value.charAt(0) != '+' && other.value.charAt(0) != '-') {
            other.value = '+' + other.value;
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


    public static void main(String[] args) {
        AInteger int1 = new AInteger("8792726365283060579833950521677211");
        AInteger int2 = new AInteger("");
        // AInteger diff = int1.subtract(int2);
        AInteger x = int1.divide(int2);

        System.out.println(x.value); 
        // System.out.println(int1.string_subtract("980","0804"));
        // System.out.println(int1.remove_zeros());
    }




}
