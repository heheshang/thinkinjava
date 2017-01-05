package practice;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
public class E20_ClassDump {
    public static void display(String msg, Object[] vals){
        print(msg);
        for ( Object val : vals )
            print(" " + val);
    }

    public static void
    classInfo(Class<?> c) throws Exception{
        print("c.getName(): " + c.getName());
        print("c.getPackage(): " + c.getPackage());
        print("c.getSuperclass(): " + c.getSuperclass());
// This produces all the classes declared as members:
        display("c.getDeclaredClasses()",
                c.getDeclaredClasses());
        display("c.getClasses()", c.getClasses());
        display("c.getInterfaces()", c.getInterfaces());
// The "Declared" version includes all
// versions, not just public:
        display("c.getDeclaredMethods()",
                c.getDeclaredMethods());
        display("c.getMethods()", c.getMethods());
        display("c.getDeclaredConstructors()",
                c.getDeclaredConstructors());
        display("c.getConstructors()", c.getConstructors());
        display("c.getDeclaredFields()",
                c.getDeclaredFields());
        display("c.getFields()", c.getFields());
        if ( c.getSuperclass() != null ) {
            print("Base class: --------");
            classInfo(c.getSuperclass());
        }
        print("End of " + c.getName());
    }

    public static void main(String[] args) throws Exception{
        for ( String arg : args )
            classInfo(Class.forName(arg));
    }
} /*
c.getName(): java.lang.String
c.getPackage(): package java.lang, Java Platform API Specification, version 1.8
c.getSuperclass(): class java.lang.Object
c.getDeclaredClasses()
 class java.lang.String$CaseInsensitiveComparator
c.getClasses()
c.getInterfaces()
 interface java.io.Serializable
 interface java.lang.Comparable
 interface java.lang.CharSequence
c.getDeclaredMethods()
 public boolean java.lang.String.equals(java.lang.Object)
 public java.lang.String java.lang.String.toString()
 public int java.lang.String.hashCode()
 public int java.lang.String.compareTo(java.lang.String)
 public int java.lang.String.compareTo(java.lang.Object)
 public int java.lang.String.indexOf(java.lang.String,int)
 public int java.lang.String.indexOf(java.lang.String)
 public int java.lang.String.indexOf(int,int)
 public int java.lang.String.indexOf(int)
 static int java.lang.String.indexOf(char[],int,int,char[],int,int,int)
 static int java.lang.String.indexOf(char[],int,int,java.lang.String,int)
 public static java.lang.String java.lang.String.valueOf(int)
 public static java.lang.String java.lang.String.valueOf(long)
 public static java.lang.String java.lang.String.valueOf(float)
 public static java.lang.String java.lang.String.valueOf(boolean)
 public static java.lang.String java.lang.String.valueOf(char[])
 public static java.lang.String java.lang.String.valueOf(char[],int,int)
 public static java.lang.String java.lang.String.valueOf(java.lang.Object)
 public static java.lang.String java.lang.String.valueOf(char)
 public static java.lang.String java.lang.String.valueOf(double)
 public char java.lang.String.charAt(int)
 private static void java.lang.String.checkBounds(byte[],int,int)
 public int java.lang.String.codePointAt(int)
 public int java.lang.String.codePointBefore(int)
 public int java.lang.String.codePointCount(int,int)
 public int java.lang.String.compareToIgnoreCase(java.lang.String)
 public java.lang.String java.lang.String.concat(java.lang.String)
 public boolean java.lang.String.contains(java.lang.CharSequence)
 public boolean java.lang.String.contentEquals(java.lang.CharSequence)
 public boolean java.lang.String.contentEquals(java.lang.StringBuffer)
 public static java.lang.String java.lang.String.copyValueOf(char[])
 public static java.lang.String java.lang.String.copyValueOf(char[],int,int)
 public boolean java.lang.String.endsWith(java.lang.String)
 public boolean java.lang.String.equalsIgnoreCase(java.lang.String)
 public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[])
 public static java.lang.String java.lang.String.format(java.lang.String,java.lang.Object[])
 public void java.lang.String.getBytes(int,int,byte[],int)
 public byte[] java.lang.String.getBytes(java.nio.charset.Charset)
 public byte[] java.lang.String.getBytes(java.lang.String) throws java.io.UnsupportedEncodingException
 public byte[] java.lang.String.getBytes()
 public void java.lang.String.getChars(int,int,char[],int)
 void java.lang.String.getChars(char[],int)
 private int java.lang.String.indexOfSupplementary(int,int)
 public native java.lang.String java.lang.String.intern()
 public boolean java.lang.String.isEmpty()
 public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.CharSequence[])
 public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.Iterable)
 public int java.lang.String.lastIndexOf(int)
 public int java.lang.String.lastIndexOf(java.lang.String)
 static int java.lang.String.lastIndexOf(char[],int,int,java.lang.String,int)
 public int java.lang.String.lastIndexOf(java.lang.String,int)
 public int java.lang.String.lastIndexOf(int,int)
 static int java.lang.String.lastIndexOf(char[],int,int,char[],int,int,int)
 private int java.lang.String.lastIndexOfSupplementary(int,int)
 public int java.lang.String.length()
 public boolean java.lang.String.matches(java.lang.String)
 private boolean java.lang.String.nonSyncContentEquals(java.lang.AbstractStringBuilder)
 public int java.lang.String.offsetByCodePoints(int,int)
 public boolean java.lang.String.regionMatches(int,java.lang.String,int,int)
 public boolean java.lang.String.regionMatches(boolean,int,java.lang.String,int,int)
 public java.lang.String java.lang.String.replace(char,char)
 public java.lang.String java.lang.String.replace(java.lang.CharSequence,java.lang.CharSequence)
 public java.lang.String java.lang.String.replaceAll(java.lang.String,java.lang.String)
 public java.lang.String java.lang.String.replaceFirst(java.lang.String,java.lang.String)
 public java.lang.String[] java.lang.String.split(java.lang.String)
 public java.lang.String[] java.lang.String.split(java.lang.String,int)
 public boolean java.lang.String.startsWith(java.lang.String,int)
 public boolean java.lang.String.startsWith(java.lang.String)
 public java.lang.CharSequence java.lang.String.subSequence(int,int)
 public java.lang.String java.lang.String.substring(int)
 public java.lang.String java.lang.String.substring(int,int)
 public char[] java.lang.String.toCharArray()
 public java.lang.String java.lang.String.toLowerCase(java.util.Locale)
 public java.lang.String java.lang.String.toLowerCase()
 public java.lang.String java.lang.String.toUpperCase()
 public java.lang.String java.lang.String.toUpperCase(java.util.Locale)
 public java.lang.String java.lang.String.trim()
c.getMethods()
 public boolean java.lang.String.equals(java.lang.Object)
 public java.lang.String java.lang.String.toString()
 public int java.lang.String.hashCode()
 public int java.lang.String.compareTo(java.lang.String)
 public int java.lang.String.compareTo(java.lang.Object)
 public int java.lang.String.indexOf(java.lang.String,int)
 public int java.lang.String.indexOf(java.lang.String)
 public int java.lang.String.indexOf(int,int)
 public int java.lang.String.indexOf(int)
 public static java.lang.String java.lang.String.valueOf(int)
 public static java.lang.String java.lang.String.valueOf(long)
 public static java.lang.String java.lang.String.valueOf(float)
 public static java.lang.String java.lang.String.valueOf(boolean)
 public static java.lang.String java.lang.String.valueOf(char[])
 public static java.lang.String java.lang.String.valueOf(char[],int,int)
 public static java.lang.String java.lang.String.valueOf(java.lang.Object)
 public static java.lang.String java.lang.String.valueOf(char)
 public static java.lang.String java.lang.String.valueOf(double)
 public char java.lang.String.charAt(int)
 public int java.lang.String.codePointAt(int)
 public int java.lang.String.codePointBefore(int)
 public int java.lang.String.codePointCount(int,int)
 public int java.lang.String.compareToIgnoreCase(java.lang.String)
 public java.lang.String java.lang.String.concat(java.lang.String)
 public boolean java.lang.String.contains(java.lang.CharSequence)
 public boolean java.lang.String.contentEquals(java.lang.CharSequence)
 public boolean java.lang.String.contentEquals(java.lang.StringBuffer)
 public static java.lang.String java.lang.String.copyValueOf(char[])
 public static java.lang.String java.lang.String.copyValueOf(char[],int,int)
 public boolean java.lang.String.endsWith(java.lang.String)
 public boolean java.lang.String.equalsIgnoreCase(java.lang.String)
 public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[])
 public static java.lang.String java.lang.String.format(java.lang.String,java.lang.Object[])
 public void java.lang.String.getBytes(int,int,byte[],int)
 public byte[] java.lang.String.getBytes(java.nio.charset.Charset)
 public byte[] java.lang.String.getBytes(java.lang.String) throws java.io.UnsupportedEncodingException
 public byte[] java.lang.String.getBytes()
 public void java.lang.String.getChars(int,int,char[],int)
 public native java.lang.String java.lang.String.intern()
 public boolean java.lang.String.isEmpty()
 public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.CharSequence[])
 public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.Iterable)
 public int java.lang.String.lastIndexOf(int)
 public int java.lang.String.lastIndexOf(java.lang.String)
 public int java.lang.String.lastIndexOf(java.lang.String,int)
 public int java.lang.String.lastIndexOf(int,int)
 public int java.lang.String.length()
 public boolean java.lang.String.matches(java.lang.String)
 public int java.lang.String.offsetByCodePoints(int,int)
 public boolean java.lang.String.regionMatches(int,java.lang.String,int,int)
 public boolean java.lang.String.regionMatches(boolean,int,java.lang.String,int,int)
 public java.lang.String java.lang.String.replace(char,char)
 public java.lang.String java.lang.String.replace(java.lang.CharSequence,java.lang.CharSequence)
 public java.lang.String java.lang.String.replaceAll(java.lang.String,java.lang.String)
 public java.lang.String java.lang.String.replaceFirst(java.lang.String,java.lang.String)
 public java.lang.String[] java.lang.String.split(java.lang.String)
 public java.lang.String[] java.lang.String.split(java.lang.String,int)
 public boolean java.lang.String.startsWith(java.lang.String,int)
 public boolean java.lang.String.startsWith(java.lang.String)
 public java.lang.CharSequence java.lang.String.subSequence(int,int)
 public java.lang.String java.lang.String.substring(int)
 public java.lang.String java.lang.String.substring(int,int)
 public char[] java.lang.String.toCharArray()
 public java.lang.String java.lang.String.toLowerCase(java.util.Locale)
 public java.lang.String java.lang.String.toLowerCase()
 public java.lang.String java.lang.String.toUpperCase()
 public java.lang.String java.lang.String.toUpperCase(java.util.Locale)
 public java.lang.String java.lang.String.trim()
 public final void java.lang.Object.wait() throws java.lang.InterruptedException
 public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
 public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
 public final native java.lang.Class java.lang.Object.getClass()
 public final native void java.lang.Object.notify()
 public final native void java.lang.Object.notifyAll()
 public default java.util.stream.IntStream java.lang.CharSequence.chars()
 public default java.util.stream.IntStream java.lang.CharSequence.codePoints()
c.getDeclaredConstructors()
 public java.lang.String(byte[],int,int)
 public java.lang.String(byte[],java.nio.charset.Charset)
 public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
 public java.lang.String(byte[],int,int,java.nio.charset.Charset)
 public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
 java.lang.String(char[],boolean)
 public java.lang.String(java.lang.StringBuilder)
 public java.lang.String(java.lang.StringBuffer)
 public java.lang.String(byte[])
 public java.lang.String(int[],int,int)
 public java.lang.String()
 public java.lang.String(char[])
 public java.lang.String(java.lang.String)
 public java.lang.String(char[],int,int)
 public java.lang.String(byte[],int)
 public java.lang.String(byte[],int,int,int)
c.getConstructors()
 public java.lang.String(byte[],int,int)
 public java.lang.String(byte[],java.nio.charset.Charset)
 public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
 public java.lang.String(byte[],int,int,java.nio.charset.Charset)
 public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
 public java.lang.String(java.lang.StringBuilder)
 public java.lang.String(java.lang.StringBuffer)
 public java.lang.String(byte[])
 public java.lang.String(int[],int,int)
 public java.lang.String()
 public java.lang.String(char[])
 public java.lang.String(java.lang.String)
 public java.lang.String(char[],int,int)
 public java.lang.String(byte[],int)
 public java.lang.String(byte[],int,int,int)
c.getDeclaredFields()
 private final char[] java.lang.String.value
 private int java.lang.String.hash
 private static final long java.lang.String.serialVersionUID
 private static final java.io.ObjectStreamField[] java.lang.String.serialPersistentFields
 public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
c.getFields()
 public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
Base class: --------
c.getName(): java.lang.Object
c.getPackage(): package java.lang, Java Platform API Specification, version 1.8
c.getSuperclass(): null
c.getDeclaredClasses()
c.getClasses()
c.getInterfaces()
c.getDeclaredMethods()
 protected void java.lang.Object.finalize() throws java.lang.Throwable
 public final void java.lang.Object.wait() throws java.lang.InterruptedException
 public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
 public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
 public boolean java.lang.Object.equals(java.lang.Object)
 public java.lang.String java.lang.Object.toString()
 public native int java.lang.Object.hashCode()
 public final native java.lang.Class java.lang.Object.getClass()
 protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException
 public final native void java.lang.Object.notify()
 public final native void java.lang.Object.notifyAll()
 private static native void java.lang.Object.registerNatives()
c.getMethods()
 public final void java.lang.Object.wait() throws java.lang.InterruptedException
 public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
 public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
 public boolean java.lang.Object.equals(java.lang.Object)
 public java.lang.String java.lang.Object.toString()
 public native int java.lang.Object.hashCode()
 public final native java.lang.Class java.lang.Object.getClass()
 public final native void java.lang.Object.notify()
 public final native void java.lang.Object.notifyAll()
c.getDeclaredConstructors()
 public java.lang.Object()
c.getConstructors()
 public java.lang.Object()
c.getDeclaredFields()
c.getFields()
End of java.lang.Object
End of java.lang.String

*///:~

