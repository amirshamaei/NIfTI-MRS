import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.NifTiMRS.DIM_KEYS;
import org.NifTiMRS.JsonExtention;
import org.NifTiMRS.Metadata;
import org.NifTiMRS.TAGS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class testNiftiMrsDocExmples {
    public static void main(String[] args) throws FileNotFoundException {
        JsonExtention niftiMRS = new JsonExtention();
        niftiMRS.setDim_5(DIM_KEYS.DIM_INDIRECT_0);
        niftiMRS.setDim_5_info("Echo time increment");
        niftiMRS.setDim_5_header(TAGS.EchoTime, Arrays.asList(new Double[]{0.03, 0.04}));
        niftiMRS.setDim_5_header(TAGS.RepetitionTime, Arrays.asList(new Double[]{1d, 1.10}));

        Metadata userDefinedMetadata_1 = new Metadata(Arrays.asList(3), "duration of the excitation pulse", "ms", false);

        niftiMRS.setDim_5_header("Excitation pulse duration", userDefinedMetadata_1);

        Metadata userDefinedMetadata_nes1 = new Metadata("Duration",Arrays.asList(3), "Duration of the excitation pulse", "ms", false);
        Metadata userDefinedMetadata_nes2 = new Metadata("Pulse name",Arrays.asList("SINC"), "Excitation pulse description", null, false);

        ArrayList<Metadata> userDefinedMetadataArrayList = new ArrayList<>();
        userDefinedMetadataArrayList.add(userDefinedMetadata_nes1);
        userDefinedMetadataArrayList.add(userDefinedMetadata_nes2);

        niftiMRS.setDim_5_header("Excitation pulse information", userDefinedMetadataArrayList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter("test.json")) {
            gson.toJson(niftiMRS, file);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        Object object = gson.fromJson(new FileReader("test.json"), JsonExtention.class);
        System.out.println(object);

    }
}
