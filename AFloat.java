public class AFloat {
    String value;

    AFloat(){
        this.value="0.0";
    }

  
    AFloat(String value){
        this.value=value;
    }

   
    public AFloat parse(String s){
        return new AFloat(s);
        
    }

   
   public AFloat copy(AFloat other){
    return new AFloat(other.value);
   }
   
   
   public String int_part(){
        int first_decimal = this.value.indexOf('.');
        return this.value.substring(0,first_decimal);
    }

   
    public String float_part(){
        int first_decimal = this.value.indexOf('.');
        return this.value.substring(first_decimal);

    }
 
 
    static String remove_zeros(String s1){
        String result="";
        int check=0;
        for(int i =0;i<s1.length();i++){
        if(check!=0){
            result+=s1.charAt(i);
        }
        if(check==0&&(s1.charAt(i)!='0')){
            check=1;
            result+=s1.charAt(i);
        }
        }
       



        return result;
    }

    
    public int string_compare_int(String s1,String s2){
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

  
    public int string_compare_float(String s1, String s2) {
        AFloat float1 = new AFloat(s1);
        AFloat float2 = new AFloat(s2);

        int intComparison = string_compare_int(float1.int_part(), float2.int_part());
        if (intComparison != 0) {
            return intComparison;
        } else {
            return string_compare_int(float1.float_part(), float2.float_part());
        }
    }

   
    public static String shift_decimal_point_right(String floatStr, int shiftRightBy) {
        if (floatStr == null || floatStr.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        int decimalIndex = floatStr.indexOf('.');
        if (decimalIndex == -1) {
            return floatStr; // No decimal point found, return original string
        }

        StringBuilder sb = new StringBuilder(floatStr.replace(".", "")); // Remove existing decimal point
        int newDecimalPosition = decimalIndex + shiftRightBy;

        if (newDecimalPosition < 0) {
            // Add leading zeros
            int zerosToAdd = -newDecimalPosition;
            for (int i = 0; i < zerosToAdd; i++) {
                sb.insert(0, '0');
            }
            sb.insert(0, '.');
        } else if (newDecimalPosition > sb.length()) {
            // Add trailing zeros and then the decimal point
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

    
    public String string_add_int(String s1,String s2){
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
        int max_length= s1.length();
        int carry_on =0;
        for(int i=max_length-1;i>=0;i--){
            
            int curr_sum=0;
            curr_sum += s1.charAt(i)-'0' + s2.charAt(i)-'0' + carry_on;
            result = Integer.toString(curr_sum%10) +result;
            carry_on = curr_sum/10;
        }
        if(carry_on>0){
            result = Integer.toString(carry_on)+result;
        }
    return result;
    }


    public String string_subtract_int(String s1,String s2){
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
        int max_length= s1.length();
        boolean carry_on_is_neg = false;
        for(int i=max_length-1;i>=0;i--){
            int curr_diff=0;
            curr_diff += (s1.charAt(i)-'0') - (s2.charAt(i)-'0');
            if(carry_on_is_neg){
                curr_diff-=1;
                carry_on_is_neg=false;
            }

            if(curr_diff<0){
                carry_on_is_neg=true;
                curr_diff+=10;
            }
            
            result= Integer.toString(curr_diff) + result;
            

        }
        return result;

    }


    public String string_multiply_int(String s1,String s2){
        String result ="";
        for(int i=s2.length()-1;i>=0;i--){
            String curr_product ="";
            int carry_on=0;
            for(int j=s1.length()-1;j>=0;j--){
                int temp = carry_on + ((s1.charAt(j)-'0')*(s2.charAt(i)-'0'));
                curr_product= Integer.toString(temp%10) + curr_product;
                carry_on = temp/10;
                // System.out.println("carry on: "+carry_on);
            }
            curr_product = Integer.toString(carry_on)+curr_product;
            for(int j=0;j<s2.length()-i-1;j++){
                curr_product +='0';
            }
            result= string_add_int(result, curr_product);

            // System.out.println("curr_product : " + curr_product);
        }
        // System.out.println("result : " + result);



        return result;


    }


    public String string_div_int(String s1,String s2){
        s1 = AInteger.removeLeadingZeros(s1);
        s2 = AInteger.removeLeadingZeros(s2);
        // System.out.println(s1+s2);
    
        if (s2.equals("")) throw new ArithmeticException("Division by zero");
    
        String result = "";
        String current = "";
        // System.out.println(s1+" "+s2);
        for (int i = 0; i < s1.length(); i++) {
            current += s1.charAt(i);
            current = AFloat.remove_zeros(current);
            // System.out.println("current: "+current);
    
            if (string_compare_int(current, s2) < 0) {
                if (result.length() > 0) result+="0";
            } else {
                for (int j = 9; j >= 0; j--) {
                    
                    String product = string_multiply_int(s2, String.valueOf(j));
                    // System.out.println("Product: "+product +" comp: "+string_compare_int(product, current)+"j "+j);
                    product = AFloat.remove_zeros(product);
                    if (string_compare_int(product, current) <= 0) {
                        // System.out.println(j);
                        result+=j;
                        current = string_subtract_int(current, product);
                        current = AFloat.remove_zeros(current);
                        break;
                    }
                }
            }
        }
        result +='.';
        if(!current.equals(".")){
            // System.out.println("result: "+result);
        
            int precision = 1000;
            while(result.length()<precision){
                current+='0';
                if (string_compare_int(current, s2) < 0) {
                    if (result.length() > 0) result+="0";
                } else {
                    for (int j = 9; j >= 0; j--) {
                    String product = string_multiply_int(s2, String.valueOf(j));
                        if (string_compare_int(product, current) <= 0) {
                            result+=j;
                            current = string_subtract_int(current, product);
                            current = AInteger.removeLeadingZeros(current);
                            break;
                        }
                    }
                }

            }
        }

        if(result.charAt(0)=='.'){
            result = '0'+result;
        }
        
    
    
        return result.isEmpty() ? "0" : AInteger.removeLeadingZeros(result);

    }
   

    public String string_add_float(String s1,String s2){
        int first_decimal = s1.indexOf('.');
        int second_decimal = s2.indexOf('.');
        int decimal_num_s1 = s1.length() - first_decimal;
        int decimal_num_s2 = s2.length() - second_decimal;
        if(decimal_num_s1>=decimal_num_s2){
            for(int i=0;i<decimal_num_s1-decimal_num_s2;i++){
                s2+='0';
            }
        }
        else{
            for(int i=0;i<decimal_num_s2-decimal_num_s1;i++){
                s1+='0';
            }
        }
        if(first_decimal >= second_decimal){
            for (int i = 0; i < first_decimal-second_decimal; i++) {
                s1 = '0'+s1;
            }
        }
        else{
            for (int i=0;i<second_decimal-first_decimal;i++){
                s2='0'+s2;
            }
        }
        
        String result="";
        
        // System.out.println("num1 : " + s1);
        // System.out.println("num2 : " + s2);
        int max_length= s1.length();
        int carry_on =0;
        for(int i=max_length-1;i>=0;i--){
            if(s1.charAt(i)!='.'){
            int curr_sum=0;
            curr_sum += s1.charAt(i)-'0' + s2.charAt(i)-'0' + carry_on;
            result = Integer.toString(curr_sum%10) +result;
            carry_on = curr_sum/10;
        }
        else{
            result = '.' +result;
        }
        }
        if(carry_on>0){
            result = Integer.toString(carry_on)+result;
        }
        


        
        
        return result;   
        
    }

   
    public String string_subtract_float(String s1,String s2){
        int first_decimal = s1.indexOf('.');
        int second_decimal = s2.indexOf('.');
        int decimal_num_s1 = s1.length() - first_decimal;
        int decimal_num_s2 = s2.length() - second_decimal;
        if(decimal_num_s1>=decimal_num_s2){
            for(int i=0;i<decimal_num_s1-decimal_num_s2;i++){
                s2+='0';
            }
        }
        else{
            for(int i=0;i<decimal_num_s2-decimal_num_s1;i++){
                s1+='0';
            }
        }
        if(first_decimal >= second_decimal){
            for (int i = 0; i < first_decimal-second_decimal; i++) {
                s1 = '0'+s1;
            }
        }
        else{
            for (int i=0;i<second_decimal-first_decimal;i++){
                s2='0'+s2;
            }
        }
        String result="";
        int max_length= s1.length();
        boolean carry_on_is_neg = false;
        for(int i=max_length-1;i>=0;i--){
            if(s1.charAt(i)!='.'){
            int curr_diff=0;
            curr_diff += (s1.charAt(i)-'0') - (s2.charAt(i)-'0');
            if(carry_on_is_neg){
                curr_diff-=1;
                carry_on_is_neg=false;
            }

            if(curr_diff<0){
                carry_on_is_neg=true;
                curr_diff+=10;
            }
            
            result= Integer.toString(curr_diff) + result;
            }
            else{
                result ='.' +result;
            }

        }
        return result;


    }
   

    public String string_multiply_float(String s1,String s2){
        String result="";
        int first_decimal = s1.indexOf('.');
        int second_decimal = s2.indexOf('.');
        int decimal_num_s1 = s1.length() - first_decimal;
        int decimal_num_s2 = s2.length() - second_decimal;
        String num1 = s1.substring(0,first_decimal)+ s1.substring(first_decimal+1);
        String num2 = s2.substring(0,second_decimal)+s2.substring(second_decimal+1);
        // System.out.println("num1: "+num1+"num2: "+num2);

        
        for(int i=num2.length()-1;i>=0;i--){
            String curr_product ="";
            int carry_on=0;
            for(int j=num1.length()-1;j>=0;j--){
                int temp = carry_on + ((num1.charAt(j)-'0')*(num2.charAt(i)-'0'));
                curr_product= Integer.toString(temp%10) + curr_product;
                carry_on = temp/10;
                // System.out.println("carry on: "+carry_on);
            }
            curr_product = Integer.toString(carry_on)+curr_product;
            for(int j=0;j<s2.length()-i-1;j++){
                curr_product +='0';
            }
            result= string_add_int(result, curr_product);
            
            // System.out.println("curr_product : " + curr_product);
        }
        // System.out.println("result : " + result);
        int new_len = result.length();
        StringBuilder sb = new StringBuilder(result);
        sb.insert(new_len - (decimal_num_s1+decimal_num_s2)+1, '.');

        result = sb.toString();


        return result;




        
    }
   

    public String string_divide_float(String s1,String s2){
        String result;
        int first_decimal = s1.indexOf('.');
        int second_decimal = s2.indexOf('.');
        int decimal_num_s1 = s1.length() - first_decimal;
        int decimal_num_s2 = s2.length() - second_decimal;
        String num1 = s1.substring(0,first_decimal)+ s1.substring(first_decimal+1);
        String num2 = s2.substring(0,second_decimal)+s2.substring(second_decimal+1);
        // System.out.println("num1: "+num1+"num2: "+num2);
        result = string_div_int(num1, num2);

        // int new_len = result.length();
        // int new_decimal_place = result.indexOf('.');
        // StringBuilder sb = new StringBuilder(result);
        // sb.insert(new_len - (decimal_num_s1-decimal_num_s2)+1, '.');

        // result = sb.toString();
        result = shift_decimal_point_right(result,decimal_num_s2-decimal_num_s1);

        

        return result;


    }
 

    public AFloat add(AFloat other){
        String Float_sum="";
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_sum = string_add_float(this.value, other.value);
                
                
                

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(string_compare_int(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    
                }
                else if(string_compare_int(this.int_part(),other.int_part())==0){
                    if(string_compare_int(this.float_part(), other.float_part())>=0){
                        s1=this.value;
                        s2=other.value;
                    }
                    else{
                        s1=other.value;
                        s2=this.value;
                        is_neg=true;
                    }
                }
                else{
                    s1=other.value;
                    s2=this.value;
                    is_neg=true;
                }
                Float_sum=string_subtract_float(s1, s2);
                if(is_neg){
                    Float_sum = '-'+Float_sum;
                }
                
            }
            
            
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                Float_sum = string_add_float(this.value, other.value);
                Float_sum = '-'+Float_sum;
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value=other.value.substring(1);
                if(string_compare_int(this.value, other.value)>0){
                    Float_sum = string_subtract_float(this.value, other.value);
                    Float_sum = '-'+Float_sum;
                }
                else{
                    Float_sum = string_subtract_float(other.value, this.value);
                }

            }
        }
        return new AFloat(Float_sum);

    }

   
    public AFloat subtract(AFloat other){
        String Float_diff="";
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(string_compare_int(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    
                }
                else if(string_compare_int(this.int_part(),other.int_part())==0){
                    if(string_compare_int(this.float_part(), other.float_part())>=0){
                        s1=this.value;
                        s2=other.value;
                    }
                    else{
                        s1=other.value;
                        s2=this.value;
                        is_neg=true;
                    }
                }
                else{
                    s1=other.value;
                    s2=this.value;
                    is_neg=true;
                }
                Float_diff=string_subtract_float(s1, s2);
                System.out.println("diff: "+Float_diff);
                if(is_neg){
                    Float_diff = '-'+Float_diff;
                }

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_diff = string_add_float(this.value, other.value);
                
            
            }
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(string_compare_int(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    is_neg=true;
                    
                }
                else if(string_compare_int(this.int_part(),other.int_part())==0){
                    if(string_compare_int(this.float_part(), other.float_part())>0){
                        s1=this.value;
                        s2=other.value;
                        is_neg=true;
                    }
                    else{
                        s1=other.value;
                        s2=this.value;
                    }
                }
                else{
                    s1=other.value;
                    s2=this.value;
                }
                Float_diff=string_subtract_float(s1, s2);
                if(is_neg){
                    Float_diff = '-'+Float_diff;
                }
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_diff = string_add_float(this.value, other.value);
                // System.out.println("FLoat diff");
                Float_diff='-'+Float_diff;

            }
        }
    
    return new AFloat(Float_diff);
    }


    public AFloat multiply(AFloat other){
        String Float_mul="";
        
        
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            Float_mul = string_multiply_float(this.value, other.value);

            Float_mul = '-'+Float_mul;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            Float_mul = string_multiply_float(this.value, other.value);
            
        }
        



        return new AFloat(Float_mul);

    }

    
    public AFloat divide(AFloat other){
        String Float_div="";
        
        
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            Float_div =string_divide_float(this.value, other.value);

            Float_div= '-'+Float_div;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            this.value = AInteger.removeLeadingZeros(this.value);
            other.value = AInteger.removeLeadingZeros(other.value);
            // System.out.println("given values are "+this.value+other.value);

            Float_div = string_divide_float(this.value, other.value);
            
            
        }
        
        
        return new AFloat(Float_div);
    }

    
    public static void main(String[] args) {
        AFloat float1 = new AFloat("+0.89");
        AFloat float2 = new AFloat("-0.25");
        AFloat x = float1.divide(float2);
        System.out.println(x.value);

    }


}
