package hurricane;

import java.util.ArrayList;
import java.util.function.Function;

public class HurricaneData extends ArrayList<Hurricane> {
   public HurricaneData select(Function<Hurricane, Boolean> func) {
      HurricaneData selected = new HurricaneData();

      for (Hurricane hurricane : this) {
         if (func.apply(hurricane)) {
            selected.add(hurricane);
         }
      }

      return selected;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();

      for (Hurricane hurricane : this) {
         builder.append(hurricane.toString());
         builder.append("\n");
      }

      return builder.toString();
   }
}
