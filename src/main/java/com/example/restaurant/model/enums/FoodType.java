package com.example.restaurant.model.enums;

public enum FoodType {
    DRINK(1,"İçki","Drink"),
    HOT_FOOD(2, "Isti yemekler","Hot food"),
    SNACK(3,"Qəlyanaltı","Snack");

    private int code;
    private String nameAz;
    private String nameEn;

    FoodType(int code, String nameAz, String nameEn) {
        this.code = code;
        this.nameAz = nameAz;
        this.nameEn = nameEn;
    }

    public static Integer getCode(FoodType foodType) {
        return foodType == null ? null : foodType.code;
    }
    public static FoodType lookup(Integer code) {
        if (code == null) {
            return null;
        } else {
           FoodType[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                FoodType foodType = var1[var3];
                if (foodType.code == code) {
                    return foodType;
                }
            }

            throw new IllegalArgumentException("No matching constant for " + FoodType.class.getSimpleName() + " code =" + code);
        }
    }



}
