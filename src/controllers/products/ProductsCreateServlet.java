package controllers.products;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import models.Product;
import models.User;
import models.validators.ProductValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class ProductsCreateServlet
 */
@MultipartConfig // 画像アップ機能時は必ず必要
@WebServlet("/products/create")
public class ProductsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // 画像アップロード
            Part part = request.getPart("image");
            if (part.getSize() != 0) {
                String filename = getFileName(part);
                String filePath = getServletContext().getRealPath("/uploads/") + filename;
                System.out.println("filePath!!!" + filePath);
                File uploadDir = new File(getServletContext().getRealPath("/uploads/"));
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                part.write(filePath);
                try {
                        /* S3 */
                        String region = (String) this.getServletContext().getAttribute("region");
                        String awsAccessKey = (String) this.getServletContext().getAttribute("awsAccessKey");
                        String awsSecretKey = (String) this.getServletContext().getAttribute("awsSecretKey");
                        String bucketName = (String) this.getServletContext().getAttribute("bucketName");
                        // 認証情報を用意
                        AWSCredentials credentials = new BasicAWSCredentials(
                                // アクセスキー
                                awsAccessKey,
                                // シークレットキー
                                awsSecretKey);
                        // クライアントを生成
                        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                                // 認証情報を設定
                                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                // リージョンを ? に設定
                                .withRegion(region).build();
                        // === ファイルから直接アップロードする場合 ===
                        // アップロードするファイル
                        File file = new File(filePath);
                        // ファイルをアップロード
                        s3.putObject(
                                // アップロード先バケット名
                                bucketName,
                                // アップロード後のキー名
                                "uploads/" + filename,
                                // ファイルの実体
                                file);
                    } catch (Exception e) {
                        System.out.println("S3失敗");
                    }
//                 // 認証情報を用意
//                    AWSCredentials credentials = new BasicAWSCredentials(
//                        // アクセスキー
//                        "ag956TJam@",
//                        // シークレットキー
//                        "tmAW149@2"
//                    );
//
//                    // クライアントを生成
//                    AmazonS3 client = AmazonS3ClientBuilder
//                        .standard()
//                        // 認証情報を設定
//                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                        // リージョンを AP_NORTHEAST_1(東京) に設定
//                        .withRegion(Regions.AP_NORTHEAST_1)
//                        .build();
//                 // === ファイルから直接アップロードする場合 ===
//                 // アップロードするファイル
//                 File file = new File("filePath");
//                 // ファイルをアップロード
//                 client.putObject(
//                         // アップロード先バケット名
//                         "test2195",
//                         // アップロード後のキー名
//                         "uploads/" + filename,
//                         // ファイルの実体
//                         file
//                 );
//
               Product r = new Product();

                r.setUser((User)request.getSession().getAttribute("login_user"));

                Date date = new Date(System.currentTimeMillis());
                String rd_str = request.getParameter("date");
                if(rd_str != null && !rd_str.equals("")) {
                    date = Date.valueOf(request.getParameter("date"));
                }

                r.setDate(date);
                r.setProductname(request.getParameter("productname"));
                r.setModelnumber(request.getParameter("modelnumber"));
                r.setManufacture(request.getParameter("manufacture"));
                // 変数pに画像名をセットする
                r.setImage(filename);

                List<String> errors = ProductValidator.validate(r);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("product", r);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products/new.jsp");
                    rd.forward(request, response);
                } else {
                    em.getTransaction().begin();
                    em.persist(r);
                    em.getTransaction().commit();
                    em.close();
                    request.getSession().setAttribute("flush", "登録が完了しました。");

                    response.sendRedirect(request.getContextPath() + "/products/index");
                }
            } else { // 画像が選択されなかった場合
                // Productのインスタンスを生成
                Product r = new Product();

                r.setUser((User)request.getSession().getAttribute("login_user"));

                Date date = new Date(System.currentTimeMillis());
                String rd_str = request.getParameter("date");
                if(rd_str != null && !rd_str.equals("")) {
                    date = Date.valueOf(request.getParameter("date"));
                }

                r.setDate(date);
                r.setProductname(request.getParameter("productname"));
                r.setModelnumber(request.getParameter("modelnumber"));
                r.setManufacture(request.getParameter("manufacture"));
                // バリデーター の呼び出し
                List<String> errors = ProductValidator.validate(r);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("product", r);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products/new.jsp");
                    rd.forward(request, response);
                }
            }
        }
        }
     // 拡張子を変えずに、ランダムな名前のファイルを生成する
        private String getFileName(Part part) {
            String[] headerArrays = part.getHeader("Content-Disposition").split(";");
            String fileName = null;
            for (String head : headerArrays) {
                if (head.trim().startsWith("filename")) {
                    fileName = head.substring(head.indexOf('"')).replaceAll("\"", "");
                }
            }
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            String randName = EncryptUtil.getWordEncrypt(currentTime.toString());
            String extension = fileName.substring(fileName.lastIndexOf("."));
            String rndFileName = randName + extension;
            return rndFileName;
    }
}