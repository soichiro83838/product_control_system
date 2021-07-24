package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Part;

public class PartValidator {
    public static List<String> validate(Part r) {
        List<String> errors = new ArrayList<String>();

        String partname_error = _validatePartname(r.getPartname());
        if(!partname_error.equals("")) {
            errors.add(partname_error);
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

    private static String _validatePartname(String partname) {
        if(partname == null || partname.equals("")) {
            return "部品名を入力してください。";
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
