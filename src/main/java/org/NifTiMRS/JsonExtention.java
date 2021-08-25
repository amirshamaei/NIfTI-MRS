package org.NifTiMRS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonExtention {
    Double[] SpectrometerFrequency;
    String[] ResonantNucleus;

    DIM_KEYS dim_5;
    String dim_5_info;
    JsonObject dim_5_header = new JsonObject();

    DIM_KEYS dim_6;
    String dim_6_info;
    JsonObject dim_6_header = new JsonObject();;

    DIM_KEYS dim_7;
    String dim_7_info;
    JsonObject dim_7_header = new JsonObject();


    public JsonExtention() {

    }

    public Double[] getSpectrometerFrequency() {
        return SpectrometerFrequency;
    }

    public void setSpectrometerFrequency(Double[] spectrometerFrequency) {
        SpectrometerFrequency = spectrometerFrequency;
    }

    public String[] getResonantNucleus() {
        return ResonantNucleus;
    }

    public void setResonantNucleus(String[] resonantNucleus) {
        ResonantNucleus = resonantNucleus;
    }

    public DIM_KEYS getDim_5() {
        return dim_5;
    }

    public void setDim_5(DIM_KEYS dim_5) {
        this.dim_5 = dim_5;
    }

    public String getDim_5_info() {
        return dim_5_info;
    }

    public void setDim_5_info(String dim_5_info) {
        this.dim_5_info = dim_5_info;
    }

    public JsonObject getDim_5_header() {
        return dim_5_header;
    }

    public void setDim_5_header(TAGS name, List values) {
        JsonArray jsonArray = new JsonArray();
        for (Object value:values
             ) {
            try {
                jsonArray.add((Number) value);
            } catch (Exception e) {
                jsonArray.add((String) value);
            } finally {

            }
        }
        dim_5_header.add(String.valueOf(name), jsonArray);
    }
    public void setDim_5_header(String name, Metadata metadata) {
        JsonObject metaobj = getMetaJson(metadata);
        dim_5_header.add(name, metaobj);

    }
    public void setDim_5_header(String name, ArrayList<Metadata> userDefinedMetadataArrayList) {
        JsonObject metaJsons = new JsonObject();
        for (Metadata meta:userDefinedMetadataArrayList
             ) {
            JsonObject metaJson = getMetaJson(meta);
            metaJsons.add(meta.getName(), metaJson);
        }
        dim_5_header.add(name, metaJsons);
    }

    private JsonObject getMetaJson(Metadata metadata) {
        JsonObject metaobj = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        for (Object value:metadata.getValue()
        ) {
            try {
                jsonArray.add((Number) value);
            } catch (Exception e) {
                jsonArray.add((String) value);
            } finally {

            }
        }
        metaobj.add("Value", jsonArray);

        metaobj.addProperty("Description", metadata.getDescription());
        metaobj.addProperty("Unit",  metadata.getUnit());
        metaobj.addProperty("Delete_for_anon",  metadata.getDelete_for_anon());
        return metaobj;
    }

    public void setDim_5_header(Metadata metadata) {
        setDim_5_header(metadata.getName(),metadata);
    }
    
    public DIM_KEYS getDim_6() {
        return dim_6;
    }

    public void setDim_6(DIM_KEYS dim_6) {
        this.dim_6 = dim_6;
    }

    public String getDim_6_info() {
        return dim_6_info;
    }

    public void setDim_6_info(String dim_6_info) {
        this.dim_6_info = dim_6_info;
    }

    public JsonObject getDim_6_header() {
        return dim_6_header;
    }

    public void setDim_6_header(JsonObject dim_6_header) {
        this.dim_6_header = dim_6_header;
    }

    public DIM_KEYS getDim_7() {
        return dim_7;
    }

    public void setDim_7(DIM_KEYS dim_7) {
        this.dim_7 = dim_7;
    }

    public String getDim_7_info() {
        return dim_7_info;
    }

    public void setDim_7_info(String dim_7_info) {
        this.dim_7_info = dim_7_info;
    }

    public JsonObject getDim_7_header() {
        return dim_7_header;
    }

    public void setDim_7_header(JsonObject dim_7_header) {
        this.dim_7_header = dim_7_header;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
