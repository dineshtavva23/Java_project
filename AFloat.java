public class AFloat {
    String value;

    AFloat(){
        this.value="0.0";
    }
    AFloat(String value){
        this.value=value;
    }

    public AFloat parse(String s){
        AFloat new_float =  new AFloat(s);
        return new_float;
    }

    public String int_part(){
        int first_decimal = this.value.indexOf('.');
        return this.value.substring(0,first_decimal);
    }

   
    public String float_part(){
        int first_decimal = this.value.indexOf('.');
        return this.value.substring(first_decimal);

    }
 
 
    public int string_compare(String s1,String s2){
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




    public String string_add(String s1,String s2){
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

   
    public String string_subtract(String s1,String s2){
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
   

    public String string_multiply(String s1,String s2){
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
   
 
    public AFloat add(AFloat other){
        String Float_sum="";
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_sum = string_add(this.value, other.value);
                
                
                

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(string_compare(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    
                }
                else if(string_compare(this.int_part(),other.int_part())==0){
                    if(string_compare(this.float_part(), other.float_part())>=0){
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
                Float_sum=string_subtract(s1, s2);
                if(is_neg){
                    Float_sum = '-'+Float_sum;
                }
                
            }
            
            
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                Float_sum = string_add(this.value, other.value);
                Float_sum = '-'+Float_sum;
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value=other.value.substring(1);
                if(string_compare(this.value, other.value)>0){
                    Float_sum = string_subtract(this.value, other.value);
                    Float_sum = '-'+Float_sum;
                }
                else{
                    Float_sum = string_subtract(other.value, this.value);
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
                if(string_compare(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    
                }
                else if(string_compare(this.int_part(),other.int_part())==0){
                    if(string_compare(this.float_part(), other.float_part())>=0){
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
                Float_diff=string_subtract(s1, s2);
                System.out.println("diff: "+Float_diff);
                if(is_neg){
                    Float_diff = '-'+Float_diff;
                }

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_diff = string_add(this.value, other.value);
                
            
            }
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(string_compare(this.int_part(), other.int_part())>0){
                    s1=this.value;
                    s2=other.value;
                    is_neg=true;
                    
                }
                else if(string_compare(this.int_part(),other.int_part())==0){
                    if(string_compare(this.float_part(), other.float_part())>0){
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
                Float_diff=string_subtract(s1, s2);
                if(is_neg){
                    Float_diff = '-'+Float_diff;
                }
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Float_diff = string_add(this.value, other.value);
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
            Float_mul = string_multiply(this.value, other.value);

            Float_mul = '-'+Float_mul;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            Float_mul = string_multiply(this.value, other.value);
            
        }
        



        return new AFloat(Float_mul);

    }

    


    
    public static void main(String[] args) {
        AFloat float1 = new AFloat("-9.899999399957097394794375927439572439759247598425927509");
        AFloat float2 = new AFloat("-0.8982759872349857209843750928475892743985274509823740958988");
        AFloat x = float1.multiply(float2);
        System.out.println(x.value);

    }

    
}
