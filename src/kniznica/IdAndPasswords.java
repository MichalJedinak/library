 package kniznica;

import java.util.HashMap;

public class IdAndPasswords {

      HashMap<String,String> loginInfo = new HashMap<String,String>();

       IdAndPasswords() {
            loginInfo.put("Bubak","heslo");
            loginInfo.put("name","kukucka13");
            loginInfo.put("Name_3","00SOMAR5");
      }

      protected HashMap<String,String> getLoginInfo() {
            return loginInfo;
      }
}