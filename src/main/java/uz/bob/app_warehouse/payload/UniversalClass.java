package uz.bob.app_warehouse.payload;

public class UniversalClass {

    public static String forRandomCode(){
        double random = Math.random()*10000;
        String value = String.valueOf(random);
        String[] split = value.split("\\.");
        return split[0];
    }

}
