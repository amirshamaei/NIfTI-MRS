import java.io.FileNotFoundException;

public class testNiftiMrsReader {
    public static void main(String[] args) throws FileNotFoundException {
        NiftiMRS niftiMRS1 = NiftiMRS.read("D:\\forBern\\NIfTI-MRS\\testNiftiHeadandVol1.json");
        System.out.println("done!");
        NiftiMRS niftiMRS2 = NiftiMRS.read("D:\\forBern\\NIfTI-MRS\\testNiftiHeadandVol2.nii.gz");
        System.out.println("done!");
    }
}
