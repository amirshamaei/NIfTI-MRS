import com.ericbarnhill.niftijio.NiftiHeader;
import com.ericbarnhill.niftijio.NiftiVolume;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class NiftiMRS {
    private NiftiVolume nifti;
    private JsonExtention json;
    private int[] dims;

    public NiftiMRS(int[] dims) {
        this.dims = dims;
        NiftiHeader niftiHeader = new NiftiHeader(dims);
        setDefaults(niftiHeader);
        nifti = new NiftiVolume(niftiHeader);
        json = new JsonExtention();

    }

    private void setDefaults(NiftiHeader niftiHeader) {
        niftiHeader.datatype = NiftiHeader.NIFTI_TYPE_COMPLEX64;
    }

    public NiftiVolume getNifti() {
        return nifti;
    }

    public void setNifti(NiftiVolume nifti) {
        this.nifti = nifti;
    }

    public JsonExtention getJson() {
        return json;
    }

    public void setJson(JsonExtention json) {
        this.json = json;
    }

    public int[] getDims() {
        return dims;
    }

    public void setDims(int[] dims) {
        this.dims = dims;
    }

    public void write(String path, boolean compression, boolean extention)  {
        try {
            nifti.write(path);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter file = new FileWriter(path + ".json")) {
                gson.toJson(json, file);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
