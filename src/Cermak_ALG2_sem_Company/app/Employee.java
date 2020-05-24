package Cermak_ALG2_sem_Company.app;

import java.text.Collator;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Employee implements Comparable<Employee> {
   private Integer id;
   private String firstName;
   private String surname;
   private String sex;
   private Date birthDate;
   private String position;

   @Override
   public int compareTo(Employee o) {
      Collator col = Collator.getInstance(new Locale("cs", "CZ"));
      return col.compare(this.firstName, o.firstName);
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = Math.abs(id);
   }

   public Integer Age() {
      Period period = Period.between(toLocalDate(birthDate), toLocalDate(today()));
      return period.getYears();
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      validateString(firstName);
      this.firstName = firstName;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      validateString(surname);
      this.surname = surname;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      validateSex(sex);
      this.sex = sex;
   }

   public Date birthDate() {
      return birthDate;
   }

   public String getBirthDate() {
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      return formatter.format(birthDate);
   }

   public void setBirthDate(String birthDate) throws ParseException {
      this.birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDate);
   }

   public String getPosition() {
      return position;
   }

   public void setPosition(String position) {
      validateString(position);
      this.position = position;
   }

   private Date today() {
      return Calendar.getInstance().getTime();
   }

   private LocalDate toLocalDate(Date date) {
      return Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
   }

   private void validateString(String string) {
      if (string == null || string.isEmpty()) {
         throw new IllegalArgumentException("Employee argument cannot be empty");
      }
   }

   private void validateSex(String sex) {
      validateString(sex);
      if (!sex.equals("male") && !sex.equals("female")) {
         throw new IllegalArgumentException("Sex must be set to `male` or `female`");
      }
   }
}
