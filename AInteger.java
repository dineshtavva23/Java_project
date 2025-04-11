

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
        if(s1.length()>= s2.length()){
            for(int i =0;i<s1.length()-s2.length();i++){
                s2 = '0'+ s2; 
            }
        }
        else{
            for(int i =0;i<s2.length()-s1.length();i++){
            s1 = '0'+ s1; 
            }
        }
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
    public static void main(String[] args) {
        AInteger int1 = new AInteger("+299");
        AInteger int2 = new AInteger("+4293");
        AInteger sum = int1.add(int2);
        System.out.println(sum.value); 
     }




}