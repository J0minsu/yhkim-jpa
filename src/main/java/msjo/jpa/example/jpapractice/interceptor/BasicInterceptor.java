//package msjo.jpa.example.jpapractice.interceptor;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Map;
//
//import org.apache.commons.codec.binary.Base64;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.InvalidClaimException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.google.gson.Gson;
//
//import kr.lifesemantics.common.vo.ReturnVO;
//
//
//public class JWTUtil {
////    //private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
////    /**
////     * 인증 확인
////     * @param key
////     * @param token
////     * @return
////     * @throws Exception
////     */
////    public static String verify(String key, String token) {
////        ReturnVO result = new ReturnVO();
////        try {
////            if(token == null) {
////                result.setRetCode("401-0");
////                result.setRetMsg("Error ::: Token Is Null Value");
////                return new Gson().toJson(result);
////            }
////            Algorithm algorithm = Algorithm.HMAC256(key);
////            JWTVerifier verifier = JWT.require(algorithm)
////                    .withIssuer("auth0")
////                    .build(); //Reusable verifier instance
////            DecodedJWT jwt = verifier.verify(token);
////
////            result.setRetCode("000");
////            result.setRetMsg(new String(Base64.decodeBase64(jwt.getPayload().getBytes()), "UTF-8"));
////            return new Gson().toJson(result);
////        } catch (UnsupportedEncodingException e) {
////            // TODO Auto-generated catch block
////            //logger.info("error Msg is :::" + "Error ::: UnsupportedEncodingException");
////            result.setRetCode("401-1");
////            result.setRetMsg("Error ::: UnsupportedEncodingException");
////            return new Gson().toJson(result);
////        }catch (AlgorithmMismatchException e) {
////            // TODO Auto-generated catch block
////            //logger.info("error Msg is :::" + "Error ::: UnsupportedEncodingException");
////            result.setRetCode("401-2");
////            result.setRetMsg("Error ::: AlgorithmMismatchException");
////            return new Gson().toJson(result);
////        }catch (TokenExpiredException e) {
////            // TODO Auto-generated catch block
////            //logger.info("error Msg is :::" + "Error ::: UnsupportedEncodingException");
////            result.setRetCode("401-3");
////            result.setRetMsg("Error ::: TokenExpiredException");
////            return new Gson().toJson(result);
////        }catch (InvalidClaimException e) {
////            // TODO Auto-generated catch block
////            //logger.info("error Msg is :::" + "Error ::: UnsupportedEncodingException");
////            result.setRetCode("401-5");
////            result.setRetMsg("Error ::: InvalidClaimException");
////            return new Gson().toJson(result);
////        }catch (JWTVerificationException e) {
////            // TODO Auto-generated catch block
////            //logger.info("error Msg is :::" + "Error ::: UnsupportedEncodingException");
////            result.setRetCode("401-6");
////            result.setRetMsg("Error ::: JWTVerificationException");
////            return new Gson().toJson(result);
////        }
////
//////		AlgorithmMismatchException - if the algorithm stated in the token's header it's not equal to the one defined in the JWTVerifier.
//////		SignatureVerificationException - if the signature is invalid.
//////		TokenExpiredException - if the token has expired.
//////		InvalidClaimException - if a claim contained a different value than the expected one.
//////		JWTVerificationException
////    }
////
////    /**
////     * 인증토큰 생성
////     * @param key
////     * @param token
////     * @return
////     * @throws Exception
////     */
////    public static String createToken(String key, Map<String, Object> param) {
////
////        try {
////
////            Calendar cal = Calendar.getInstance();
////            cal.add(Calendar.DATE, 1);
////            Date expDate = new Date(cal.getTimeInMillis());
////
////            Algorithm algorithmHS;
////            algorithmHS = Algorithm.HMAC256(key);
////            String token = JWT.create()
////                    .withIssuer("auth0")
////                    .withExpiresAt(expDate)
////                    .withIssuedAt(new Date())
////                    .withClaim("userID", String.valueOf(param.get("userID")))
////                    .sign(algorithmHS);
////
////            return token;
////        } catch (IllegalArgumentException e) {
////
////            //logger.info("error Msg is :::" + "Error ::: IllegalArgumentException");
////            return "";
////        }
////    }
//}