package com.oceanview.util;
import java.time.LocalDate;
import java.util.regex.Pattern;
public class ValidationUtil {
    private static final Pattern NIC_OLD = Pattern.compile("^[0-9]{9}[vVxX]$");
    private static final Pattern NIC_NEW = Pattern.compile("^[0-9]{12}$");
    private static final Pattern EMAIL   = Pattern.compile("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PHONE   = Pattern.compile("^[0-9]{10}$");
    public static boolean isValidNIC(String nic)
    { if(nic==null)return false; return NIC_OLD.matcher(nic).matches()||NIC_NEW.matcher(nic).matches(); }
    public static boolean isValidEmail(String email)
    { return email!=null&&!email.isEmpty()&&EMAIL.matcher(email).matches(); }
    public static boolean isValidPhone(String phone)
    { return phone!=null&&PHONE.matcher(phone).matches(); }
    public static boolean isNotEmpty(String value)
    { return value!=null&&!value.trim().isEmpty(); }
    public static boolean isValidDateRange(LocalDate in,LocalDate out)
    { return in!=null&&out!=null&&out.isAfter(in); }
    public static boolean isFutureOrToday(LocalDate date)
    { return date!=null&&!date.isBefore(LocalDate.now()); }
}
