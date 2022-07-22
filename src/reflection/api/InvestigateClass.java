package reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class InvestigateClass implements Investigator {

    private Class clazz;
    private Object anInstanceOfSomething;
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
    /**
     * checks if the given class extends a super class, that is not Object (which all extend by default, implicitly)
     *
     * @return true if the class extends, false otherwise
     */
    public boolean isExtending() {
      if(clazz.getSuperclass().equals(Object.class)||(clazz.equals(Object.class)))
      {
          return false;
      }

        return true;
    }
    /**
     * get the name of the direct parent class
     * the name of the class is the short, simple name and not the fully qualified name
     *
     * @return the name of the parent class. null if this class doesn't extend other class
     */
    @Override
    public String getParentClassSimpleName() {
        if(this.isExtending()) {
            return clazz.getSuperclass().getSimpleName();
        }
        return null;
    }
    /**
     * checks if the given class parent is of type abstract
     *
     * @return true if the parent class is abstract, false otherwise (also in case the given class does not extends any other class)
     */
    @Override
    public boolean isParentClassAbstract() {
        if(this.isExtending())
        {
            return false;
        }
        else if(Modifier.isAbstract( clazz.getSuperclass().getModifiers()))
        {
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        return null;
    }

    /**
     /**
     * invokes a method that returns an int value, on the given instance.
     * the method to invoke will be given by its name only.
     * You can assume that there is exactly one such method and that
     * the method is declared on the instance itself and not as part of its inheritance chain
     *
     * @param methodName the name of the method to invoke
     * @param args the arguments to pass to the method, if such exists.
     *             Note: You should not use the arguments in order to extract and identify the method.
     *                   You can do that only (and simply) by its name.
     *                   You just need to pass the arguments AS IS to the method invocation...
     *
     * @return the result returned from the method invocation
     */
    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        int result=0;
        try{
            Method func = clazz.getDeclaredMethod(methodName, new Class[]{});
            result = (int)func.invoke(anInstanceOfSomething,args);
        }
        catch(Exception e){}


        return result;
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
