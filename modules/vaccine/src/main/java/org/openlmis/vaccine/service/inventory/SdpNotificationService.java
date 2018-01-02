package org.openlmis.vaccine.service.inventory;

import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.ConfigurationSetting;
import org.openlmis.core.service.ConfigurationSettingService;
import org.openlmis.vaccine.domain.inventory.VaccineDistribution;
import org.openlmis.vaccine.repository.mapper.inventory.VaccineInventoryDistributionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Future;


/**
 * Created by hassan on 5/25/17.
 */

/*
@Component
*/
@Service
@Async
public class SdpNotificationService {

    public  static final String TIIS_URL= "VIMS_TIMR_INTERGRATION";
    public  static final String TIIS_USERNAME= "VIMS_TIMR_USERNAME";
    public  static final String TIIS_PASSWORD= "VIMS_TIMR_PASSWORD";

    @Autowired
    private VaccineInventoryDistributionMapper mapper;

    @Autowired
    private VaccineInventoryDistributionService distributionService;

    @Autowired
    ConfigurationSettingService configurationSettingService;

//    @Transactional



    public void updateNotification(Long distributionId) {

       if (distributionId != null) {
            VaccineDistribution d = mapper.getDistributionById(distributionId);
            String url = configurationSettingService.getByKey(TIIS_URL).getValue();
            String username = configurationSettingService.getByKey(TIIS_USERNAME).getValue();
            String password = configurationSettingService.getByKey(TIIS_PASSWORD).getValue();
            if(url != null && username != null && password !=null)
                sendHttps(d,url,username,password);
            //sendNotification(d);
        }
    }


/*    public void updateNotification(Long distributionId) {
        if (distributionId != null) {
            VaccineDistribution d = mapper.getDistributionById(distributionId);
            String url = configurationSettingService.getByKey(TIIS_URL).getValue();
            String username = configurationSettingService.getByKey(TIIS_USERNAME).getValue();
            String password = configurationSettingService.getByKey(TIIS_PASSWORD).getValue();
            if(url != null && username != null && password !=null)
            sendHttps(d,url,username,password);
            //sendNotification(d);
        }

    }*/

    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void sendHttps(VaccineDistribution d,String url,String username,String password) {

        VaccineDistribution distribution = distributionService.getDistributionByToFacility(d.getToFacilityId());
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(username);
        System.out.println(password);
        System.out.println(url);
        try {
            String jsonInString = mapper.writeValueAsString(distribution);

            URL obj = new URL(url);

            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
           // disableSslVerification();
            String userCredentials = username+":"+password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
            con.setRequestProperty("Authorization", basicAuth);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/plain");
            System.out.println("all connection" + con);

            con.setDoOutput(true);

            System.out.println(jsonInString);

            OutputStream wr = con.getOutputStream();
            wr.write(jsonInString.getBytes("UTF-8"));

            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("e" + e.getMessage());
            e.printStackTrace();
        }


    }


}