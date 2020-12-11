import java.io.IOException;

public class testNiftiHeadandVol {
    public static void main(String[] args) throws IOException {
        NiftiMRS niftiMRS = new NiftiMRS(new int[] {1,1,1,2048});

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <2 ; j++) {
                niftiMRS.getNifti().data.set(new int[] {j,0,0,i},Math.random());
            }
        }

        niftiMRS.getNifti().header.pixdim[4] = (float) 0.00025;
        niftiMRS.getJson().ResonantNucleus = new String[] {Nucleus.N_1H.toString()};
        niftiMRS.getJson().SpectrometerFrequency = new Double[] {400d};

        niftiMRS.write("testNiftiHeadandVol1", false, false);
        niftiMRS.write("testNiftiHeadandVol2", true, true);

    }
}
