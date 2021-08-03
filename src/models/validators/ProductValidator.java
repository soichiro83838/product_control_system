package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductValidator {
    public static List<String> validate(Product r) {
        List<String> errors = new ArrayList<String>();

        String productname_error = _validateProductname(r.getProductname());
        if(!productname_error.equals("")) {
            errors.add(productname_error);
        }

        String modelnumber_error = _validateModelnumber(r.getModelnumber());
        if(!modelnumber_error.equals("")) {
            errors.add(modelnumber_error);
        }

        String manufacture_error = _validateManufacture(r.getManufacture());
        if(!manufacture_error.equals("")) {
            errors.add(manufacture_error);
        }

        String image_error = _validateImage(r.getImage());
        if(!image_error.equals("")) {
            errors.add(image_error);
        }

        return errors;
    }

    private static String _validateProductname(String productname) {
        if(productname == null || productname.equals("")) {
            return "製品名を入力してください。";
        }

        return "";
    }

    private static String _validateModelnumber(String modelnumber) {
        if(modelnumber == null || modelnumber.equals("")) {
            return "型番を入力してください。";
        }

        return "";
    }

    private static String _validateManufacture(String manufacture) {
        if(manufacture == null || manufacture.equals("")) {
            return "メーカーを入力してください。";
        }

        return "";
    }

    private static String _validateImage(String image) {
        if(image == null || image.equals("")) {
            return "ファイルを添付してください。";
        }

        return "";
    }
}
