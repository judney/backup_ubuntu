package Utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsultaGeocode {
	  static int codigoSucesso = 200;
	
	public static  String[]  consultaUrl(String url ) throws Exception {
        String urlParaChamada =url;
        String lats , latn , lngs , lngn =null ; 
        String geo[] = new String[4];
        for ( int i = 0 ; i < 4 ; i++) { 
        	geo[i]= "N/A";  
        { 			
      
        String jsonEmString = "";
        try {
            URL url1 = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url1.openConnection();
            if (conexao.getResponseCode() != codigoSucesso)
            { 
                throw new RuntimeException("HTTP error code number  : " + conexao.getResponseCode());
            } 
            else 
            {
                    BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
                   
                    
                    jsonEmString = Util.converteJsonEmString(resposta);
                    JSONObject my_obj = new JSONObject(jsonEmString);
                    //Geocode geo = new Geocode() ;  
                    
                                        
                    
                    //System.out.println("\n\n"); 
                   
                    
                    JSONArray arr = my_obj.getJSONArray("results");
                    
                    int pos =0 ; 
                   
                    //Latitude norte 
                     pos=arr.toString().lastIndexOf("northeast");
                     pos=jsonEmString.lastIndexOf("northeast");
                     pos += 40 ; 
                     //System.out.println(pos );
                     
                     latn=jsonEmString.substring(pos,(pos + 17) ); 
                     
                     pos += 44 ; 
                     lngn=jsonEmString.substring(pos,(pos + 17 )  );
                     
                     //System.out.println("Norteast");
                     //System.out.println("Latitude..: " + latn );
                     //System.out.println("Longitude.: " + lngn );
                     
                  // Latitude Sul 
                     
                     
                     pos=jsonEmString.lastIndexOf("southwest");
                     pos += 40 ; 
                     //System.out.println(pos );
                     
                     lats=jsonEmString.substring(pos,(pos + 17) ); 
                     
                     pos += 44 ; 
                     lngs=jsonEmString.substring(pos,(pos + 17 )  );
                     
                     //System.out.println("Southwest");
                     //System.out.println("Latitude..: " + lats );
                     //System.out.println("Longitude.: " + lngs );
                       
            }        
            
        } catch (Exception e) {
        	
            throw new Exception("ERRO: " + e);
        }
        
        if ( latn != null )
        	geo[0]  = latn;
        
        if ( lngn != null )	
        	geo[1]  = lngn;
        
        if ( lats != null )
        	geo[2]  = lats;
        
        if ( lngs != null )
        	geo[3]  = lngs; 
        
        
        } 
       
    }
    return geo ;
        
  }
}
