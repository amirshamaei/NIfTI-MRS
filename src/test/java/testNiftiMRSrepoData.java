import org.NifTiMRS.NiftiMRS;

import java.io.IOException;

public class testNiftiMRSrepoData {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new URL("https://github.com/wexeee/mrs_nifti_standard/blob/master/example_data/svs_1/svs_preprocessed.nii.gz").openStream();
//        Files.copy(inputStream, Paths.get(System.getProperty("user.dir") + "/svs_preprocessed.nii.gz"), StandardCopyOption.REPLACE_EXISTING);
        NiftiMRS niftiMRS = NiftiMRS.read("C:\\Users\\Amir Shamaei\\Downloads\\example_01.nii");
        System.out.println("done!");
//        NiftiMRS niftiCSI = NiftiMRS.read("D:\\csi.nii");
//        System.out.println("done!");
    }
}
