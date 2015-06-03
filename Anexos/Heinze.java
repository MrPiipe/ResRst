import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author svanegas
 */
public class Heinze {

  public void hola() {
    Time t1, t2;
    t1 = new Time(0);
    t2 = new Time(180000000);
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(t1.getTime());
    cal.set(Calendar.MINUTE, 0);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTimeInMillis(t2.getTime());
    do {
      System.out.println(getPrettyCal(cal));
      cal.add(Calendar.HOUR_OF_DAY, 1);
    }
    while (cal.before(cal2));
  }
  
  public String getPrettyCal(Calendar cal) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    String formatted = format.format(cal.getTime());
    return formatted;
  }
  
  public static void main(String[] args) {
    new Heinze().hola();
  }
}
