package Utils;
import java.util.List;
//import com.google.maps.model.GeocodingResult;

//import tstJson.Main.Book;

public class Geocode {
	private List<String> results ; 
	private List<String> geometry;
	String formatted_address;
	//--String GEOMETRIC_CENTER;
	String lat;
	String lng;
	//String location_type;
	//String long_name;
	//String partial_match;
	//String place_id;
	
	//String short_name;
	//String types;
	
	
	public void geocode() {}

	public void Geocode(List<String> results, List<String> geometry, String formatted_address, String lat, String lng) {
	
		this.results = results;
		this.geometry = geometry;
		this.formatted_address = formatted_address;
		this.lat = lat;
		this.lng = lng;
	}

	public List<String> getResults() {
		return results;
	}

	public List<String> getGeometry() {
		return geometry;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	@Override
	public String toString() {
		return "Geocode [results=" + results + ", geometry=" + geometry + ", formatted_address=" + formatted_address
				+ ", lat=" + lat + ", lng=" + lng + "]";
	} ; 
	
	
	
	
} 
	
   

