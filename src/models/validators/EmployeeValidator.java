package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class EmployeeValidator {
    public static List<String> validate(Employee e, Boolean emailDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String email_error = validateCode(e.getEmail(), emailDuplicateCheckFlag);
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

    // email
    private static String validateCode(String email, Boolean emailDuplicateCheckFlag) {
        // 必須入力チェック
        if(email == null || email.equals("")) {
            return "emailを入力してください。";
        }

        // すでに登録されているemailとの重複チェック
        if(emailDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long employees_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class).setParameter("email", email).getSingleResult();
            em.close();
            if(employees_count > 0) {
                return "入力されたemailの情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 社員名の必須入力チェック
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}