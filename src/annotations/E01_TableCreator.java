package annotations;

import annotations.database.Constraints;
import annotations.database.DBTable;
import annotations.database.SQLInteger;
import annotations.database.SQLString;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLBoolean {
    String name( ) default "";
    Constraints constraints( ) default @Constraints;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLCharacter {
    int value( ) default 0;

    String name( ) default "";

    Constraints constraints( ) default @Constraints;
}

@DBTable(name = "MEMBER")
class Member {
    static int memberCount;
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;
    @SQLCharacter(value = 15, constraints = @Constraints(primaryKey = true))
    String handle;
    @SQLBoolean
    boolean isVIP;

    public static int getMemberCount( ){
        return memberCount;
    }

    public String getFirstName( ){
        return firstName;
    }

    public String getLastName( ){
        return lastName;
    }

    public Integer getAge( ){
        return age;
    }

    public String getHandle( ){
        return handle;
    }

    public boolean isVIP( ){
        return isVIP;
    }
}

public class E01_TableCreator {
    public static void main(String[] args) throws Exception{
        if ( args.length < 1 ) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for ( String className : args ) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if ( dbTable == null ) {
                System.out.println(
                        "No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
// If the name is empty, use the Class name:
            if ( tableName.length() < 1 )
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<String>();
            for ( Field field : cl.getDeclaredFields() ) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if ( anns.length < 1 )
                    continue; // Not a db table column
                if ( anns[0] instanceof SQLInteger ) {
                    SQLInteger sInt = ( SQLInteger ) anns[0];
// Use field name if name not specified
                    if ( sInt.name().length() < 1 )
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sInt.constraints()));
                } else if ( anns[0] instanceof SQLString ) {
                    SQLString sString = ( SQLString ) anns[0];
// Use field name if name not specified.
                    if ( sString.name().length() < 1 )
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" +
                            //Annotations  577
                            sString.value() + ")" +
                            getConstraints(sString.constraints()));
                } else if ( anns[0] instanceof SQLBoolean ) {
                    SQLBoolean sBol = ( SQLBoolean ) anns[0];
// Use field name if name not specified
                    if ( sBol.name().length() < 1 )
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sBol.name();
                    columnDefs.add(columnName + " BOOLEAN" +
                            getConstraints(sBol.constraints()));
                } else if ( anns[0] instanceof SQLCharacter ) {
                    SQLCharacter sChar = ( SQLCharacter ) anns[0];
// Use field name if name not specified.
                    if ( sChar.name().length() < 1 )
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sChar.name();
                    columnDefs.add(columnName + " CHARACTER(" +
                            sChar.value() + ")" +
                            getConstraints(sChar.constraints()));
                }
                StringBuilder createCommand = new StringBuilder(
                        "CREATE TABLE " + tableName + "(");
                for ( String columnDef : columnDefs )
                    createCommand.append("\n " + columnDef + ",");
// Remove trailing comma
                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " +
                        className + " is :\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con){
        String constraints = "";
        if ( !con.allowNull() )
            constraints += " NOT NULL";
        if ( con.primaryKey() )
            constraints += " PRIMARY KEY";
        if ( con.unique() )
            constraints += " UNIQUE";
        return constraints;
    }
}