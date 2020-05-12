package rs.htec.apps.qasandbox.map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import java.util.Arrays;
import java.util.List;

public class Mapper {

    private boolean ignore = true;

    public Mapper ignoreUnknownFields() {
        this.ignore = false;
        return this;
    }

    public <T> T mapJSON(String json, Class<T> destination) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, this.ignore);
        return mapper.readValue(json, destination);
    }

    public static <T> T mapFromJSON(String json, Class<T> destination) {
        return new GsonBuilder().setLenient().create().fromJson(json, destination);
    }


    public static <T> String mapToJSON(T object) {
        return new Gson().toJson(object);
    }

	public static <T> List<T> mapFromJsonArray(String json, Class<T[]> destination) {
		return Arrays.asList(new Gson().fromJson(json, destination));
	}




}