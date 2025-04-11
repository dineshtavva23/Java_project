





public class AInteger{
    String value;

    public AInteger() {
        this.value="0";
        }


    public AInteger(String value){
        this.value = value;

    }


    public static AInteger parse(String s){
        AInteger new_int =new AInteger(s);
        return new_int;

    }
    public String string_add(String s1,String s2){
 
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


    public String string_subtract(String s1,String s2){
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
            }

            if(curr_diff<0){
                carry_on_is_neg=true;
                curr_diff+=10;
            }
            
            result= Integer.toString(curr_diff) + result;
            

        }
        return result;
        


    }

    public String string_multiply(String s1, String s2){
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
            result= string_add(result, curr_product);
            
            // System.out.println("curr_product : " + curr_product);
        }
        // System.out.println("result : " + result);



        return result;

    }


    public int compare_string(String s1, String s2){
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



    public AInteger add(AInteger other){
        String Integers_sum="";
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                
                Integers_sum = string_add(this.value, other.value);
                
                
                

            }
            else if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                String s1;
                String s2;
                boolean is_neg=false;
                if(compare_string(this.value, other.value)>0){
                    s1=this.value;
                    s2=other.value;
                    
                }
                else{
                    s1=other.value;
                    s2=this.value;
                    is_neg=true;
                }
                Integers_sum=string_subtract(s1, s2);
                if(is_neg){
                    Integers_sum = '-'+Integers_sum;
                }
                
            }
            
            
        }
        else if (this.value.charAt(0)=='-') {
            if(other.value.charAt(0)=='-'){
                this.value = this.value.substring(1);
                other.value = other.value.substring(1);
                Integers_sum = string_add(this.value, other.value);
                Integers_sum = '-'+Integers_sum;
            

            }
            else if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value=other.value.substring(1);
                if(compare_string(this.value, other.value)>0){
                    Integers_sum = string_subtract(this.value, other.value);
                    Integers_sum = '-'+Integers_sum;
                }
                else{
                    Integers_sum = string_subtract(other.value, this.value);
                }

            }
        }
        return new AInteger(Integers_sum);

    }
    public AInteger subtract(AInteger other){
        String Integers_diff="";
        if(this.value.charAt(0)=='+'){
            if(other.value.charAt(0)=='+'){
                this.value= this.value.substring(1);
                other.value = other.value.substring(1);
                if(compare_string(this.value, other.value)>0){
                    Integers_diff = string_subtract(this.value, other.value);
                }
                else{
                    Integers_diff = string_subtract(other.value, this.value);
                    Integers_diff = '-' + Integers_diff;
                }
            }
            else if(other.value.charAt(0)=='-'){
                this.value=this.value.substring(1);
                other.value = other.value.substring(1);
                Integers_diff = string_add(this.value, other.value);
            }
        }
        else if(this.value.charAt(0)=='-'){
            if(other.value.charAt(0)=='+'){
                this.value=this.value.substring(1);
                other.value = other.value.substring(1);
                Integers_diff=string_add(this.value, other.value);
                Integers_diff = '-'+Integers_diff;

            }
            else if (other.value.charAt(0)=='-'){
            this.value=this.value.substring(1);
            other.value = other.value.substring(1);
            if(compare_string(this.value, other.value)>0){
                Integers_diff = string_subtract(this.value, other.value);
                Integers_diff = '-' + Integers_diff;
            }
            else{
                Integers_diff = string_subtract(other.value, this.value);
                
            }
            

        }
    }
        return new AInteger(Integers_diff);
        

    }

    public AInteger multiply(AInteger other){
        String Integers_mul="";
        
        
        if ((this.value.charAt(0)=='-' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='+' && other.value.charAt(0)=='-')) {
            this.value = this.value.substring(1);
            other.value = other.value.substring(1);
            Integers_mul = string_multiply(this.value, other.value);

            Integers_mul = '-'+Integers_mul;
        }
        else if((this.value.charAt(0)=='+' && other.value.charAt(0)=='+') ||(this.value.charAt(0)=='-' && other.value.charAt(0)=='-')){
            Integers_mul = string_multiply(this.value, other.value);
            
        }
        return new AInteger(Integers_mul);
    }
    public static void main(String[] args) {
        AInteger int1 = new AInteger("+98");
        AInteger int2 = new AInteger("-1");
        // AInteger diff = int1.subtract(int2);
        AInteger x = int1.multiply(int2);
        System.out.println(x.value); 
    }




}
