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

/**
 * Employee class
 *
 * Implementing Comparable interface
 * Implements API to be comparable with YAML parser
 */
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

   /**
    * Id setter with conversion to absolute value
    */
   public void setId(Integer id) {
      this.id = Math.abs(id);
   }

   /**
    * Age getter
    *
    * Does not have `get` prefix, so it's not used by YAML parser
    * @return The age of employee
    */
   public Integer Age() {
      Period period = Period.between(toLocalDate(birthDate), toLocalDate(today()));
      return period.getYears();
   }

   public String getFirstName() {
      return firstName;
   }

   /**
    * Birth date setter with validation
    */
   public void setFirstName(String firstName) {
      validateString(firstName);
      this.firstName = firstName;
   }

   public String getSurname() {
      return surname;
   }

   /**
    * Surname setter with validation
    */
   public void setSurname(String surname) {
      validateString(surname);
      this.surname = surname;
   }

   public String getSex() {
      return sex;
   }

   /**
    * Sex setter with validation
    */
   public void setSex(String sex) {
      validateSex(sex);
      this.sex = sex;
   }

   /**
    * Birth date getter
    * @return Date of birth
    */
   public Date birthDate() {
      return birthDate;
   }

   /**
    * Birth date getter used by YAML parser
    * @return Birth date as a string
    */
   public String getBirthDate() {
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      return formatter.format(birthDate);
   }

   /**
    * Birth date setter with parsing to Date type
    */
   public void setBirthDate(String birthDate) throws ParseException {
      this.birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDate);
   }

   public String getPosition() {
      return position;
   }

   /**
    * Position setter with validation
    */
   public void setPosition(String position) {
      validateString(position);
      this.position = position;
   }

   private Date today() {
      return Calendar.getInstance().getTime();
   }

   /**
    * Date to LocalDate converter
    * @param date Date
    * @return Converted LocalDate
    */
   private LocalDate toLocalDate(Date date) {
      return Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
   }

   /**
    * Validation function which checks whether a string is null or empty
    * @param string Any string value
    */
   private void validateString(String string) {
      if (string == null || string.isEmpty()) {
         throw new IllegalArgumentException("Employee argument cannot be empty");
      }
   }

   /**
    * Validation function for sex
    * @param sex Sex type
    */
   private void validateSex(String sex) {
      validateString(sex);
      if (!sex.equals("male") && !sex.equals("female")) {
         throw new IllegalArgumentException("Sex must be set to `male` or `female`");
      }
   }
}
