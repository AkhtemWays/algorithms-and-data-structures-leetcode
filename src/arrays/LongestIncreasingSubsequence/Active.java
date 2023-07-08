package arrays.LongestIncreasingSubsequence;


import java.util.Arrays;

public class Active {
    public static void main(String[] args) {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        int[] nums2 = {0,1,0,3,2,3};
//        System.out.println(lengthOfLIS(nums));
//        System.out.println(lengthOfLIS(nums2));
        System.out.println("struct<rpo_bar_code:string,create_date_time:string,first_processed_date:string,own_number:string,rpo_index_from:string,index_from_rf:string,rpo_index_to:string,country_from:string,country_to:string,trans_type:int,mail_type:int,mail_ctg:int,mail_rank:int,send_ctg:int,post_mark:int,rpo_mass:int,payment:bigint,value:bigint,pay_type:int,direct_ctg:int,inter_type:int,mass_rate:bigint,insr_rate:bigint,air_rate:bigint,ad_val_tax:bigint,rate:bigint,inn:string, kpp:string,custom_duty:bigint,height:int,length:int,width:int,volume_weight:int,sndr:string,send_address:struct<type:int,index:string,border_index:string,address:struct<type:int,region:string,area:string,place:string,street:string,house:struct<value:string,letter:string,slash:string,corpus:string,building:string,room:string>>,country:struct<a2_code:string,a3_code:string,ru_code:string>>,send_phone:string,rcpn:string,recv_address:struct<type:int,index:string,border_index:string,address:struct<type:int,region:string,area:string,place:string,street:string,house:struct<value:string,letter:string,slash:string,corpus:string,building:string,room:string>>,country:struct<a2_code:string,a3_code:string,ru_code:string>>,recv_phone:string,rpo_software_version:string,rpo_source_code:string,group_id:string,group_weight:struct<type:int,properties:array<struct<name:string,value:string,object_list:array<struct<name:string,attributes:map<string,string>>>>>,value:double,measurement:string>,delivery_method:int,money:array<struct<type:int,properties:array<struct<name:string,value:string,object_list:array<struct<name:string,attributes:map<string,string>>>>>,value:int,measurement:string,pay_method:int>>,rpo_num:int,retention_end_date:string,retention_period:int,rtm002_postmark:string,rpo_properties:array<struct<name:string,value:string,object_list:array<struct<name:string,attributes:map<string,string>>>>>,last_updated_date:string>".substring(446, 466));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                    dp[i] = Math.max(dp[i], 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
