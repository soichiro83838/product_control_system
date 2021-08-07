package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User e, Boolean emailDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String email_error = validateEmail(e.getEmail(), emailDuplicateCheckFlag);
        if(!email_error.equals("")) {
            errors.add(email_error);
        }

        String name_error = validateName(e.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(e.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String validateEmail(String email, Boolean emailDuplicateCheckFlag) {
        if(email == null || email.equals("")) {
            return "メールアドレスを入力してください。";
        }

        if(emailDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredEmail", Long.class).setParameter("email", email).getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたメールアドレスの情報はすでに存在しています。";
            }
        }

        return "";
    }

    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}