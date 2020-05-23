package hurricane;

import java.util.Comparator;

public class HurricaneComparatorBySpeed implements Comparator<Hurricane> {
   @Override
   public int compare(Hurricane o1, Hurricane o2) {
      return o1.getSpeedInKilometersPerHour().compareTo(o2.getSpeedInKilometersPerHour());
   }
}
