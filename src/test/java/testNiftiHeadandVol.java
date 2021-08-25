import org.NifTiMRS.NiftiMRS;
import org.NifTiMRS.Nucleus;

import java.io.IOException;

public class testNiftiHeadandVol {
    public static void main(String[] args) throws IOException {
        NiftiMRS niftiMRS = new NiftiMRS(new int[] {1,1,3,100});
        for (int k = 0; k < 3 ; k++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 2; j++) {
                    niftiMRS.getNifti().getData().set(new int[]{j, 0, k, i}, 2 * Math.sin(2 * i)+2*k + j );
                }
            }
        }

        niftiMRS.getNifti().getHeader2().pixdim[4] = 0.00025;
        niftiMRS.getJson().setResonantNucleus(new String[] {Nucleus.N_1H.toString()});
        niftiMRS.getJson().setSpectrometerFrequency(new Double[] {123d});

//        niftiMRS.write("D:\\forBern\\NIfTI-MRS\\testNiftiHeadandVol2.nii", true, true);
        niftiMRS.write("testNiftiHeadandVol2", true, true);

    }
}
