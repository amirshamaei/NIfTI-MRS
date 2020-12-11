import com.ericbarnhill.niftijio.NiftiHeader;
import com.ericbarnhill.niftijio.NiftiVolume;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Paths;

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

    public NiftiMRS() {

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
    public static NiftiMRS read(String path) throws FileNotFoundException {
        NiftiMRS niftiMRS = new NiftiMRS();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String[] paths = pathBuilder(path);
        try {
            niftiMRS.setJson(gson.fromJson(new FileReader(paths[0]), JsonExtention.class));
            try {
                niftiMRS.setNifti(NiftiVolume.read(paths[1]));
//            niftiMRS.setDims(niftiMRS.getNifti().header.dim);
            } catch (IOException e) {
                try {
                    NiftiVolume.read(paths[1]+".gz");
                } catch (IOException ioException) {
                    System.out.println("there is no associated nifti file");
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            try {
                niftiMRS.setNifti(NiftiVolume.read(paths[1]));
            } catch (IOException e1) {
                try {
                    niftiMRS.setNifti(NiftiVolume.read(paths[1]+".gz"));
                } catch (IOException ioException) {
                    System.out.println("there is no nifti file");
                }
            }
        }



        return niftiMRS;
    }

    private static String[] pathBuilder(String path) {
        String path2parent = Paths.get(path).getParent().toString();
        String fileName = Paths.get(path).getFileName().toString();
        String filenameWithoutExtention = FilenameUtils.removeExtension(fileName);
        String extention = FilenameUtils.getExtension(path);
        String path2json = null;
        String path2nii = null;
        if (extention.equals("json")){
            path2json = path;
            path2nii = path2parent + File.separator+ filenameWithoutExtention + ".nii";
        } else if (extention.equals("nii")){
            path2json = path2parent + File.separator + filenameWithoutExtention + ".json";
            path2nii = path2parent + File.separator+ filenameWithoutExtention + ".nii";
        } else if (extention.equals("gz")) {
            filenameWithoutExtention = FilenameUtils.removeExtension(filenameWithoutExtention);
            path2json = path2parent + File.separator + filenameWithoutExtention + ".json";
            path2nii = path2parent + File.separator+ filenameWithoutExtention + ".nii";
        }
        return new String[] {path2json,path2nii};
    }

    public void write(String path, boolean compression, boolean extention) throws IOException {
        String path2nii;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (compression) {
            path2nii = path+ ".nii.gz";
        } else {
            path2nii = path+ "nii";
        }
        if (extention) {
            float lenOfExt = (float) Math.ceil((nifti.header.vox_offset + gson.toJson(json).getBytes().length + 8) / 16d) * 16;
            nifti.header.extension[0] = 1;
            nifti.header.extensions_list.add(new int[] {gson.toJson(json).getBytes().length+8,2001});
            nifti.header.extension_blobs.add(gson.toJson(json).getBytes());
            nifti.header.vox_offset = (float) Math.ceil((nifti.header.vox_offset + gson.toJson(json).getBytes().length+8)/16d)*16;
            nifti.write(path2nii);
        } else {
            try {
                nifti.write(path2nii);

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
}
