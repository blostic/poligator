package pl.agh.student.service;

import java.util.Comparator;

/**
 * Created by blost on 5/6/2016.
 */
public class UserConfigComparator implements Comparator<UserApplyCoordinates> {

    @Override
    public int compare(UserApplyCoordinates o1, UserApplyCoordinates o2) {
        int depthCompare = Integer.compare(o1.getDepth(), o2.getDepth());
        if (depthCompare != 0) {
            return depthCompare;
        }
        int primeReferenceCompare = Integer.compare(o1.getNumberOfPrimeReferences(), o2.getNumberOfPrimeReferences());
        if (primeReferenceCompare != 0) {
            return primeReferenceCompare;
        }
        int secundaryReferenceCompare = Integer.compare(o1.getNumberOfPrimeReferences(), o2.getNumberOfPrimeReferences());
        if (secundaryReferenceCompare != 0) {
            return secundaryReferenceCompare;
        }
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
