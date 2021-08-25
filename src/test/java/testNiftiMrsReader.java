import org.NifTiMRS.NiftiMRS;

import java.io.IOException;

public class testNiftiMrsReader {
    public static void main(String[] args) throws IOException {
//        org.NifTiMRS.NiftiMRS niftiMRS1 = org.NifTiMRS.NiftiMRS.read("D:\\forBern\\NIfTI-MRS\\testNiftiHeadandVol2.json");
//        System.out.println("done!");
        NiftiMRS niftiMRS2 = NiftiMRS.read("D:\\forBern\\NIfTI-MRS\\testNiftiHeadandVol2.nii.gz");
        System.out.println(
                niftiMRS2.getNifti().getHeader2().toString()
        );
        System.out.println("done!");
//        niftiMRS2.getNifti()
    }
}
