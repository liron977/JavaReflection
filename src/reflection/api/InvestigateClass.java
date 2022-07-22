package reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class InvestigateClass implements Investigator {

    private Class clazz;
    public void load(Object anInstanceOfSomething)
    {
        clazz = anInstanceOfSomething.getClass();
    }
    public int getTotalNumberOfMethods()
    {
        Method[]  allMethods=clazz.getDeclaredMethods();
        return allMethods.length;
    }
    public int getTotalNumberOfConstructors()
    {
        Constructor[] allConstructors =clazz.getConstructors();
        return  allConstructors.length;
    }
    public int getTotalNumberOfFields()
    {
        Field[] allFields=clazz.getDeclaredFields();
        return allFields.length;
    }

    @Override
     public Set<String> getAllImplementedInterfaces() {
        return null;
    }

    /**
     * returns the names of all the interfaces that this class implements
     * (and NOT the ones that are being implemented by it's ancestor in the inheritance chain)
     * Note that by name I mean simple, short Name that is only the interface name,
     * and not its fully qualified name including the package it is located within...
     *
     * @return set of interfaces names
     */
/*    Set<String> getAllImplementedInterfaces()
    {
        Class[] interfaces = clazz.getInterfaces();
        for(int i=0;i<interfaces.length;i++)
        {
            return interfaces[i].getSimpleName();
        }

    }*/
    public int getCountOfConstantFields() {
        int countOfConstantFields=0;
        Field[] allFields = clazz.getDeclaredFields();
        for (int i = 0; i < allFields.length; i++) {
            if (Modifier.isFinal(allFields[i].getModifiers())) {
                countOfConstantFields++;
            }
        }
        return  countOfConstantFields;
    }
    public int getCountOfStaticMethods()
    {
        int countOfStaticMethods=0;
        Method[]  allMethods=clazz.getDeclaredMethods();
        for (int i = 0; i < allMethods.length; i++) {
            if (Modifier.isStatic(allMethods[i].getModifiers())) {
                countOfStaticMethods++;
            }
        }
        return  countOfStaticMethods;
    }

    @Override
    public boolean isExtending() {
        return false;
    }

    @Override
    public String getParentClassSimpleName() {
        return null;
    }

    @Override
    public boolean isParentClassAbstract() {
        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        return null;
    }

    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        return 0;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) {
        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        return null;
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        return null;
    }
    /**
     * checks if the given class extends a super class, that is not Object (which all extend by default, implicitly)
     *
     * @return true if the class extends, false otherwise
     */
 /*   boolean isExtending()
    {

    }*/


}
